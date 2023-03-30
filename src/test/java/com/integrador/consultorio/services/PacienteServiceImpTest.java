package com.integrador.consultorio.services;

import com.integrador.consultorio.entity.Domicilio;
import com.integrador.consultorio.entity.PacienteDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PacienteServiceImpTest {

    @Autowired
    private PacienteServiceImp pacienteServiceImp;

    @Test
    public void testGuardarPaciente(){
        PacienteDTO p1 = new PacienteDTO();
        Domicilio domicilio = new Domicilio("13",971,"Mar del Plata","Buenos Aires");

        p1.setNombre("Mauro");
        p1.setApellido("Lucero");
        p1.setDni("24455773");
        p1.setDomicilio(domicilio);
        p1.setFechaIngreso(LocalDate.of(1993,11,12));

        pacienteServiceImp.guardarPaciente(p1);

        assertTrue(pacienteServiceImp.buscarPaciente(1L) != null);
    }

    @Test
    public void testBorrarPaciente(){
        PacienteDTO p1 = new PacienteDTO();
        Domicilio domicilio = new Domicilio("13",971,"Mar del Plata","Buenos Aires");

        p1.setNombre("Mauro");
        p1.setApellido("Lucero");
        p1.setDni("24455773");
        p1.setDomicilio(domicilio);
        p1.setFechaIngreso(LocalDate.of(1993,11,12));

        pacienteServiceImp.guardarPaciente(p1);
        PacienteDTO pacienteBuscado = pacienteServiceImp.buscarPaciente(1L);
        pacienteServiceImp.borrarPaciente(pacienteBuscado.getId());

        assertTrue(pacienteServiceImp.buscarPaciente(1L) == null);
    }

    @Test
    public void testActualizarPaciente(){
        PacienteDTO p1 = new PacienteDTO();
        Domicilio domicilio = new Domicilio("13",971,"Mar del Plata","Buenos Aires");

        p1.setNombre("Mauro");
        p1.setApellido("Lucero");
        p1.setDni("24455773");
        p1.setDomicilio(domicilio);
        p1.setFechaIngreso(LocalDate.of(1993,11,12));

        pacienteServiceImp.guardarPaciente(p1);
        PacienteDTO pacienteGuardado = pacienteServiceImp.buscarPaciente(1L);
        pacienteGuardado.setNombre("Rafael");

        pacienteServiceImp.actualizarPaciente(pacienteGuardado);

        assertEquals("Rafael",pacienteServiceImp.buscarPaciente(1L).getNombre());
    }

    @Test
    public void testBuscarTodos(){
        PacienteDTO p1 = new PacienteDTO();
        PacienteDTO p2 = new PacienteDTO();
        Domicilio domicilio = new Domicilio("13",971,"Mar del Plata","Buenos Aires");
        Domicilio domicilio2 = new Domicilio("13",971,"Mar del Plata","Buenos Aires");

        p1.setNombre("Mauro");
        p1.setApellido("Lucero");
        p1.setDni("24455773");
        p1.setDomicilio(domicilio);
        p1.setFechaIngreso(LocalDate.of(1985,11,12));

        p2.setNombre("Agustin");
        p2.setApellido("Acosta");
        p2.setDni("35887768");
        p2.setDomicilio(domicilio2);
        p2.setFechaIngreso(LocalDate.of(1993,1,11));

        pacienteServiceImp.guardarPaciente(p1);
        pacienteServiceImp.guardarPaciente(p2);

        assertEquals(2,pacienteServiceImp.buscarTodos().size());
    }

}