package com.ahmad.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.ahmad.entity.Produk;
import com.ahmad.exception.BadRequestException;
import com.ahmad.repository.KategoryRepository;
import com.ahmad.service.ProdukService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@PreAuthorize("isAuthenticated()")
public class ProdukController {
    

    @Autowired
    private KategoryRepository kategoriRepository;

    @Autowired
    private ProdukService produkService;
    
    @GetMapping("/produks")
    public Iterable<Produk> findAll() {
        return produkService.findAll();
    }

    @GetMapping("/produks/{id}")
    public Produk findById(@PathVariable("id") String id) {
        return produkService.findById(id);
    }
    
    @PostMapping("/produks")
    public ResponseEntity<ResponseData<Produk>> create(@Valid @RequestBody Produk produk, Errors errors) {
        ResponseData<Produk> response = new ResponseData<>();
        if (errors.hasErrors()) {
            for(ObjectError error: errors.getAllErrors()){
                response.getMessage().add(error.getDefaultMessage());
            }
            response.setStatus(false);
            response.setPayload(null);
            return ResponseEntity.badRequest().body(response);
        }
        if (produk.getKategori() == null) {
            response.setStatus(false);
            response.setMessage(List.of("Kategori tidak boleh kosong"));
            return ResponseEntity.badRequest().body(response);
        }
        try {
            Kategori kategori = kategoriRepository.findById(produk.getKategori().getId())
            .orElseThrow(() -> new BadRequestException("Kategori tidak ditemukan")); // Handle jika kategori tidak ditemukan
            produk.setKategori(kategori);
            produkService.create(produk);
            response.setStatus(true);
            response.setMessage(List.of("Berhasil menambah produk"));
            response.setPayload(produk);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setStatus(false);
            response.setMessage(List.of("Gagal menambah produk"));
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @PutMapping("/produks")
    public ResponseEntity<ResponseData<Produk>> edit(@RequestBody Produk produk) {
        ResponseData<Produk> response = new ResponseData<>();
    
        if (produk.getKategori() == null) {
            response.setStatus(false);
            response.setMessage(List.of("Kategori tidak boleh kosong"));
            return ResponseEntity.badRequest().body(response);
        }
    
        try {
            Kategori kategori = kategoriRepository.findById(produk.getKategori().getId())
                    .orElseThrow(() -> new BadRequestException("Kategori tidak ditemukan"));
            produk.setKategori(kategori);
            Produk updatedProduk = produkService.edit(produk);
    
            response.setStatus(true);
            response.setMessage(List.of("Berhasil mengedit produk"));
            response.setPayload(updatedProduk);
            return ResponseEntity.ok(response);
        } catch (BadRequestException e) {
            response.setStatus(false);
            response.setMessage(List.of(e.getMessage())); // Menggunakan pesan dari BadRequestException
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            response.setStatus(false);
            response.setMessage(List.of("Gagal mengedit produk: " + e.getMessage())); // Menambahkan pesan exception
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @DeleteMapping("/produks/{id}")
    public void deleteById(@PathVariable("id") String id) {
        produkService.deleteById(id);
    }
   
}
