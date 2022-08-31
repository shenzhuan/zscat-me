package com.mallplus.common.entity.ums;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mallplus.common.entity.BaseEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.social.security.SocialUserDetails;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

/**
 * <p>
 * 会员表
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Data
@TableName("ums_member")
public class UmsMember extends BaseEntity implements Serializable,SocialUserDetails {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("member_level_id")
    private Long memberLevelId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 帐号启用状态:0->禁用；1->启用
     */
    private Integer status;

    @TableField("area_id")
    private Long areaId;
    @TableField("area_name")
    private String areaName;
    @TableField("school_name")
    private String schoolName;
    /**
     * 注册时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 头像
     */
    private String icon;

    /**
     * 性别：0->未知；1->男；2->女
     */
    private Integer gender;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 所做城市
     */
    private String city;

    /**
     * 职业
     */
    private String job;

    /**
     * 个性签名
     */
    @TableField("personalized_signature")
    private String personalizedSignature;

    /**
     * 用户来源 1 小程序 2 公众号 3 页面
     */
    @TableField("source_type")
    private Integer sourceType;

    /**
     * 积分
     */
    private Integer integration;

    /**
     * 成长值
     */
    private Integer growth;

    /**
     * 剩余抽奖次数
     */
    @TableField("luckey_count")
    private Integer luckeyCount;

    /**
     * 历史积分数量
     */
    @TableField("history_integration")
    private Integer historyIntegration;

    private String avatar;

    @TableField("weixin_openid")
    private String weixinOpenid;

    private Long invitecode;
    @TableField("buy_count")
    private Integer buyCount;
    @TableField("buy_money")
    private BigDecimal buyMoney;
    /**
     * 余额
     */
    private BigDecimal blance;
    @TableField(exist = false)
    private String confimpassword;

    @TableField(exist = false)
    private String phonecode;

    @TableField("school_id")
    private Long schoolId;


    @TableField(exist = false)
    private String code;

    @Override
    public String getUserId() {
        return this.getWeixinOpenid();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new HashSet<>();

        return collection;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
