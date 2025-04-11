package com.ahmad.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pengguna {
    @Id
    private String id;
    private String nama;
    @JsonIgnore
    private String email;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String alamat;
    @JsonIgnore
    private String hp;
    @JsonIgnore
    private String roles; // "admin" or "pelanggan"
    @JsonIgnore
    private Boolean isAktif; 

    public Pengguna(String username) {
        this.id = username;
    }
}
