package com.integrador.consultorio.services.security;

import com.integrador.consultorio.entity.security.Rol;
import com.integrador.consultorio.entity.security.Usuario;
import com.integrador.consultorio.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {
    private final UsuarioService usuarioService;

    @Override
    public void run(ApplicationArguments args) throws BadRequestException {
        usuarioService.guardar(
                Usuario.builder()
                        .username("admin")
                        .password("admin")
                        .rol(new Rol("ADMIN"))
                        .build()
        );

    }
}
