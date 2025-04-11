package com.ahmad.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ahmad.entity.Keranjang;
import com.ahmad.model.KeranjangRequest;
import com.ahmad.service.KeranjangService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api")
@PreAuthorize("isAuthenticated()")
public class KeranjangController {

    @Autowired
    private KeranjangService keranjangService;

    @GetMapping("/keranjangs")
    public List<Keranjang> findByPenggunaId(@AuthenticationPrincipal UserDetails user) {
        return keranjangService.findByPenggunaId(user.getUsername());
    }

    @PostMapping("/keranjangs")
    public Keranjang creatKeranjang(@AuthenticationPrincipal UserDetails user, @RequestBody KeranjangRequest keranjangRequest) {
        return keranjangService.addKeranjang(user.getUsername(), keranjangRequest.getProdukId(), keranjangRequest.getKuantitas());   
    }
    
    @PatchMapping("/keranjangs/{produkId}")
    public Keranjang updateKuantitas(@AuthenticationPrincipal UserDetails user, @PathVariable("produkId") String produkId, @RequestParam Double kuantitas) {
        return keranjangService.updateKuantitas(user.getUsername(), produkId, kuantitas);
    }

    @DeleteMapping("/keranjangs/{produkId}")
    public void deleteKeranjang(@AuthenticationPrincipal UserDetails user, @PathVariable("produkId") String produkId) {
        keranjangService.deleteKeranjang(user.getUsername(), produkId);
    }
    
}
