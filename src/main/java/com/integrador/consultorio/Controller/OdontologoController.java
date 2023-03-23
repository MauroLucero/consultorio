package com.integrador.consultorio.Controller;


import com.integrador.consultorio.Model.Paciente;
import com.integrador.consultorio.Repository.impl.OdontologoIDAOH2;
import com.integrador.consultorio.Model.Odontologo;
import com.integrador.consultorio.Services.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private final OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService){
        this.odontologoService = odontologoService;
        odontologoService.setOdontologoService(new OdontologoIDAOH2());
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
        List<Odontologo> listaOdontologos = odontologoService.listarTodos();
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
            odontologoService.eliminar(id);
            response = ResponseEntity.status(HttpStatus.OK).build();
        }
        else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }
}
