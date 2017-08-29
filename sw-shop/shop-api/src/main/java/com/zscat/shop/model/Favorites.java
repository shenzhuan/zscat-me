package com.zscat.shop.model;

import javax.persistence.*;

import com.zsCat.common.base.BaseEntity;

@Table(name = "t_favorites")
public class Favorites extends BaseEntity{
   

    /**
     * 收藏ID
     */
    @Column(name = "fav_id")
    private Long favId;

    /**
     * 收藏类型
     */
    @Column(name = "fav_type")
    private String favType;

    /**
     * 收藏时间
     */
    @Column(name = "fav_time")
    private Long favTime;

    @Column(name = "member_id")
    private Long memberId;

    

    /**
     * 获取收藏ID
     *
     * @return fav_id - 收藏ID
     */
    public Long getFavId() {
        return favId;
    }

    /**
     * 设置收藏ID
     *
     * @param favId 收藏ID
     */
    public void setFavId(Long favId) {
        this.favId = favId;
    }

    /**
     * 获取收藏类型
     *
     * @return fav_type - 收藏类型
     */
    public String getFavType() {
        return favType;
    }

    /**
     * 设置收藏类型
     *
     * @param favType 收藏类型
     */
    public void setFavType(String favType) {
        this.favType = favType;
    }

    /**
     * 获取收藏时间
     *
     * @return fav_time - 收藏时间
     */
    public Long getFavTime() {
        return favTime;
    }

    /**
     * 设置收藏时间
     *
     * @param favTime 收藏时间
     */
    public void setFavTime(Long favTime) {
        this.favTime = favTime;
    }

    /**
     * @return member_id
     */
    public Long getMemberId() {
        return memberId;
    }

    /**
     * @param memberId
     */
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}