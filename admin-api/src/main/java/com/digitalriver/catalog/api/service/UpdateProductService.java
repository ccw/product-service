package com.digitalriver.catalog.api.service;

import java.util.List;

import com.digitalriver.catalog.api.domain.Product;

/**
 * Service to update product info.
 *
 * @author <a href="mailto:kchen@digitalriver.com">Ken Chen</a>
 */
public interface UpdateProductService {

    /**
     * Pushes all the products belonging to the given catalog ID to Solr.
     *
     * @param catalogID the catalog ID
     * @return the list of products which have been pushed
     */
    List<Product> pushCatalog(String catalogID);

    /**
     * Pushes the product by the given product ID to solr.
     *
     * @param productID the product ID
     * @return the list of products which have been pushed
     */
    List<Product> pushProduct(String productID);

}
