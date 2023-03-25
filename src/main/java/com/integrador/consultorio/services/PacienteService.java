package com.integrador.consultorio.services;

import com.integrador.consultorio.entity.Paciente;

import java.util.List;

public interface PacienteService {

    Paciente guardar(Paciente paciente);
    Paciente buscar(Long id);
    List<Paciente> buscarTodos();
    Paciente actualizar(Paciente paciente);

    void borrar(Long id);
}
