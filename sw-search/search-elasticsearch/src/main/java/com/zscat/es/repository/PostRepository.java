package com.zscat.es.repository;


import com.zscat.es.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface PostRepository extends ElasticsearchRepository<Post, String> {

    Page<Post> findByTagsName(String name, Pageable pageable);

    List<Post> findByRatingBetween(Double beginning, Double end);

}
