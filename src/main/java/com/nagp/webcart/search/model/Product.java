package com.nagp.webcart.search.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Document(indexName = "products") // This tells Elasticsearch to store this data in the "products" index
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {

    @Id
    private String id;
    private Long productId;

    private String name;
    private String description;
    private String brand;
    private Double price;
    private Double originalPrice;
    private String discount;
    private Double rating;
    private Integer reviewsCount;
    private String currency;
    private Integer stock;
    private String image;

    @Field(type = FieldType.Keyword)
    private List<String> categories;

    @Field(type = FieldType.Keyword)
    private List<String> sizes;

    @Field(type = FieldType.Object)
    private Map<String, String> specification;

    @Field(type = FieldType.Nested, includeInParent = true)
    private List<Review> reviews; // Can be empty

}
