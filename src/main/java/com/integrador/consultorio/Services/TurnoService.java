package com.integrador.consultorio.Services;

import com.integrador.consultorio.Repository.IDao;
import com.integrador.consultorio.Model.Turno;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TurnoService implements IDao<Turno> {

    private IDao<Turno> turnoService;

    public TurnoService() {
    }

    public IDao<Turno> getTurnoService() {
        return turnoService;
    }

    public void setTurnoService(IDao<Turno> turnoService) {
        this.turnoService = turnoService;
    }

    @Override
    public Turno guardar(Turno turno) {
       return this.turnoService.guardar(turno);
    }

    @Override
    public void eliminar(long id) {
        this.turnoService.eliminar(id);
    }

    @Override
    public Turno buscar(long id) {
        return this.turnoService.buscar(id);
    }

    @Override
    public List<Turno> listarTodos() {
        return this.turnoService.listarTodos();
    }

    @Override
    public void actualizar(Turno turno) {
        this.turnoService.actualizar(turno);
    }
}
