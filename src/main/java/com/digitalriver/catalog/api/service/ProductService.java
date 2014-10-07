package com.digitalriver.catalog.api.service;

import java.util.Map;

public interface ProductService {

    Map<String, ?> get(String productID, String locale);

}
