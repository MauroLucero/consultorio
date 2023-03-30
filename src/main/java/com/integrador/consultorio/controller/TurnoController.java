package com.integrador.consultorio.controller;


import com.integrador.consultorio.entity.PacienteDTO;
import com.integrador.consultorio.entity.TurnoDTO;
import com.integrador.consultorio.services.TurnoService;
import com.integrador.consultorio.services.TurnoServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    TurnoServiceImp turnoService;
    @Autowired
    public TurnoController(TurnoServiceImp turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping("/")
    public ResponseEntity<?> guardarTurno(@RequestBody TurnoDTO turnoDTO){
        turnoService.guardarTurno(turnoDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public TurnoDTO buscarTurno(@PathVariable("id") long id){
        return turnoService.buscarTurno(id);
    }

    @GetMapping("/")
    public Collection<TurnoDTO> listarTurnos(){
        return turnoService.buscarTodos();
    }

    @PutMapping("/")
    public ResponseEntity<?> actualizarTurno(@RequestBody TurnoDTO turnoDTO){
        turnoService.actualizarTurno(turnoDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTurno(@PathVariable("id") long id){
        turnoService.borrarTurno(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
