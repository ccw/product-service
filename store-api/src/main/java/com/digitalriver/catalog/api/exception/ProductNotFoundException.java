package com.digitalriver.catalog.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends ProductException {

    public ProductNotFoundException(final String aProductID) {
        super("Product: " + aProductID + " Not Found");
    }

}
