package com.mallplus.common.entity.cms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mallplus.common.entity.BaseEntity;
import com.mallplus.common.utils.ValidatorUtils;
import com.mallplus.common.vo.SamplePmsProduct;
import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 专题表
 * </p>
 *
 * @author zscat
 * @since 2019-04-17
 */
@Data
@TableName("cms_subject")
public class CmsSubject extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 分类
     */
    @TableField("category_id")
    private Long categoryId;

    /**
     * 标题
     */
    private String title;

    /**
     * 专题主图
     */
    private String pic;

    /**
     * 关联产品数量
     */
    @TableField("product_count")
    private Integer productCount;

    /**
     * 推荐
     */
    @TableField("recommend_status")
    private Integer recommendStatus;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 收藏量
     */
    @TableField("collect_count")
    private Integer collectCount;

    /**
     * 点击量
     */
    @TableField("read_count")
    private Integer readCount;

    /**
     * 评论量
     */
    @TableField("comment_count")
    private Integer commentCount;

    /**
     * 画册图片用逗号分割
     */
    @TableField("album_pics")
    private String albumPics;

    /**
     * 描述
     */
    private String description;

    /**
     * 显示状态：0->不显示；1->显示
     */
    @TableField("show_status")
    private Integer showStatus;

    /**
     * 内容
     */
    private String content;

    /**
     * 转发数
     */
    @TableField("forward_count")
    private Integer forwardCount;

    /**
     * 专题分类名称
     */
    @TableField("category_name")
    private String categoryName;

    @TableField("area_id")
    private Long areaId;

    @TableField("school_id")
    private Long schoolId;

    @TableField("member_id")
    private Long memberId;

    /**
     * 打赏
     */
    private Integer reward;

    /**
     * 1 學校 2 區域
     */
    @TableField(exist = false)
    private int qsType;

    @TableField("member_name")
    private String memberName;

    @TableField("area_name")
    private String areaName;
    @TableField("school_name")
    private String schoolName;

    @TableField("video_src")
    private String videoSrc;
    private Integer type;
    @TableField(exist = false)
    private List pics;
    @TableField(exist = false)
    List<SamplePmsProduct> products ;
    public List getPics() {
        if (ValidatorUtils.notEmpty(albumPics)){
            this.pics = Arrays.asList(albumPics.split(",,"));
        }
        return pics;
    }

    public void setPics(List pics) {
        if (ValidatorUtils.notEmpty(albumPics)){
            this.pics = Arrays.asList(albumPics.split(",,"));
        }
    }
}
