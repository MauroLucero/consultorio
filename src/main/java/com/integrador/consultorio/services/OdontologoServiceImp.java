package com.integrador.consultorio.services;

import com.integrador.consultorio.entity.Odontologo;
import com.integrador.consultorio.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoServiceImp implements OdontologoService{

    OdontologoRepository odontologoRepository;
    @Autowired
    public OdontologoServiceImp(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        return this.odontologoRepository.save(odontologo);
    }

    @Override
    public Odontologo buscar(Long id) {
        return this.odontologoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Odontologo> buscarTodos() {
        return odontologoRepository.findAll();
    }

    @Override
    public Odontologo actualizar(Odontologo odontologo) {
        return this.odontologoRepository.save(odontologo);
    }

    @Override
    public void borrar(Long id) {
        this.odontologoRepository.delete(buscar(id));
    }
}
