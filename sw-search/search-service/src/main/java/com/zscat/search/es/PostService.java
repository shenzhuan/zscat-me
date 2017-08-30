package com.zscat.search.es;


import com.alibaba.dubbo.config.annotation.Service;
import com.zscat.search.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import java.util.List;

/**
 * Created by patterncat on 2016-01-28.
 */
@Service(version = "1.0.0",retries = 0,timeout = 60000)
public class PostService {

    @Autowired
    PostRepository postRepository;

    public Post save(Post post){
        postRepository.save(post);
        return post;
    }

    public Post findOne(String id) {
        return postRepository.findOne(id);
    }

    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    public Page<Post> findByTagsName(String tagName, PageRequest pageRequest) {
        return postRepository.findByTagsName(tagName, pageRequest);
    }

    List<Post> findByRatingBetween(Double beginning, Double end){
        return postRepository.findByRatingBetween(beginning,end);
    }
}
