package com.integrador.consultorio.services;

import com.integrador.consultorio.model.TurnoDTO;

import java.util.Set;

public interface TurnoService {
    void guardarTurno(TurnoDTO turnoDTO);
    TurnoDTO buscarTurno(Long id);
    Set<TurnoDTO> buscarTodos();
    void actualizarTurno(TurnoDTO turnoDTO);

    void borrarTurno(Long id);
}
