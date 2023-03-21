package com.integrador.consultorio.Services;

import com.integrador.consultorio.Repository.IDao;
import com.integrador.consultorio.Model.Odontologo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OdontologoService implements IDao<Odontologo> {

    IDao<Odontologo> odontologoService = null;

    public IDao<Odontologo> getOdontologoService() {
        return odontologoService;
    }

    public void setOdontologoService(IDao<Odontologo> odontologoService) {
        this.odontologoService = odontologoService;
    }


    @Override
    public Odontologo guardar(Odontologo odontologo) {
        return this.odontologoService.guardar(odontologo);

    };

    @Override
    public Odontologo buscar(long id) {
        return this.odontologoService.buscar(id);

    }

    @Override
    public List<Odontologo> listarTodos() {
        return this.odontologoService.listarTodos();
    }

    @Override
    public void actualizar(Odontologo odontologo) {
        odontologoService.actualizar(odontologo);
    }

    @Override
    public void eliminar(long id) {
        odontologoService.eliminar(id);
    }
}
