package com.integrador.consultorio.services;

import com.integrador.consultorio.model.PacienteDTO;

import java.util.Set;

public interface PacienteService {

    void guardarPaciente(PacienteDTO pacienteDTO);
    PacienteDTO buscarPaciente(Long id);
    Set<PacienteDTO> buscarTodos();
    void actualizarPaciente(PacienteDTO pacienteDTO);

    void borrarPaciente(Long id);
}
