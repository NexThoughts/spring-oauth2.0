package com.buzzbuilder.buzzbuilderrest.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
@TableName("clients")
public class Client  {

    @TableId
    private String id;String getId() {
        return id
    }

    void setId(String id) {
        this.id = id
    }

    String getClientId() {
        return clientId
    }

    void setClientId(String clientId) {
        this.clientId = clientId
    }

    String getResourceIds() {
        return resourceIds
    }

    void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds
    }

    Boolean getIsSecretRequired() {
        return isSecretRequired
    }

    void setIsSecretRequired(Boolean isSecretRequired) {
        this.isSecretRequired = isSecretRequired
    }

    String getClientSecret() {
        return clientSecret
    }

    void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret
    }

    Boolean getIsScoped() {
        return isScoped
    }

    void setIsScoped(Boolean isScoped) {
        this.isScoped = isScoped
    }

    String getScope() {
        return scope
    }

    void setScope(String scope) {
        this.scope = scope
    }

    String getAuthorizedGrantTypes() {
        return authorizedGrantTypes
    }

    void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes
    }

    String getRegisteredRedirectUri() {
        return registeredRedirectUri
    }

    void setRegisteredRedirectUri(String registeredRedirectUri) {
        this.registeredRedirectUri = registeredRedirectUri
    }

    String getAuthorities() {
        return authorities
    }

    void setAuthorities(String authorities) {
        this.authorities = authorities
    }

    Boolean getIsAutoApprove() {
        return isAutoApprove
    }

    void setIsAutoApprove(Boolean isAutoApprove) {
        this.isAutoApprove = isAutoApprove
    }

    Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds
    }

    void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds
    }

    Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds
    }

    void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds
    }
    @TableField("clientId")
    @NotNull
    private String clientId;
    @TableField("resourceIds")
    private String resourceIds;
    @TableField("isSecretRequired")
    private Boolean isSecretRequired;
    @TableField("clientSecret")
    @NotNull
    private String clientSecret;
    @TableField("isScoped")
    private Boolean isScoped;
    @TableField("scope")
    private String scope;
    @TableField("authorizedGrantTypes")
    @NotNull
    private String authorizedGrantTypes;
    @TableField("registeredRedirectUri")
    @NotNull
    private String registeredRedirectUri;
    @TableField("authorities")
    private String authorities;
    @TableField("isAutoApprove")
    private Boolean isAutoApprove;
    @TableField("accessTokenValiditySeconds")
    @NotNull
    private Integer accessTokenValiditySeconds;
    @TableField("refreshTokenValiditySeconds")
    @NotNull
    private Integer refreshTokenValiditySeconds;

}
