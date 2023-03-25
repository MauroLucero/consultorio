package com.integrador.consultorio.repository;

import com.integrador.consultorio.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente,Long> {
}
