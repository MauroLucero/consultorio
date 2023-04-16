package com.integrador.consultorio.services;

import com.integrador.consultorio.model.OdontologoDTO;

import java.util.Set;

public interface OdontologoService {
    void guardarOdontologo(OdontologoDTO odontologoDTO);

    OdontologoDTO buscarOdontologo(Long id);

    Set<OdontologoDTO> buscarTodos();

    void actualizarOdontologo(OdontologoDTO odontologoDTO);

    void borrarOdontologo(Long id);
}

