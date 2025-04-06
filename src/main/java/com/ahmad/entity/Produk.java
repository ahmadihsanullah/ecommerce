package com.ahmad.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Produk {
    @Id
    private String id;
    @Column(nullable = false, length = 50)
    @Size(min = 3, max = 50, message = "Nama produk harus diantara {min} dan {max} karakter")
    private String nama;
    @Column(nullable = true, length = 255)
    private String deskripsi;
    private String gambar;

    @JoinColumn
    @ManyToOne
    private Kategori kategori;

    private BigDecimal harga;
    private Double stok;
}
