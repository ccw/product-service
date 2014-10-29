package com.digitalriver.catalog.api.service;

import javax.annotation.Resource;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.digitalriver.catalog.api.domain.Product;
import com.digitalriver.catalog.api.mapper.ProductMapper;
import com.digitalriver.catalog.api.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductServiceImpl implements UpdateProductService {

    private static final Logger logger = LoggerFactory.getLogger(UpdateProductServiceImpl.class);

    protected static final String EA_PREFIX = "ext_";
    protected static final String EXTENDED_ATTRIBUTES = "EXTENDED_ATTRIBUTES";

    @Resource
    protected ProductMapper productMapper;

    @Resource
    protected ProductRepository productRepository;

    @Override
    public List<Product> pushCatalog(final String aCatalogID) {
        final List<String> dataIDs = productMapper.getProductDataIDByCatalog(aCatalogID);
        if (dataIDs == null || dataIDs.isEmpty()) {
            return Collections.emptyList();
        }
        final List<Map<String, ?>> dataList = new ArrayList<>(dataIDs.size());
        dataIDs.forEach(id -> {
            try {
                dataList.add(productMapper.getDisplayDataByID(id));
            } catch (final Exception e) {
                logger.warn("Fail to load product data : [" + id + "], " + e.getMessage());
            }
        });
        final List<Product> products = this.refineProductDisplayData(dataList);
        productRepository.save(products);
        return products;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> pushProduct(final String aProductID) {
        final Map<String, ?> product = productMapper.getMetadata(aProductID);
        if (product == null || product.isEmpty()) {
            return Collections.emptyList();
        }
        final Map<String, String> states = (Map<String, String>) product.get("STATES");
        if (states.containsKey("Deployed")) {
            final String foundProductID = (String) product.get("PRODUCT_ID");
            final List<Product> existProducts = productRepository.findAllByBaseID(foundProductID);
            if (!existProducts.isEmpty()) {
                productRepository.delete(existProducts);
            }
            final Integer version = Integer.parseInt(states.get("Deployed"));
            final List<Map<String, ?>> dataList = productMapper.getAllLocaleDisplayData(foundProductID, version);
            final List<Product> products = this.refineProductDisplayData(dataList);
            productRepository.save(products);
            return products;
        } else {
            logger.warn("No deployed version found on product: " + aProductID);
            return Collections.emptyList();
        }
    }

    @SuppressWarnings("unchecked")
    protected List<Product> refineProductDisplayData(List<Map<String, ?>> aDataList) {
        final List<Product> result = new ArrayList<>();
        if (aDataList != null && !aDataList.isEmpty()) {
            aDataList.forEach(data -> {
                final Product pData = new Product();
                data.forEach((k, v) -> {
                    if (UpdateProductServiceImpl.EXTENDED_ATTRIBUTES.equalsIgnoreCase(k)) {
                        pData.setExtendAttributes(new HashMap<>());
                        ((Map<String, ?>) v).forEach((extK, extV) -> {
                            final String valueString = (extV == null ? "" : (extV instanceof List ? ((List<String>) extV).stream().reduce((s, w) -> s.length() == 0 ? w : (s + ", " + w)).orElse("") : extV.toString()));
                            pData.getExtendAttributes().put(UpdateProductServiceImpl.EA_PREFIX + extK, valueString);
                        });
                    } else {
                        final String key = Arrays.asList(k.split("_")).stream().map(w -> w.replaceAll("\\s", "").toLowerCase()).reduce((s, w) -> s + (s.length() == 0 ? w : w.substring(0, 1).toUpperCase() + w.substring(1).toLowerCase())).orElse("");
                        final PropertyDescriptor descriptor = BeanUtils.getPropertyDescriptor(Product.class, key);
                        if (descriptor != null) {
                            try {
                                descriptor.getWriteMethod().invoke(pData, v == null ? "" : v);
                            } catch (final Exception e) {
                                logger.warn("Fail to assign " + key + "=" + v + " to target object", e);
                            }
                        }
                    }
                });
                if (pData.getBaseProductId() == null) {
                    pData.setBaseProductId(pData.getProductId());
                }
                result.add(pData);
            });
        }
        return result;
    }

}
