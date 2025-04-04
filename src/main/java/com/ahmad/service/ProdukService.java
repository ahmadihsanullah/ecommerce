package com.ahmad.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahmad.entity.Produk;
import com.ahmad.repository.ProdukRepository;

@Service
public class ProdukService {
    
    @Autowired
    private ProdukRepository produkRepository;

    public Iterable<Produk> findAll(){
        return produkRepository.findAll();
    }

    public Produk findById(String id){
        return produkRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("Produk dengan id "+ id + " tidak ditemukan"));
    }

    public Produk create(Produk produk){
        produk.setId(UUID.randomUUID().toString());
        return produkRepository.save(produk);
    }

    public Produk edit(Produk produk){
        return produkRepository.save(produk);
    }

    public Produk ubahGambar(String id, String gambar){
        Produk produk = findById(id);
        produk.setGambar(gambar);
        return produkRepository.save(produk);
    }

    public void deleteById(String id){
        produkRepository.deleteById(id);
    }


    
}
