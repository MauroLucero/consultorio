package com.integrador.consultorio.services;

import com.integrador.consultorio.entity.Odontologo;
import com.integrador.consultorio.entity.Paciente;

import java.util.List;

public interface OdontologoService {
    Odontologo guardar(Odontologo odontologo);
    Odontologo buscar(Long id);
    List<Odontologo> buscarTodos();
    Odontologo actualizar(Odontologo odontologo);
    void borrar(Long id);
}
