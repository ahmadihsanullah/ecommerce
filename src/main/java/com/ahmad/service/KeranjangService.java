package com.ahmad.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahmad.entity.Keranjang;
import com.ahmad.entity.Pengguna;
import com.ahmad.entity.Produk;
import com.ahmad.exception.BadRequestException;
import com.ahmad.repository.KeranjangRepository;
import com.ahmad.repository.PenggunaRepository;
import com.ahmad.repository.ProdukRepository;

import jakarta.transaction.Transactional;

@Service
public class KeranjangService {

    @Autowired
    private KeranjangRepository keranjangRepository;

    @Autowired
    private ProdukRepository produkRepository;

    @Autowired
    private PenggunaRepository penggunaRepository;

    private Optional<Keranjang> byPenggunaIdAndProdukId;

    @Transactional
    public Keranjang addKeranjang(String username, String produkId, Double kuantitas) {
        // apakah produk ada
        // apakah sudah ada dalam keranjang
        // jika sudah ada, update kuantitas
        // jika belum ada, buat baru

        Produk produk = produkRepository.findById(produkId)
                .orElseThrow(() -> new BadRequestException("Produk tidak ditemukan"));

        Optional<Keranjang> optionalKeranjang = keranjangRepository.findByPenggunaIdAndProdukId(username, produkId);
        Keranjang keranjang;
        if (optionalKeranjang.isPresent()) {
            keranjang = optionalKeranjang.get();
            keranjang.setKuantitas(keranjang.getKuantitas() + kuantitas);
            keranjang.setJumlah(keranjang.getHarga().multiply(new BigDecimal(keranjang.getKuantitas())));
            keranjangRepository.save(keranjang);
        } else {
            keranjang = new Keranjang();
            keranjang.setId(UUID.randomUUID().toString());
            keranjang.setPengguna(new Pengguna(username));
            keranjang.setProduk(produk);
            keranjang.setKuantitas(kuantitas);
            keranjang.setHarga(produk.getHarga());
            keranjang.setJumlah(produk.getHarga().multiply(new BigDecimal(kuantitas)));
            keranjangRepository.save(keranjang);
        }
        return keranjang;
    }

    @Transactional
    public Keranjang updateKuantitas(String username, String produkId, Double kuantitas) {
        Keranjang keranjang = keranjangRepository.findByPenggunaIdAndProdukId(username, produkId)
                .orElseThrow(() -> new BadRequestException("Produk tidak ditemukan dalam keranjang anda"));
        keranjang.setKuantitas(kuantitas);
        keranjang.setJumlah(keranjang.getHarga().multiply(new BigDecimal(keranjang.getKuantitas())));
        keranjangRepository.save(keranjang);
        return keranjang;
    }

    @Transactional
    public void deleteKeranjang(String username, String produkId) {
        Keranjang keranjang = keranjangRepository.findByPenggunaIdAndProdukId(username, produkId)
                .orElseThrow(() -> new BadRequestException("Produk tidak ditemukan dalam keranjang anda"));
        keranjangRepository.delete(keranjang);
    }

    @Transactional
    public List<Keranjang> findByPenggunaId(String username) {
        return keranjangRepository.findByPenggunaId(username);
    }
}