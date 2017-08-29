package com.zscat.search.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;

import com.zscat.search.lucene.LuceneDao;
import com.zscat.search.model.IndexObject;
import com.zscat.search.service.LuceneService;
import org.springframework.beans.factory.annotation.Autowired;



/**
 * Description:luncene
 *
 * @author Jin
 * @create 2017-06-06
 **/
@Service(version = "1.0.0",retries = 0,timeout = 60000)
public class LuceneServiceImpl implements LuceneService {

    @Autowired
    private LuceneDao luceneDao;


    @Override
    public void save(IndexObject indexObject) {
        luceneDao.create(indexObject);
    }


    @Override
    public void update(IndexObject indexObject) {
        luceneDao.update(indexObject);
    }

    @Override
    public void delete(IndexObject indexObject) {
        luceneDao.deleteAll();
    }

    @Override
    public PageInfo page(Integer pageNumber, Integer pageSize, String keyword) {
        return luceneDao.page(pageNumber,pageSize,keyword);
    }
}
