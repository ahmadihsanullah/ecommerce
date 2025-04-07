package com.ahmad.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ahmad.entity.Pengguna;
import com.ahmad.exception.BadRequestException;
import com.ahmad.exception.ResourceNotFoundException;
import com.ahmad.repository.PenggunaRepository;

@Service
public class PenggunaService {
    
    @Autowired
    private PenggunaRepository penggunaRepository;

    public Pengguna findById(String id){
        return penggunaRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Pengguna dengan id "+ id + " tidak ditemukan"));
    }
    public Iterable<Pengguna> findAll(){
        return penggunaRepository.findAll();
    }

    public Pengguna create(Pengguna pengguna){
        if(!StringUtils.hasText(pengguna.getId())){
            throw new BadRequestException("username harus diisi");
        }
        if(penggunaRepository.existsById(pengguna.getId())){
            throw new BadRequestException("username sudah terdaftar");
        }
        if(!StringUtils.hasText(pengguna.getEmail())){
            throw new BadRequestException("email harus diisi");
        }
        if(penggunaRepository.existsByEmail(pengguna.getEmail())){
            throw new BadRequestException("email sudah terdaftar");
        }
        pengguna.setIsAktif(true);
        return penggunaRepository.save(pengguna);
    }

    public Pengguna edit(Pengguna pengguna){
        return penggunaRepository.save(pengguna);
    }

    public void deleteById(String id){
        penggunaRepository.deleteById(id);
    }

}
