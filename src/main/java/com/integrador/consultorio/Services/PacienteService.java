package com.integrador.consultorio.Services;

import com.integrador.consultorio.Repository.IDao;
import com.integrador.consultorio.Model.Paciente;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PacienteService implements IDao<Paciente> {

    private IDao<Paciente> pacienteService;

    public IDao<Paciente> getPacienteService() {
        return pacienteService;
    }

    public void setPacienteService(IDao<Paciente> pacienteService) {
        this.pacienteService = pacienteService;
    }

    @Override
    public Paciente guardar(Paciente paciente) {
        return pacienteService.guardar(paciente);
    }

    @Override
    public void eliminar(long id) {
        pacienteService.eliminar(id);
    }

    @Override
    public Paciente buscar(long id) {
        return pacienteService.buscar(id);
    }

    @Override
    public List<Paciente> listarTodos() {
        return pacienteService.listarTodos();
    }

    @Override
    public void actualizar(Paciente paciente) {
        pacienteService.actualizar(paciente);
    }
}
