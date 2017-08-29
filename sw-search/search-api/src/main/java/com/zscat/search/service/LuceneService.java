package com.zscat.search.service;

import com.github.pagehelper.PageInfo;
import com.zscat.search.model.IndexObject;


public interface LuceneService {

    void save(IndexObject indexObject);

    void update(IndexObject indexObject);

    void delete(IndexObject indexObject);

    PageInfo page(Integer pageNumber, Integer pageSize, String keyword);
}
