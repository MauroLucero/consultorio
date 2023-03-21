package com.integrador.consultorio.Controller;


import com.integrador.consultorio.Repository.impl.OdontologoIDAOH2;
import com.integrador.consultorio.Model.Odontologo;
import com.integrador.consultorio.Services.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Odontologo guardarOdontologo(@RequestBody Odontologo odontologo){
        return odontologoService.guardar(odontologo);
    }

    @GetMapping("/{id}")
    public Odontologo buscarOdontologo(@PathVariable("id") long id){
        return odontologoService.buscar(id);
    }

    @GetMapping("/")
    public List<Odontologo> listarOdontologos(){
        return odontologoService.listarTodos();
    }

    @PutMapping("/")
    public void actualizarOdontologo(@RequestBody Odontologo odontologo){
        odontologoService.actualizar(odontologo);
    }

    @DeleteMapping("/{id}")
    public void eliminarOdontologo(@PathVariable("id") long id){
        odontologoService.eliminar(id);
    }
}
