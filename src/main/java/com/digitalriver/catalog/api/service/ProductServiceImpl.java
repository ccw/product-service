package com.digitalriver.catalog.api.service;

import com.digitalriver.catalog.api.mapper.ProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Resource
    protected ProductMapper productMapper;

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, ?> get(String aProductID, String aLocale) {
        final Map<String, Object> result = new HashMap<>();
        final Map<String, ?> product = productMapper.getMetadata(aProductID);
        result.putAll(product);
        final Map<String, String> states = (Map<String, String>) product.get("STATES");
        if (states.containsKey("Deployed")) {
            final Integer version = Integer.parseInt(states.get("Deployed"));
            result.putAll(productMapper.getDisplayData(aProductID, version, aLocale));
        } else {
            logger.warn("No deployed version found on product: " + aProductID);
        }
        return result;
    }

}
