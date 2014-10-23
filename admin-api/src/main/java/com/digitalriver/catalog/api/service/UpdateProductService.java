package com.digitalriver.catalog.api.service;

import java.util.List;

import com.digitalriver.catalog.api.domain.Product;

public interface UpdateProductService {

    List<Product> pushCatalog(String catalogID);

    List<Product> pushProduct(String productID);

}
