package com.ahmad.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ahmad.entity.Pengguna;
import com.ahmad.repository.PenggunaRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PenggunaRepository penggunaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        Pengguna pengguna = penggunaRepository.findById(username)
            .orElseThrow(()-> new UsernameNotFoundException("User not found"));
        return  UserDetailsImpl.build(pengguna);

    }
    
}
