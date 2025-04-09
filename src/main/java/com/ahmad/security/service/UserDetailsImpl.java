package com.ahmad.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import com.ahmad.entity.Pengguna;

import lombok.Data;

@Data
public class UserDetailsImpl implements UserDetails{
    
    private String username;
    private String email;
    private String nama;
    private String password;
    private String roles;


    public UserDetailsImpl() {
    }

    public UserDetailsImpl(String username, String email, String nama, String password, String roles) {
        this.username = username;
        this.email = email;
        this.nama = nama;
        this.password = password;
        this.roles = roles;
    }

    public static UserDetailsImpl build(Pengguna pengguna) {
        return new UserDetailsImpl(pengguna.getId(),
                                    pengguna.getEmail(), 
                                    pengguna.getNama(), 
                                    pengguna.getPassword(), 
                                    pengguna.getRoles());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(StringUtils.hasText(roles)) {
            String[] roleArray = roles.replaceAll(" ", "").split(",");
            for (String role : roleArray) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

}
