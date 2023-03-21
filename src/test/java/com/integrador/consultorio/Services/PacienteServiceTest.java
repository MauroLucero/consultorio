package com.integrador.consultorio.Services;

import com.integrador.consultorio.Repository.impl.PacienteIDAOH2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PacienteServiceTest {

    @Test
    public void probandoListarTodos(){
        PacienteService pacienteService = new PacienteService();
        pacienteService.setPacienteService(new PacienteIDAOH2());

        assertEquals(2,pacienteService.listarTodos().size());
    }

}