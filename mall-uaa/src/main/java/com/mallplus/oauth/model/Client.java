package com.mallplus.oauth.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mallplus.common.model.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author mall
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("oauth_client_details")
public class Client extends SuperEntity {
   private static final long serialVersionUID = -8185413579135897885L;
   private String clientId;
   private String resourceIds = "";
   private String clientSecret;
   private String clientSecretStr;
   private String scope = "all";
   private String authorizedGrantTypes = "authorization_code,password,refresh_token,client_credentials";
   private String webServerRedirectUri;
   private String authorities = "";
   @TableField(value = "access_token_validity")
   private Integer accessTokenValiditySeconds = 18000;
   @TableField(value = "refresh_token_validity")
   private Integer refreshTokenValiditySeconds = 28800;
   private String additionalInformation = "{}";
   private String autoapprove = "true";
}
