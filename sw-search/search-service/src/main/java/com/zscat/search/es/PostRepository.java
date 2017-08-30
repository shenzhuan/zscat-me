package com.zscat.search.es;



import com.zscat.search.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface PostRepository extends ElasticsearchRepository<Post, String> {

    Page<Post> findByTagsName(String name, Pageable pageable);

    List<Post> findByRatingBetween(Double beginning, Double end);

}
