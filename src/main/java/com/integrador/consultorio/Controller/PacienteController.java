package com.integrador.consultorio.Controller;


import com.integrador.consultorio.Repository.impl.PacienteIDAOH2;
import com.integrador.consultorio.Model.Paciente;
import com.integrador.consultorio.Services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;


    @Autowired
    public PacienteController(PacienteService pacienteService){
        this.pacienteService = pacienteService;
        pacienteService.setPacienteService(new PacienteIDAOH2());
    }

    @PostMapping("/")
    public ResponseEntity<Paciente> guardarPaciente(@RequestBody Paciente paciente){
        ResponseEntity response;
       if (pacienteService.guardar(paciente)!=null){
           response = ResponseEntity.status(HttpStatus.OK).build();
        }
       else{
           response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }
       return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable("id") long id){
        ResponseEntity response;
        Paciente paciente = pacienteService.buscar(id);
        if(paciente != null){
            response = ResponseEntity.ok(paciente);
        }
        else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @GetMapping("/")
    public ResponseEntity<List<Paciente>> listarPacientes(){
        ResponseEntity response;
        List<Paciente> listaPacientes = pacienteService.listarTodos();
        if(listaPacientes.size() != 0){
            response = ResponseEntity.ok(listaPacientes);
        }
        else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @PutMapping("/")
    public ResponseEntity<Paciente> actualizarPaciente(@RequestBody Paciente paciente){
        ResponseEntity response;
        if(pacienteService.buscar(paciente.getId()) != null){
            pacienteService.actualizar(paciente);
            response = ResponseEntity.status(HttpStatus.OK).build();
        }
        else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Paciente> eliminarPaciente(@PathVariable("id") long id){
        ResponseEntity response;
        if(pacienteService.buscar(id) != null){
            pacienteService.eliminar(id);
            response = ResponseEntity.status(HttpStatus.OK).build();
        }
        else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }
}
