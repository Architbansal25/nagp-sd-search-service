package com.nagp.webcart.search.repository;

import com.nagp.webcart.search.model.Product;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSearchRepository extends ElasticsearchRepository<Product, String> {
    @Query("{\"match\": {\"name\": \"?0\"}}")
    List<Product> findByNameContainingIgnoreCase(String name);
    @Query("{\"match\": {\"brand\": \"?0\"}}")    List<Product> findByBrandContainingIgnoreCase(String brand);
    @Query("{\"match\": {\"categories\": \"?0\"}}")
    List<Product> findByCategoriesContainingIgnoreCase(String category);
}
