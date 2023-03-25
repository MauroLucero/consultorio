package com.integrador.consultorio.services;

import com.integrador.consultorio.entity.Paciente;
import com.integrador.consultorio.repository.DomicilioRepository;
import com.integrador.consultorio.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PacienteServiceImp implements PacienteService{

    PacienteRepository pacienteRepository;
    DomicilioRepository domicilioRepository;

    @Autowired
    public PacienteServiceImp(PacienteRepository pacienteRepository, DomicilioRepository domicilioRepository) {
        this.pacienteRepository = pacienteRepository;
        this.domicilioRepository = domicilioRepository;
    }


    @Override
    public Paciente guardar(Paciente paciente) {
        this.domicilioRepository.save(paciente.getDomicilio());
        return this.pacienteRepository.save(paciente);
    }

    @Override
    public Paciente buscar(Long id) {
        return this.pacienteRepository.findById(id).orElse(null);
    }

    @Override
    public List<Paciente> buscarTodos() {
        return this.pacienteRepository.findAll();
    }

    @Override
    public Paciente actualizar(Paciente paciente) {
        this.domicilioRepository.save(paciente.getDomicilio());
        return this.pacienteRepository.save(paciente);
    }

    @Override
    public void borrar(Long id) {
        this.domicilioRepository.delete(buscar(id).getDomicilio());
        this.pacienteRepository.delete(buscar(id));
    }
}
