package com.ahmad.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ahmad.dto.ResponseData;
import com.ahmad.entity.Kategori;
import com.ahmad.service.KategoriService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class KategoriController {
    
    @Autowired
    private KategoriService kategoriService;
    
    @GetMapping("/kategoris")
    public Iterable<Kategori> findAll() {
        return kategoriService.findAll();
    }

    @GetMapping("/kategoris/{id}")
    public Kategori findById(@PathVariable("id") String id) {
        return kategoriService.findById(id);
    }
    
    @PostMapping("/kategoris")
    public ResponseEntity<ResponseData<Kategori>> create(@Valid @RequestBody Kategori kategori, Errors errors) {
        ResponseData<Kategori> response = new ResponseData<>();
        if (errors.hasErrors()) {
            for(ObjectError error: errors.getAllErrors()){
                response.getMessage().add(error.getDefaultMessage());
            }
            response.setStatus(false);
            response.setPayload(null);
            return ResponseEntity.badRequest().body(response);
        }
        try {
            kategoriService.create(kategori);
            response.setStatus(true);
            response.setMessage(List.of("Berhasil menambah kategori"));
            response.setPayload(kategori);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setStatus(false);
            response.setMessage(List.of("Gagal menambah kategori"));
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @PutMapping("/kategoris")
    public Kategori edit(@RequestBody Kategori kategori) {
        return kategoriService.edit(kategori);
    }
    
    @DeleteMapping("/kategoris/{id}")
    public void deleteById(@PathVariable("id") String id) {
        kategoriService.deleteById(id);
    }
   
}
