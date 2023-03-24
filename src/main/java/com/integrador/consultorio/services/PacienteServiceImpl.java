package com.integrador.consultorio.services;

import com.integrador.consultorio.entity.Paciente;
import com.integrador.consultorio.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PacienteServiceImpl implements PacienteService{

    PacienteRepository pacienteRepository;



    @Autowired
    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Paciente guardar(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    public Paciente buscar(Long id) {
        return pacienteRepository.findById(id).orElse(null);
    }

    @Override
    public List<Paciente> buscarTodos() {
        return pacienteRepository.findAll();
    }

    @Override
    public Paciente actualizar(Paciente paciente) {
       return this.pacienteRepository.save(paciente);
    }

    @Override
    public void borrar(Long id) {
        this.pacienteRepository.delete(buscar(id));
    }
}
