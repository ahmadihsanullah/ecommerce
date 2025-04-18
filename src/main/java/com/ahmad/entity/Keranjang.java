package com.ahmad.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
public class Keranjang implements Serializable{
    @Id
    private String id;
    @JoinColumn
    @ManyToOne
    private Pengguna pengguna;
    @JoinColumn
    @ManyToOne
    private Produk produk;
    private Double kuantitas;
    private BigDecimal harga;
    private BigDecimal jumlah;
    @Temporal(TemporalType.TIMESTAMP)
    private Date waktuDiBuat;
}
