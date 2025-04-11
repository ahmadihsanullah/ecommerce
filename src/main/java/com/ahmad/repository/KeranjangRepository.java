package com.ahmad.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahmad.entity.Keranjang;

public interface KeranjangRepository extends JpaRepository<Keranjang, String> {
    
    Optional<Keranjang> findByPenggunaIdAndProdukId(String penggunaId, String produkId);

    List<Keranjang> findByPenggunaId(String username);
    
}
