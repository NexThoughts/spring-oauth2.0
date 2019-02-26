package com.buzzbuilder.buzzbuilderrest.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * @author yuit
 * @create time 2018/10/9  15:43
 * @description
 * @modify by
 * @modify time
 **/


@Data
@TableName("user")
public class User implements UserDetails {
    @TableId
    private String id;
    private String username;
    private String emailAddress;
    @TableField("enabled")
    private Boolean enabled;
    @TableField("accountExpired")
    private Boolean accountExpired;
    @TableField("accountLocked")
    private Boolean accountLocked;
    private String password;

    @TableField(exist = false)
    private List<GrantedAuthority> authorities;

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public List<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
        return this.enabled;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}
