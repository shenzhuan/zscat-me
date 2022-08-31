package com.mallplus.oauth.service;

import com.mallplus.common.model.PageResult;
import com.mallplus.common.model.Result;
import com.mallplus.common.service.ISuperService;
import com.mallplus.oauth.model.Client;

import java.util.Map;

/**
 * @author mall
 */
public interface IClientService extends ISuperService<Client> {
    Result saveClient(Client clientDto);

    /**
     * 查询应用列表
     * @param params
     * @param isPage 是否分页
     */
    PageResult<Client> listClent(Map<String, Object> params, boolean isPage);

    void delClient(long id);
}
