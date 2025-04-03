package com.nagp.webcart.search.service;

import com.nagp.webcart.search.model.Product;
import com.nagp.webcart.search.repository.ProductSearchRepository;
import com.nagp.webcart.search.utils.SearchUtils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductSearchService {

    @Autowired
    private ProductSearchRepository productSearchRepository;
    private static final Logger logger = LoggerFactory.getLogger(ProductSearchService.class);
    private SearchUtils searchUtils;


    public List<Product> getAllProducts() {
        Page<Product> pageResult = productSearchRepository.findAll(Pageable.unpaged());
        return pageResult.getContent(); // Extract List<Product>
    }

    public void saveProductsBulk(List<Product> products) {
        for (Product product : products) {
            if (product.getId() == null || product.getId().isEmpty()) {
                product.setId(UUID.randomUUID().toString());
            }
        }
        try{
            productSearchRepository.saveAll(products);
            logger.info("received request completed for bulk product save");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Product saveProduct(Product product) {
        return productSearchRepository.save(product);
    }

    public void deleteProduct(String id) {
        productSearchRepository.deleteById(id);
    }

    public List<Product> searchByName(String name) {
        return productSearchRepository.findByNameContainingIgnoreCase(name);
    }
//public List<Product> searchByName(String name) {
//    return searchUtils.searchByField(name,"name");
//}
    public List<Product> searchByBrand(String brand) {
        return productSearchRepository.findByBrandContainingIgnoreCase(brand);
    }

    public List<Product> searchByCategory(String category) {
        return productSearchRepository.findByCategoriesContainingIgnoreCase(category);
    }
}
