package com.zscat.cms.model;

import com.zsCat.common.base.BaseEntity;

import javax.persistence.*;

@Table(name = "gw_producttype")
public class GwProductType extends BaseEntity {


    private String name;



    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}