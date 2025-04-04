package com.ahmad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahmad.entity.Pengguna;

public interface PenggunaRepository extends JpaRepository<Pengguna, String> {
    
}
