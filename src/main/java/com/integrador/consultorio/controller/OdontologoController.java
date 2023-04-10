package com.integrador.consultorio.controller;

import com.integrador.consultorio.model.OdontologoDTO;
import com.integrador.consultorio.services.OdontologoServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Collection;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private final OdontologoServiceImp odontologoService;

    @Autowired
    public OdontologoController(OdontologoServiceImp odontologoService){
        this.odontologoService = odontologoService;
    }


    @PostMapping("/")
    public ResponseEntity<?> guardarOdontologo(@RequestBody OdontologoDTO odontologoDTO){

        odontologoService.guardarOdontologo(odontologoDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public OdontologoDTO buscarOdontologo(@PathVariable("id") long id){
       return odontologoService.buscarOdontologo(id);
    }

    @GetMapping("/")
    public Collection<OdontologoDTO> listarOdontologos(){
       return odontologoService.buscarTodos();
    }

    @PutMapping("/")
    public ResponseEntity<?> actualizarOdontologo(@RequestBody OdontologoDTO odontologoDTO){
         odontologoService.actualizarOdontologo(odontologoDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable("id") long id){
        odontologoService.buscarOdontologo(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
