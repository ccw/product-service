package com.digitalriver.catalog.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductNotDeployedException extends ProductException {

    public ProductNotDeployedException(final String aProductID) {
        super("Product: " + aProductID + " is not yet deployed");
    }

}
