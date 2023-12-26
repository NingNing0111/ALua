package com.ning.aluaback.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ning.aluaback.handler.type.ListToVarcharTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Project: com.ning.aluaback.entity
 * @Author: pgthinker
 * @Date: 2023/12/26 15:24
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value="user",autoResultMap = true)
public class User implements UserDetails {
    @TableId(value = "u_id", type = IdType.AUTO)
    private Integer id;
    @TableField(value = "u_email")
    private String username;
    @TableField(value = "u_username")
    private String nickname;
    @TableField(value = "u_password")
    private String password;
    @TableField(value = "u_roles",typeHandler = ListToVarcharTypeHandler.class)
    private List<String> roles;
    @TableField(value = "u_enable")
    private Boolean isEnabled;
    @TableField(value = "u_balance")
    private Integer balance;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        for (String role: roles){
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role));
        }
        return simpleGrantedAuthorities;
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
        return isEnabled;
    }
}
