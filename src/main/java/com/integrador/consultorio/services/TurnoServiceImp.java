package com.integrador.consultorio.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.integrador.consultorio.entity.Odontologo;
import com.integrador.consultorio.entity.OdontologoDTO;
import com.integrador.consultorio.entity.Turno;
import com.integrador.consultorio.entity.TurnoDTO;
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
    private final PacienteService pacienteService;
    private final OdontologoService odontologoService;
    @Autowired
    public TurnoServiceImp(ITurnoRepository turnoRepository, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoRepository = turnoRepository;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }




    @Autowired
    ObjectMapper mapper;

    private void guardar(TurnoDTO turnoDTO){
        if(pacienteService.buscarPaciente(turnoDTO.getPaciente().getId())!=null && odontologoService.buscarOdontologo(turnoDTO.getOdontologo().getId())!=null){
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
