package com.integrador.consultorio.repository;

import com.integrador.consultorio.entity.security.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface IRolRepository extends JpaRepository<Rol,Long> {
}
