package org.aplication.services;

import org.domain.Usuario;

import java.util.List;

public interface UsuarioService {
    List<Usuario> findAll();
    Usuario findByNombre(String nombre);
    void save(Usuario usuario);
    void update(Usuario usuario);
    void delete(String nombre);
}
