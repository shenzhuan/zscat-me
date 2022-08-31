package com.mallplus.common.entity.cms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mallplus.common.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 话题表
 * </p>
 *
 * @author zscat
 * @since 2019-04-17
 */
@Data
@TableName("cms_topic")
public class CmsTopic extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 所属分类
     */
    @TableField("category_id")
    private Long categoryId;

    /**
     * 标题
     */
    private String name;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 开始时间
     */
    @TableField("start_time")
    private Date startTime;

    /**
     * 结束时间
     */
    @TableField("end_time")
    private Date endTime;

    /**
     * 参与人数
     */
    @TableField("attend_count")
    private Integer attendCount;

    /**
     * 关注人数
     */
    @TableField("attention_count")
    private Integer attentionCount;

    /**
     * 点击人数
     */
    @TableField("read_count")
    private Integer readCount;

    /**
     * 奖品名称
     */
    @TableField("award_name")
    private String awardName;

    /**
     * 参与方式
     */
    @TableField("attend_type")
    private String attendType;

    /**
     * 话题内容
     */
    private String content;

    private String address;

    private String atids;

    @TableField("area_id")
    private Long areaId;

    @TableField("school_id")
    private Long schoolId;

    @TableField("member_id")
    private Long memberId;

    @TableField("area_name")
    private String areaName;
    @TableField("school_name")
    private String schoolName;

    /**
     * 1 學校 2 區域
     */
    @TableField(exist = false)
    private int qsType;

    @TableField("member_name")
    private String memberName;
}
