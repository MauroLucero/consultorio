package com.integrador.consultorio.controller;

import com.integrador.consultorio.entity.Odontologo;
import com.integrador.consultorio.services.OdontologoServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private final OdontologoServiceImp odontologoService;

    @Autowired
    public OdontologoController(OdontologoServiceImp odontologoService){
        this.odontologoService = odontologoService;
    }


    @PostMapping("/")
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odontologo){
        ResponseEntity response;
        if (odontologoService.guardar(odontologo)!=null){
            response = ResponseEntity.status(HttpStatus.OK).build();
        }
        else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologo(@PathVariable("id") long id){
        ResponseEntity response;
        Odontologo odontologo = odontologoService.buscar(id);
        if(odontologo != null){
            response = ResponseEntity.ok(odontologo);
        }
        else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @GetMapping("/")
    public ResponseEntity<List<Odontologo>> listarOdontologos(){
        ResponseEntity response;
        List<Odontologo> listaOdontologos = odontologoService.buscarTodos();
        if(listaOdontologos.size() != 0){
            response = ResponseEntity.ok(listaOdontologos);
        }
        else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @PutMapping("/")
    public ResponseEntity<Odontologo> actualizarOdontologo(@RequestBody Odontologo odontologo){
        ResponseEntity response;
        if(odontologoService.buscar(odontologo.getId()) != null){
            odontologoService.actualizar(odontologo);
            response = ResponseEntity.status(HttpStatus.OK).build();
        }
        else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Odontologo> eliminarOdontologo(@PathVariable("id") long id){
        ResponseEntity response;
        if(odontologoService.buscar(id) != null){
            odontologoService.borrar(id);
            response = ResponseEntity.status(HttpStatus.OK).build();
        }
        else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }
}
