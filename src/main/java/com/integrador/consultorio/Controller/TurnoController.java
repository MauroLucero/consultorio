package com.integrador.consultorio.Controller;


import com.integrador.consultorio.Model.Paciente;
import com.integrador.consultorio.Repository.impl.OdontologoIDAOH2;
import com.integrador.consultorio.Repository.impl.PacienteIDAOH2;
import com.integrador.consultorio.Repository.impl.TurnoIDAO;
import com.integrador.consultorio.Model.Turno;
import com.integrador.consultorio.Services.OdontologoService;
import com.integrador.consultorio.Services.PacienteService;
import com.integrador.consultorio.Services.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private final TurnoService turnoService;
    private final OdontologoService odontologoService ;
    private final PacienteService pacienteService;

    @Autowired
    public TurnoController(TurnoService turnoService, OdontologoService odontologoService, PacienteService pacienteService){
        this.turnoService = turnoService;
        turnoService.setTurnoService(new TurnoIDAO());

        this.odontologoService = odontologoService;
        odontologoService.setOdontologoService(new OdontologoIDAOH2());

        this.pacienteService = pacienteService;
        pacienteService.setPacienteService(new PacienteIDAOH2());
    }


    @PostMapping("/")
    public ResponseEntity<Turno> reservarTurno(@RequestBody Turno turno){
       ResponseEntity<Turno> response;
       if(pacienteService.buscar(turno.getPaciente().getId()) != null && odontologoService.buscar(turno.getOdontologo().getId()) != null){
           response = ResponseEntity.ok(turnoService.guardar(turno));
       }
       else {
           response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }
       return response;
    }

    @GetMapping("/{id}")
    public Turno buscarTurno(@PathVariable("id") long id){
        return turnoService.buscar(id);
    }

    @GetMapping("/")
    public List<Turno> listarTurnos(){
        return  turnoService.listarTodos();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Turno> borrarTurno(@PathVariable long id){
        ResponseEntity<Turno> response;

        if (turnoService.buscar(id) == null){
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            turnoService.eliminar(id);
            response = ResponseEntity.status(HttpStatus.OK).build();
        }
        return response;
    }




}






