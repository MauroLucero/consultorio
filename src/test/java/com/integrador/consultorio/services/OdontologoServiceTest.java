package com.integrador.consultorio.services;


import com.integrador.consultorio.model.OdontologoDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class OdontologoServiceTest {

    @Autowired
    private OdontologoServiceImp odontologoService;

    @Test
    public void testCrearPaciente(){

        OdontologoDTO odontologoDTO = new OdontologoDTO();
        odontologoDTO.setNombre("Cosme");
        odontologoDTO.setApellido("Fulanito");
        odontologoDTO.setMatricula(420);

        odontologoService.guardarOdontologo(odontologoDTO);

        OdontologoDTO pacienteCosme = odontologoService.buscarOdontologo(1L);

        assertEquals("Cosme",pacienteCosme.getNombre());
    }

    @Test
    public void testEliminarPaciente(){

        OdontologoDTO odontologoDTO = new OdontologoDTO();
        odontologoDTO.setNombre("Cosme");
        odontologoDTO.setApellido("Fulanito");
        odontologoDTO.setMatricula(420);

        odontologoService.guardarOdontologo(odontologoDTO);

        odontologoService.borrarOdontologo(2L);

        OdontologoDTO odontologoCosme = odontologoService.buscarOdontologo(2L);

        assertTrue(odontologoCosme == null);
    }

    @Test
    public void testMostrarTodos(){

        OdontologoDTO odontologoDTO = new OdontologoDTO();
        odontologoDTO.setNombre("Cosma");
        odontologoDTO.setApellido("Fulanita");
        odontologoDTO.setMatricula(421);

        OdontologoDTO odontologoDTO2 = new OdontologoDTO();
        odontologoDTO2.setNombre("Cosmo");
        odontologoDTO2.setApellido("Fulano");
        odontologoDTO2.setMatricula(422);

        odontologoService.guardarOdontologo(odontologoDTO);
        odontologoService.guardarOdontologo(odontologoDTO2);


        Set<OdontologoDTO> odontologos = odontologoService.buscarTodos();
        assertTrue(!odontologos.isEmpty());
        assertEquals(3, odontologos.size());
    }
}