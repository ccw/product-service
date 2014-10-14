package com.digitalriver.catalog.api.service;

import com.digitalriver.catalog.api.exception.ProductException;
import com.digitalriver.catalog.api.exception.ProductNotDeployedException;
import com.digitalriver.catalog.api.exception.ProductNotFoundException;
import com.digitalriver.catalog.api.mapper.ProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    public static final String EA_PREFIX = "ext_";
    public static final String STATE_DEPLOYED = "Deployed";

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Resource
    protected ProductMapper productMapper;

    @Override
    @SuppressWarnings("unchecked")
    public List<Map<String, ?>> get(String aProductID, String aLocale) throws ProductException {
        final Map<String, ?> product = productMapper.getMetadata(aProductID);
        if (product == null || product.isEmpty()) {
            throw new ProductNotFoundException(aProductID);
        }
        final Map<String, String> states = (Map<String, String>) product.get("STATES");
        if (states.containsKey(ProductServiceImpl.STATE_DEPLOYED)) {
            final String foundProductID = (String) product.get("PRODUCT_ID");
            final Integer version = Integer.parseInt(states.get(ProductServiceImpl.STATE_DEPLOYED));
            final List<Map<String, ?>> productDataList = productMapper.getDisplayData(foundProductID, version, aLocale);
            return this.refineProductDisplayData(productDataList);
        } else {
            logger.warn("No deployed version found on product: " + aProductID);
            throw new ProductNotDeployedException(aProductID);
        }
    }

    @Override
    public List<Map<String, ?>> list(String catalogID) {
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
        return this.refineProductDisplayData(dataList);
    }

    @SuppressWarnings("unchecked")
    protected List<Map<String, ?>> refineProductDisplayData(List<Map<String, ?>> aDataList) {
        final List<Map<String, ?>> result = new ArrayList<>();
        if (aDataList != null && !aDataList.isEmpty()) {
            aDataList.forEach(data -> {
                final Map<String, Object> pData = new TreeMap<>((s1, s2) -> {
                    if (s1.startsWith(ProductServiceImpl.EA_PREFIX)) {
                        if (s2.startsWith(ProductServiceImpl.EA_PREFIX)) {
                            return s1.compareTo(s2);
                        } else {
                            return 1;
                        }
                    } else {
                        if (s2.startsWith(ProductServiceImpl.EA_PREFIX)) {
                            return -1;
                        } else {
                            return s1.compareTo(s2);
                        }
                    }
                });
                data.forEach((k, v) -> {
                    if ("EXTENDED_ATTRIBUTES".equalsIgnoreCase(k)) {
                        ((Map<String, ?>) v).forEach((extK, extV) -> pData.put(ProductServiceImpl.EA_PREFIX + extK, extV == null ? "" : extV));
                    } else {
                        final String key = Arrays.asList(k.split("_")).stream().map(w -> w.replaceAll("\\s", "").toLowerCase()).reduce((s, w) -> s + (s.length() == 0 ? w : w.substring(0, 1).toUpperCase() + w.substring(1).toLowerCase())).orElse("");
                        pData.put(key, v == null ? "" : v);
                    }
                });
                result.add(pData);
            });
        }
        return result;
    }

}
