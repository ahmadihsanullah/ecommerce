package com.ahmad.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Pengguna {
    @Id
    private String id;
    private String nama;
    private String email;
    private String password;
    private String alamat;
    private String hp;
    private String roles; // "admin" or "pelanggan"
    private String isAktif; // "aktif" or "nonaktif"
}
