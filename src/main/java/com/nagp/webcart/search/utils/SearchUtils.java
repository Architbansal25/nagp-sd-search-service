package com.nagp.webcart.search.utils;

import com.nagp.webcart.search.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class SearchUtils {
    private final ElasticsearchOperations elasticsearchOperations;

    public List<Product> searchByField(String attribute, String type){
        Criteria criteria = new Criteria(type).matches(attribute);
        CriteriaQuery query = new CriteriaQuery(criteria);
        SearchHits<Product> searchHits = elasticsearchOperations.search(query, Product.class);
        return searchHits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }
}
