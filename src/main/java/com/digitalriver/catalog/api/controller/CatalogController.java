package com.digitalriver.catalog.api.controller;

import com.digitalriver.catalog.api.exception.ProductException;
import com.digitalriver.catalog.api.service.ProductService;
import groovy.json.JsonBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/cat")
public class CatalogController {

    @Resource
    protected ProductService service;

    @RequestMapping(method = RequestMethod.GET, value = "/{catalogID}", produces = "application/json")
    public String list(@PathVariable String catalogID) throws ProductException {
        return new JsonBuilder(service.list(catalogID)).toPrettyString();
    }

}