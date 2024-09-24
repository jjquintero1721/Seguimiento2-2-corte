package org.interfaces;

import org.domain.Usuario;

import java.util.List;

public interface UsuarioRepository {
    List<Usuario> findAll();
    Usuario findByNombre(String nombre);
    void save(Usuario usuario);
    void update(Usuario usuario);
    void delete(String nombre);
}
