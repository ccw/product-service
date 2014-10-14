package com.digitalriver.catalog.api.repository;

import com.digitalriver.catalog.api.domain.Product;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductRepository extends SolrCrudRepository<Product, String> {

    @Query(value = "productId:?0 OR baseProductId:?0", filters = {"locale:?1"})
    List<Product> findByIDAndLocale(String aID, String aLocale);

}
