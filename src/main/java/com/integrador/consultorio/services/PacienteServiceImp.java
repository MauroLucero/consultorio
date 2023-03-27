package com.integrador.consultorio.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.integrador.consultorio.entity.Domicilio;
import com.integrador.consultorio.entity.Paciente;
import com.integrador.consultorio.entity.PacienteDTO;
import com.integrador.consultorio.repository.IDomicilioRepository;
import com.integrador.consultorio.repository.IPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class PacienteServiceImp implements PacienteService{

    IPacienteRepository pacienteRepository;
    IDomicilioRepository domicilioRepository;

    @Autowired
    public PacienteServiceImp(IPacienteRepository pacienteRepository, IDomicilioRepository domicilioRepository) {
        this.pacienteRepository = pacienteRepository;
        this.domicilioRepository = domicilioRepository;
    }

    @Autowired
    ObjectMapper mapper;

    private void guardar(PacienteDTO pacienteDTO){
        Paciente paciente = mapper.convertValue(pacienteDTO,Paciente.class);
        this.domicilioRepository.save(paciente.getDomicilio());
        pacienteRepository.save(paciente);
    }
    @Override
    public void guardarPaciente(PacienteDTO pacienteDTO) {
        guardar(pacienteDTO);
    }

    @Override
    public void actualizarPaciente(PacienteDTO pacienteDTO) {
        guardar(pacienteDTO);
    }

    @Override
    public PacienteDTO buscarPaciente(Long id) {
        Paciente paciente = this.pacienteRepository.findById(id).orElse(null);
        PacienteDTO pacienteDTO= null;
        if(paciente!=null){
            pacienteDTO = mapper.convertValue(paciente,PacienteDTO.class);
        }
        return pacienteDTO;
    }

    @Override
    public Set<PacienteDTO> buscarTodos() {
        List<Paciente> pacientes= this.pacienteRepository.findAll();
        Set<PacienteDTO> pacientesDTO = new HashSet<>();
        for(Paciente paciente:pacientes){
            pacientesDTO.add(mapper.convertValue(paciente,PacienteDTO.class));
        }
        return pacientesDTO;
    }



    @Override
    public void borrarPaciente(Long id) {
        this.pacienteRepository.deleteById(id);
    }
}
