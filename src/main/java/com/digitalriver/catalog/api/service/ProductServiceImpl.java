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

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    public static final String EA_PREFIX = "ext_";

    @Resource
    protected ProductMapper productMapper;

    @Override
    @SuppressWarnings("unchecked")
    public List<Map<String, ?>> get(String aProductID, String aLocale) throws ProductException {
        final List<Map<String, ?>> result = new ArrayList<>();
        final Map<String, ?> product = productMapper.getMetadata(aProductID);
        if (product == null || product.isEmpty()) {
            throw new ProductNotFoundException(aProductID);
        }
        final Map<String, String> states = (Map<String, String>) product.get("STATES");
        if (states.containsKey("Deployed")) {
            final String foundProductID = (String) product.get("PRODUCT_ID");
            final Integer version = Integer.parseInt(states.get("Deployed"));
            final List<Map<String, ?>> productDataList = productMapper.getDisplayData(foundProductID, version, aLocale);
            if (productDataList != null && !productDataList.isEmpty()) {
                productDataList.forEach(data -> {
                    final Map<String, Object> pData = new TreeMap<>(new Comparator<String>() {
                        @Override
                        public int compare(String s1, String s2) {
                            if (s1.startsWith(EA_PREFIX)) {
                                if (s2.startsWith(EA_PREFIX)) {
                                    return s1.compareTo(s2);
                                } else {
                                    return 1;
                                }
                            } else {
                                if (s2.startsWith(EA_PREFIX)) {
                                    return -1;
                                } else {
                                    return s1.compareTo(s2);
                                }
                            }
                        }
                    });
                    data.forEach((k, v) -> {
                        if ("EXTENDED_ATTRIBUTES".equalsIgnoreCase(k)) {
                            ((Map<String, ?>) v).forEach((extK, extV) -> pData.put(EA_PREFIX + extK, extV));
                        } else {
                            final String key = Arrays.asList(k.split("_")).stream().map(w -> w.toLowerCase()).reduce((s, w) -> s + (s.length() == 0 ? w : w.substring(0, 1).toUpperCase() + w.substring(1).toLowerCase())).orElse("");
                            pData.put(key, v);
                        }
                    });
                    result.add(pData);
                });
            }
        } else {
            logger.warn("No deployed version found on product: " + aProductID);
            throw new ProductNotDeployedException(aProductID);
        }
        return result;
    }

}
