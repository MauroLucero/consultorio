package com.integrador.consultorio.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.integrador.consultorio.entity.Odontologo;
import com.integrador.consultorio.model.OdontologoDTO;
import com.integrador.consultorio.repository.IOdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OdontologoServiceImp implements OdontologoService{

    IOdontologoRepository odontologoRepository;
    @Autowired
    public OdontologoServiceImp(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }
    @Autowired
    ObjectMapper mapper;

    private void guardar(OdontologoDTO odontologoDTO){
        Odontologo odontologo = mapper.convertValue(odontologoDTO,Odontologo.class);

        odontologoRepository.save(odontologo);
    }
    @Override
    public void guardarOdontologo(OdontologoDTO odontologoDTO) {
        guardar(odontologoDTO);
    }

    @Override
    public void actualizarOdontologo(OdontologoDTO odontologoDTO) {
        guardar(odontologoDTO);
    }

    @Override
    public OdontologoDTO buscarOdontologo(Long id) {
        Odontologo odontologo = this.odontologoRepository.findById(id).orElse(null);
        OdontologoDTO odontologoDTO= null;
        if(odontologo!=null){
            odontologoDTO = mapper.convertValue(odontologo,OdontologoDTO.class);
        }
        return odontologoDTO;
    }

    @Override
    public Set<OdontologoDTO> buscarTodos() {
        List<Odontologo> odontologos= this.odontologoRepository.findAll();
        Set<OdontologoDTO> odontologoDTO = new HashSet<>();
        for(Odontologo odontologo:odontologos){
            odontologoDTO.add(mapper.convertValue(odontologo,OdontologoDTO.class));
        }
        return odontologoDTO;
    }



    @Override
    public void borrarOdontologo(Long id) {
        this.odontologoRepository.deleteById(id);
    }
}
