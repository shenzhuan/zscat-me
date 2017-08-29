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
package com.zscat.search.service;


import com.zscat.search.model.IndexObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lishen
 */
public interface SolrSearchService {

    /**
     * 增加与修改<br>
     * 增加与修改其实是一回事，只要id不存在，则增加，如果id存在，则是修改
     * @throws IOException
     * @throws
     */
    public Integer upadteIndex(IndexObject goods,String serverUrl) throws  Exception;

    void deleteAll(String serverUrl)throws Exception;
    /**
     * 删除索引
     * @throws Exception
     */
    public Integer deleteIndex(IndexObject goods,String serverUrl)throws Exception;

    public List<IndexObject> search(String keyword,String serverUrl)throws Exception;
}
