package com.ahmad.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahmad.entity.Kategori;
import com.ahmad.exception.ResourceNotFoundException;
import com.ahmad.repository.KategoryRepository;

@Service
public class KategoriService {
    
    @Autowired
    private KategoryRepository kategoryRepository;

    public Kategori findById(String id){
        return kategoryRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Kategori dengan id "+ id + " tidak ditemukan"));
    }
    public Iterable<Kategori> findAll(){
        return kategoryRepository.findAll();
    }

    public Kategori create(Kategori kategori){
        kategori.setId(UUID.randomUUID().toString());
        return kategoryRepository.save(kategori);
    }

    public Kategori edit(Kategori kategori){
        return kategoryRepository.save(kategori);
    }

    public void deleteById(String id){
        kategoryRepository.deleteById(id);
    }

}
