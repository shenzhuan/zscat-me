package com.mallplus.common.entity.ums;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mallplus.common.entity.BaseEntity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zscat
 * @since 2019-04-14
 */
@TableName("sys_area")
public class SysArea extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long pid;

    /**
     * 层级
     */
    private Integer deep;

    /**
     * 名称
     */
    private String name;

    /**
     * 拼音前缀
     */
    @TableField("pinyin_prefix")
    private String pinyinPrefix;

    /**
     * 拼音
     */
    private String pinyin;

    /**
     * 备注名
     */
    @TableField("ext_id")
    private String extId;

    /**
     * 备注名
     */
    @TableField("ext_name")
    private String extName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getDeep() {
        return deep;
    }

    public void setDeep(Integer deep) {
        this.deep = deep;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyinPrefix() {
        return pinyinPrefix;
    }

    public void setPinyinPrefix(String pinyinPrefix) {
        this.pinyinPrefix = pinyinPrefix;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

    public String getExtName() {
        return extName;
    }

    public void setExtName(String extName) {
        this.extName = extName;
    }

    @Override
    public String toString() {
        return "SysArea{" +
        ", id=" + id +
        ", pid=" + pid +
        ", deep=" + deep +
        ", name=" + name +
        ", pinyinPrefix=" + pinyinPrefix +
        ", pinyin=" + pinyin +
        ", extId=" + extId +
        ", extName=" + extName +
        "}";
    }
}
