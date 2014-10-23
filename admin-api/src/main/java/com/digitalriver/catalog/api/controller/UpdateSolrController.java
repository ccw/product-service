package com.digitalriver.catalog.api.controller;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.digitalriver.catalog.api.domain.Product;
import com.digitalriver.catalog.api.service.UpdateProductService;
import com.google.common.collect.ImmutableMap;
import groovy.json.JsonBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/solr")
public class UpdateSolrController {

    @Resource
    protected UpdateProductService service;

    @RequestMapping(method = RequestMethod.POST, value = "/cat/{catalogID}", produces = "application/json")
    public String pushCatalog(@PathVariable String catalogID) {
        final List<Product> updatedProducts = service.pushCatalog(catalogID);
        return new JsonBuilder(ImmutableMap.of(
            "updatedRecordSize", updatedProducts.size(),
            "updatedProductIds", updatedProducts.stream().map(Product::getProductId).collect(Collectors.toCollection(ArrayList<String>::new))
        )).toPrettyString();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/prd/{productID}", produces = "application/json")
    public String pushProduct(@PathVariable String productID) {
        final List<Product> updatedProducts = service.pushProduct(productID);
        return new JsonBuilder(ImmutableMap.of(
            "updatedRecordSize", updatedProducts.size(),
            "updatedProductIds", updatedProducts.stream().map(Product::getProductId).collect(Collectors.toCollection(ArrayList<String>::new))
        )).toPrettyString();
    }

}
