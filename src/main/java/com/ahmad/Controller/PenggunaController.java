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
import com.ahmad.entity.Pengguna;
import com.ahmad.service.PenggunaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class PenggunaController {
    
    @Autowired
    private PenggunaService penggunaService;
    
    @GetMapping("/penggunas")
    public Iterable<Pengguna> findAll() {
        return penggunaService.findAll();
    }

    @GetMapping("/penggunas/{id}")
    public Pengguna findById(@PathVariable("id") String id) {
        return penggunaService.findById(id);
    }
    
    @PostMapping("/penggunas")
    public ResponseEntity<ResponseData<Pengguna>> create(@Valid @RequestBody Pengguna pengguna, Errors errors) {
        ResponseData<Pengguna> response = new ResponseData<>();
        if (errors.hasErrors()) {
            for(ObjectError error: errors.getAllErrors()){
                response.getMessage().add(error.getDefaultMessage());
            }
            response.setStatus(false);
            response.setPayload(null);
            return ResponseEntity.badRequest().body(response);
        }
        try {
            penggunaService.create(pengguna);
            response.setStatus(true);
            response.setMessage(List.of("Berhasil menambah pengguna"));
            response.setPayload(pengguna);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setStatus(false);
            response.getMessage().add(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @PutMapping("/penggunas")
    public Pengguna edit(@RequestBody Pengguna pengguna) {
        return penggunaService.edit(pengguna);
    }
    
    @DeleteMapping("/penggunas/{id}")
    public void deleteById(@PathVariable("id") String id) {
        penggunaService.deleteById(id);
    }
   
}
