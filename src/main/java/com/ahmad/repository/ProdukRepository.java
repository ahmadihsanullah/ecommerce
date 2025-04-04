package com.ahmad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahmad.entity.Produk;

public interface ProdukRepository extends JpaRepository<Produk, String> {
}
