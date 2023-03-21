package com.integrador.consultorio.Controller;


import com.integrador.consultorio.Repository.impl.PacienteIDAOH2;
import com.integrador.consultorio.Model.Paciente;
import com.integrador.consultorio.Services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Paciente guardarPaciente(@RequestBody Paciente paciente){
        return pacienteService.guardar(paciente);
    }

    @GetMapping("/")
    public List<Paciente> listarPacientes(){
        return pacienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public Paciente buscarPaciente(@PathVariable("id") long id){
        return pacienteService.buscar(id);
    }

    @PutMapping("/")
    public void actualizarPaciente(@RequestBody Paciente paciente){
        pacienteService.actualizar(paciente);
    }

    @DeleteMapping("/{id}")
    public void eliminarPaciente(@PathVariable("id") long id){
        pacienteService.eliminar(id);
    }
}
