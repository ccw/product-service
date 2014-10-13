package com.digitalriver.catalog.api.service;

import com.digitalriver.catalog.api.exception.ProductException;
import com.digitalriver.catalog.api.exception.ProductNotDeployedException;
import com.digitalriver.catalog.api.exception.ProductNotFoundException;
import com.digitalriver.catalog.api.mapper.ProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

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
                result.addAll(productDataList);
//                if (productDataList.size() == 1) {
//                    result.putAll(productDataList.get(0));
//                } else {
//                    result.put("variations", new ArrayList<Map<String, ?>>(productDataList.size() - 1));
//                    productDataList.forEach(data -> {
//                        if (foundProductID.equals(data.get("PRODUCT_ID"))) {
//                            result.putAll(data);
//                        } else {
//                            ((List<Map<String, ?>>) result.get("variations")).add(data);
//                        }
//                    });
//                }
            }
        } else {
            logger.warn("No deployed version found on product: " + aProductID);
            throw new ProductNotDeployedException(aProductID);
        }
        return result;
    }

}
