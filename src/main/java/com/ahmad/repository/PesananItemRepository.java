package com.ahmad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahmad.entity.PesananItem;

public interface PesananItemRepository extends JpaRepository<PesananItem, String> {
    
}
