package com.ahmad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahmad.entity.Kategori;

public interface KategoryRepository extends JpaRepository<Kategori, String> {
    
}
