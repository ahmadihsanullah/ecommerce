package com.ahmad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahmad.entity.Pesanan;

public interface PesananRepository extends JpaRepository<Pesanan, String> {
    
}
