package org.aplication.services;

import org.domain.Usuario;
import org.interfaces.UsuarioRepository;

import java.util.List;

public class UsuarioServicesImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioServicesImpl(UsuarioRepository usuarioRepository){
      this.usuarioRepository = usuarioRepository;}
@Override
    public List<Usuario> findAll(){
       return usuarioRepository.findAll();
}
@Override
public Usuario findByNombre(String nombre){
        return  usuarioRepository.findByNombre(nombre);
}
@Override
    public  void save(Usuario usuario){
        validarUsuario(usuario);
        usuarioRepository.save(usuario);
}
@Override
    public void  update(Usuario usuario){
        validarUsuario(usuario);
        usuarioRepository.update(usuario);
}
@Override
    public void delete(String nombre){
        usuarioRepository.delete(nombre);
}
private void validarUsuario(Usuario usuario){
        if(usuario.getNombre().isEmpty()){
            throw new IllegalArgumentException("El nombre del paciente no puede estar vacio");
        }
}
}
