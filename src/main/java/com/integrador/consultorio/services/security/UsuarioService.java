package com.integrador.consultorio.services.security;


import com.integrador.consultorio.entity.security.Usuario;
import com.integrador.consultorio.exceptions.BadRequestException;
import com.integrador.consultorio.repository.security.IRolRepository;
import com.integrador.consultorio.repository.security.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final IUsuarioRepository usuarioRepository;
    private final IRolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario guardar(Usuario usuario) throws BadRequestException {
        if (usuario == null)
            throw new BadRequestException("El usuario no puede ser null");
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }
}
