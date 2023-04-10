package com.integrador.consultorio.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "Turnos")
public class Turno {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name="paciente_id", nullable = false)
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name="odontologo_id", nullable = false)
    private Odontologo odontologo;
    private LocalDateTime fecha;

    public Turno() {
    }

    public Turno(Paciente paciente, Odontologo odontologo, LocalDateTime fecha) {
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fecha = fecha;
    }

    public Turno(Long id, Paciente paciente, Odontologo odontologo, LocalDateTime fecha) {
        this.id = id;
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }


}
