package com.integrador.consultorio.entity.security;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table(name="roles")
@Builder
public class Rol {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String descripcion;

    @OneToOne(mappedBy = "rol")
    private Usuario usuario;

    public Rol() {
    }


    public Rol(Long id, String descripcion, Usuario usuario) {
        this.id = id;
        this.descripcion = descripcion;
        this.usuario = usuario;
    }

    public Rol(String descripcion) {
        this.descripcion = descripcion;
    }

    public Rol(Long id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
