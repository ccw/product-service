package com.digitalriver.catalog.api.service;

import javax.annotation.Resource;
import java.util.List;

import com.digitalriver.catalog.api.domain.Product;
import com.digitalriver.catalog.api.exception.ProductException;
import com.digitalriver.catalog.api.exception.ProductNotFoundException;
import com.digitalriver.catalog.api.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FetchProductServiceImpl implements FetchProductService {

    public static final String EA_PREFIX = "ext_";

    private static final Logger logger = LoggerFactory.getLogger(FetchProductServiceImpl.class);

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

}
