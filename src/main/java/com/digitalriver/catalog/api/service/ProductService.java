package com.digitalriver.catalog.api.service;

import com.digitalriver.catalog.api.domain.Product;
import com.digitalriver.catalog.api.exception.ProductException;

import java.util.List;

public interface ProductService {

    List<Product> get(String productID, String locale) throws ProductException;

    List<Product> load(String productID, String locale) throws ProductException;

    List<Product> pushCatalog(String catalogID);

    List<Product> pushProduct(String productID);

}
