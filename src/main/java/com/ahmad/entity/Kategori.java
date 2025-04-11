package com.ahmad.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Kategori {

    @Id
    private String id;

    @Column(nullable = false, unique = true, length = 50)
    @NotBlank(message = "Nama kategori tidak boleh kosong")
    @Size(min = 3, max = 50, message = "Nama kategori harus diantara {min} dan {max} karakter")
    private String nama;
}
