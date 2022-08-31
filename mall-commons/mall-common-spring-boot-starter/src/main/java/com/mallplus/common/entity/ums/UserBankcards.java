package com.mallplus.common.entity.ums;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author zscat
 * @since 2019-09-16
 */
@Data
@TableName("user_bankcards")
public class UserBankcards implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 银行名称
     */
    @TableField("bank_name")
    private String bankName;

    /**
     * 银行缩写
     */
    @TableField("bank_code")
    private String bankCode;

    /**
     * 账号地区ID
     */
    @TableField("bank_area_id")
    private Integer bankAreaId;

    /**
     * 开户行
     */
    @TableField("account_bank")
    private String accountBank;

    /**
     * 账户名
     */
    @TableField("account_name")
    private String accountName;

    /**
     * 卡号
     */
    @TableField("card_number")
    private String cardNumber;

    /**
     * 银行卡类型: 1=储蓄卡 2=信用卡
     */
    @TableField("card_type")
    private Boolean cardType;

    /**
     * 默认卡 1=默认 2=不默认
     */
    @TableField("is_default")
    private Integer isDefault;

    /**
     * 创建时间
     */
    private Long ctime;

    /**
     * 更新时间
     */
    private Long utime;


}
