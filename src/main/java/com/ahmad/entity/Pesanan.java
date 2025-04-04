package com.ahmad.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.ahmad.model.StatusPesanan;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
public class Pesanan {
    
    @Id
    private String id;
    private String nomor;
    @Temporal(TemporalType.DATE)
    private Date tanggal;

    @JoinColumn
    @ManyToOne
    private Pengguna penggunaId;

    private String alamatPengiriman;
    private BigDecimal jumlah;
    private BigDecimal total;
    private BigDecimal ongkir;

    @Enumerated(EnumType.STRING)
    private StatusPesanan statusPesanan;
    @Temporal(TemporalType.TIMESTAMP)
    private Date waktuPesan; 
}
