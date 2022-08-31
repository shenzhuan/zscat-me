package com.mallplus.common.entity.ums;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import com.mallplus.common.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author zscat
 * @since 2019-06-15
 */
@Data
@TableName("sys_applet_set")
public class SysAppletSet extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String appid;

    private String appsecret;

    private String mchid;

    @TableField("pay_sign_key")
    private String paySignKey;

    private String certname;

    private String notifyurl;

    /**
     * 下单通知模版
     */
    private String templateid1;

    /**
     * 支付成功模版
     */
    private String templateid2;

    private String templateid3;

    private String templateid4;

    private String templateid5;

    private String templateid6;


}
