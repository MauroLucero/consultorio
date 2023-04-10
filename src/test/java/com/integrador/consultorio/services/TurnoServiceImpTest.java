package com.integrador.consultorio.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.integrador.consultorio.entity.Domicilio;
import com.integrador.consultorio.entity.Odontologo;
import com.integrador.consultorio.entity.Paciente;
import com.integrador.consultorio.model.OdontologoDTO;
import com.integrador.consultorio.model.PacienteDTO;
import com.integrador.consultorio.model.TurnoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TurnoServiceImpTest {

    @Autowired
    private PacienteServiceImp pacienteService;
    @Autowired
    private OdontologoServiceImp odontologoService;
    @Autowired
    private TurnoServiceImp turnoService;


    @Autowired
    ObjectMapper mapper;

    public void cargarDataSet() {
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setNombre("Cosme");
        pacienteDTO.setApellido("Fulanito");
        pacienteDTO.setDni("42000000");
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle("A");
        domicilio.setLocalidad("AA");
        domicilio.setNumero(1);
        domicilio.setProvincia("AAA");
        pacienteDTO.setDomicilio(domicilio);
        pacienteDTO.setFechaIngreso(LocalDate.now());

        OdontologoDTO odontologoDTO = new OdontologoDTO();
        odontologoDTO.setNombre("Cosme");
        odontologoDTO.setApellido("Fulanito");
        odontologoDTO.setMatricula(420);

        pacienteService.guardarPaciente(pacienteDTO);
        odontologoService.guardarOdontologo(odontologoDTO);
    }

    @Test
    public void altaTurnoTest(){
        this.cargarDataSet();

        TurnoDTO turnoDTO = new TurnoDTO();

        turnoDTO.setFecha(LocalDateTime.now());

        Odontologo o = mapper.convertValue(odontologoService.buscarOdontologo(1L), Odontologo.class);
        turnoDTO.setOdontologo(o);

        Paciente p = mapper.convertValue(pacienteService.buscarPaciente(1L), Paciente.class);
        turnoDTO.setPaciente(p);

        turnoService.guardarTurno(turnoDTO);

        assertNotNull(turnoService.buscarTurno(1L));
    }
    @Test
    public void buscarTurnoTest(){
        assertNotNull(turnoService.buscarTurno(1L));
    }
    @Test
    public void eliminarTurnoTest(){
        turnoService.buscarTurno(1L);
        assertNull(turnoService.buscarTurno(1L));
    }

}