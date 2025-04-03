package com.nagp.webcart.search.model;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    private Long id;
    private Integer rating;
    private String title;
    private String comment;
    private String reviewer;
    private String date;
    private Integer likes;
    private Integer dislikes;
}
