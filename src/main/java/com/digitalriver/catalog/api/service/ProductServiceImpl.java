package com.digitalriver.catalog.api.service;

import com.digitalriver.catalog.api.domain.Product;
import com.digitalriver.catalog.api.exception.ProductException;
import com.digitalriver.catalog.api.exception.ProductNotFoundException;
import com.digitalriver.catalog.api.mapper.ProductMapper;
import com.digitalriver.catalog.api.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.beans.PropertyDescriptor;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    public static final String EA_PREFIX = "ext_";

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Resource
    protected ProductMapper productMapper;

    @Resource
    protected ProductRepository productRepository;

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> get(String aProductID, String aLocale) throws ProductException {
        final List<Product> results = productRepository.findByIDAndLocale(aProductID, aLocale);
        if (results == null || results.isEmpty()) {
            throw  new ProductNotFoundException(aProductID);
        }
        return results;
    }

    @Override
    public List<Product> list(String catalogID) {
        final List<String> dataIDs = productMapper.getProductDataIDByCatalog(catalogID);
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

    @SuppressWarnings("unchecked")
    protected List<Product> refineProductDisplayData(List<Map<String, ?>> aDataList) {
        final List<Product> result = new ArrayList<>();
        if (aDataList != null && !aDataList.isEmpty()) {
            aDataList.forEach(data -> {
                final Product pData = new Product();
                data.forEach((k, v) -> {
                    if ("EXTENDED_ATTRIBUTES".equalsIgnoreCase(k)) {
                        pData.setExtendAttributes(new HashMap<>());
                        ((Map<String, ?>) v).forEach((extK, extV) -> {
                            final String valueString = (extV == null ? "" : (extV instanceof List ? ((List<String>) extV).stream().reduce((s, w) -> s.length() == 0 ? w : (s + ", " + w)).orElse("") : extV.toString()));
                            pData.getExtendAttributes().put(ProductServiceImpl.EA_PREFIX + extK, valueString);
                        });
                    } else {
                        final String key = Arrays.asList(k.split("_")).stream().map(w -> w.replaceAll("\\s", "").toLowerCase()).reduce((s, w) -> s + (s.length() == 0 ? w : w.substring(0, 1).toUpperCase() + w.substring(1).toLowerCase())).orElse("");
                        final PropertyDescriptor descriptor = BeanUtils.getPropertyDescriptor(Product.class, key);
                        if (descriptor != null) {
                            try {
                                descriptor.getWriteMethod().invoke(pData, v == null ? "" : v);
                            } catch (final Exception e) {
                                logger.warn("Fail to assign " + key + "=" + v + " to deserialized object", e);
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
