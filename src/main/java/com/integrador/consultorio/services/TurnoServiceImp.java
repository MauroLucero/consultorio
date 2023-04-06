package com.integrador.consultorio.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.integrador.consultorio.entity.*;
import com.integrador.consultorio.model.TurnoDTO;
import com.integrador.consultorio.repository.IOdontologoRepository;
import com.integrador.consultorio.repository.IPacienteRepository;
import com.integrador.consultorio.repository.ITurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
public class TurnoServiceImp implements TurnoService{

    private final ITurnoRepository turnoRepository;
    private final IPacienteRepository pacienteRepository;
    private final IOdontologoRepository odontologoRepository;
    @Autowired
    public TurnoServiceImp(ITurnoRepository turnoRepository, IPacienteRepository pacienteRepository, IOdontologoRepository odontologoRepository) {
        this.turnoRepository = turnoRepository;
        this.pacienteRepository = pacienteRepository;
        this.odontologoRepository = odontologoRepository;
    }




    @Autowired
    ObjectMapper mapper;


    /*Conversion DTO*/

//    private TurnoDTO turnoATurnoDto(Turno turno){
//        TurnoDTO turnoDTO = new TurnoDTO();
//        turnoDTO.setId(turno.getId());
//        turnoDTO.setFecha(turno.getFecha());
//
//        turnoDTO.setPaciente(new Paciente());
//        turnoDTO.getPaciente().setId(turno.getPaciente().getId());
//
//        turnoDTO.setOdontologo(new Odontologo());
//        turnoDTO.getOdontologo().setId(turno.getOdontologo().getId());
//
//        return turnoDTO;
//    }
//
//    private Turno turnoDtoATurno(TurnoDTO turnoDTO){
//        Turno turno = new Turno();
//        turno.setId(turnoDTO.getId());
//        turno.setFecha(turnoDTO.getFecha());
//
//        turno.setPaciente(pacienteRepository.findById(turnoDTO.getPaciente().getId()).orElse(null));
//        turno.setOdontologo(odontologoRepository.findById(turnoDTO.getOdontologo().getId()).orElse(null));
//
//        return turno;
//    }



    private void guardar(TurnoDTO turnoDTO){
        if(!pacienteRepository.findById(turnoDTO.getPaciente().getId()).isEmpty() && !odontologoRepository.findById(turnoDTO.getOdontologo().getId()).isEmpty()){
        Turno turno = mapper.convertValue(turnoDTO,Turno.class);
        turnoRepository.save(turno);
        }
    }
    @Override
    public void guardarTurno(TurnoDTO turnoDTO) {
        guardar(turnoDTO);
    }

    @Override
    public void actualizarTurno(TurnoDTO turnoDTO) {
        guardar(turnoDTO);
    }

    @Override
    public TurnoDTO buscarTurno(Long id) {
        Turno turno = this.turnoRepository.findById(id).orElse(null);
        TurnoDTO turnoDTO= null;
        if(turno!=null){
            turnoDTO = mapper.convertValue(turno,TurnoDTO.class);
        }
        return turnoDTO;
    }

    @Override
    public Set<TurnoDTO> buscarTodos() {
        List<Turno> turnos= this.turnoRepository.findAll();
        Set<TurnoDTO> turnosDTO = new HashSet<>();
        for(Turno turno:turnos){
            turnosDTO.add(mapper.convertValue(turno,TurnoDTO.class));
        }
        return turnosDTO;
    }



    @Override
    public void borrarTurno(Long id) {
        this.turnoRepository.deleteById(id);
    }
}
