package com.zscat.cms.model;

import com.zsCat.common.base.BaseEntity;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;

@Table(name = "gw_product")
public class GwProduct extends BaseEntity {


    private String title;

    private String tag;

    @Column(name = "addTime")
    private Date addtime;

    /**
     * 0
     */
    @Column(name = "clickHit")
    private Integer clickhit;

    private Long typeid;

    private String img;

    private String typename;

    private String type;

    private String remark;

    private String summary;
    @Transient
    private List<String> imagesList=new LinkedList<String>();

    public List<String> getImagesList() {
        return imagesList;
    }

    public void setImagesList(List<String> imagesList) {
        this.imagesList = imagesList;
    }



    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * @param tag
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * @return addTime
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * @param addtime
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**
     * ��ȡ0
     *
     * @return clickHit - 0
     */
    public Integer getClickhit() {
        return clickhit;
    }

    /**
     * ����0
     *
     * @param clickhit 0
     */
    public void setClickhit(Integer clickhit) {
        this.clickhit = clickhit;
    }

    /**
     * @return typeid
     */
    public Long getTypeid() {
        return typeid;
    }

    /**
     * @param typeid
     */
    public void setTypeid(Long typeid) {
        this.typeid = typeid;
    }

    /**
     * @return img
     */
    public String getImg() {
        return img;
    }

    /**
     * @param img
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * @return typename
     */
    public String getTypename() {
        return typename;
    }

    /**
     * @param typename
     */
    public void setTypename(String typename) {
        this.typename = typename;
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * @param summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }
}