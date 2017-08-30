/**
 * Copyright 1999-2014 dangdang.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zscat.search.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.alibaba.dubbo.config.annotation.Service;
import com.zscat.search.model.IndexObject;
import com.zscat.search.service.SolrSearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

/**
 * @author lishen
 */
@Service(version = "1.0.0",retries = 0,timeout = 60000)
public class SolrSearchServiceImpl implements SolrSearchService {



    /**
     * 增加与修改<br>
     * 增加与修改其实是一回事，只要id不存在，则增加，如果id存在，则是修改
     * @throws IOException
     * @throws SolrServerException
     */
    public Integer upadteIndex(IndexObject goods, String serverUrl) throws Exception{
        HttpSolrClient client = new  HttpSolrClient(serverUrl);
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", goods.getId());
        doc.addField("title", goods.getTitle());
        doc.addField("summary", goods.getKeywords());
        doc.addField("descripton", goods.getDescripton());
        doc.addField("postDate", "goods.getPostDate()");
        doc.addField("url", goods.getUrl());
        client.add(doc);
        //一定要记得提交，否则不起作用
        client.commit();

        client.close();
        return 1;
    }

    public void deleteAll(String serverUrl)throws Exception{
        HttpSolrClient client = new  HttpSolrClient(serverUrl);
        client.deleteByQuery("*:*");
        client.commit();
        client.close();
    }

    /**
     * 删除索引
     * @throws Exception
     */
    public Integer deleteIndex(IndexObject goods,String serverUrl)throws Exception{
        HttpSolrClient client = new  HttpSolrClient(serverUrl);

        //1.删除一个
        client.deleteById(goods.getId().toString());

        //2.删除多个
        List<String> ids = new ArrayList<>();
        ids.add("87");
        ids.add("88");
        client.deleteById(ids);

        //3.根据查询条件删除数据,这里的条件只能有一个，不能以逗号相隔
        client.deleteByQuery("id:zxj1");



        //一定要记得提交，否则不起作用
        client.commit();
        client.close();
        return 1;
    }

    public List<IndexObject> search(String keyword,String serverUrl)throws Exception{
        List<IndexObject> goodsL = new ArrayList<>();
        HttpSolrClient client = new  HttpSolrClient(serverUrl);

        //创建查询对象
        SolrQuery query = new SolrQuery();
        //q 查询字符串，如果查询所有*:*
        query.set("q", keyword);
        //fq 过滤条件，过滤是基于查询结果中的过滤
        //	query.set("fq", "product_catalog_name:幽默杂货");
        //sort 排序，请注意，如果一个字段没有被索引，那么它是无法排序的
//		query.set("sort", "product_price desc");
        //start row 分页信息，与mysql的limit的两个参数一致效果
        query.setStart(0);
        query.setRows(10);
        //fl 查询哪些结果出来，不写的话，就查询全部，所以我这里就不写了
//		query.set("fl", "");
        //df 默认搜索的域
        query.set("df", "keywords");

        //======高亮设置===
        //开启高亮
        query.setHighlight(true);
        //高亮域
        query.addHighlightField("title");
        //前缀
        query.setHighlightSimplePre("<span style='color:red'>");
        //后缀
        query.setHighlightSimplePost("</span>");


        //执行搜索
        QueryResponse queryResponse = client.query(query);
        //搜索结果
        SolrDocumentList results = queryResponse.getResults();
        //查询出来的数量
        long numFound = results.getNumFound();
        System.out.println("总查询出:" + numFound + "条记录");

        //遍历搜索记录
        //获取高亮信息
        Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
        for (SolrDocument doc : results) {
            IndexObject indexObject = new IndexObject();
            indexObject.setId(Long.parseLong(doc.get("id").toString()));
            indexObject.setTitle(doc.get("title").toString());
            indexObject.setKeywords(doc.get("summary").toString());
            indexObject.setDescripton(doc.get("descripton").toString());
            indexObject.setPostDate(doc.get("postDate").toString());
            indexObject.setUrl(doc.get("url").toString());
            goodsL.add(indexObject);
            //输出高亮信息
//            Map<String, List<String>> map = highlighting.get(solrDocument.get("id"));
//            List<String> list = map.get("title");
//            if(list != null && list.size() > 0){
//                System.out.println(list.get(0));
//            }
        }
        client.close();
        return  goodsL;
    }
}
