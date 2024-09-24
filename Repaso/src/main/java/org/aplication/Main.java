package org.aplication;

import org.aplication.services.UsuarioService;
import org.aplication.services.UsuarioServicesImpl;
import org.domain.Usuario;
import org.infraestructure.repositoryImpl.UsuarioRepositoryImpl;
import org.interfaces.UsuarioRepository;

import javax.swing.*;
import java.util.List;

public class Main {
    private static final UsuarioService usuarioService;

    static {
        UsuarioRepository usuarioRepository = new UsuarioRepositoryImpl();
        usuarioService = new UsuarioServicesImpl(usuarioRepository);
    }

    public static void main(String[] args) {
        boolean salir = false;
        while (!salir){
            String opcion = JOptionPane.showInputDialog(
                    "1. Listar Usuario \n" +
                            "2.Crear Usuario\n" +
                            "3.Cambiar Contraseña\n" +
                            "4.Eliminar Usuario\n" +
                            "5.Salir\n" +
                            "Seleccione una opcion"
            );
            if(opcion==null){
                salir = true;
                continue;
            }
            switch (opcion){
                case "1":
                    listarUsuario();
                    break;
                case "2":
                    crearUsuario();
                    break;
                case "3":
                    actuContraseña();
                    break;
                case "4":
                    eliminarUsuario();
                    break;
                case "5":
                    salir = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null,"Opcion invalida");
            }
        }

    }
    private static void listarUsuario() {
        List<Usuario> usuarios = usuarioService.findAll();
        if(usuarios.isEmpty()){
            JOptionPane.showMessageDialog(null,"No hay usuarios registrados");
        }else {
            StringBuilder mensaje = new StringBuilder("Listado de usuarios: \n");
            for(Usuario usuario : usuarios){
                mensaje.append(usuario).append("\n");
            }
            JOptionPane.showMessageDialog(null, mensaje.toString());
        }
    }
    private static  void crearUsuario(){
        String nombre1 = JOptionPane.showInputDialog("Ingrese el nombre del paciente:");
        String contraseña = JOptionPane.showInputDialog("ingrese la contraseña");
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre1);
        usuario.setContraseña(contraseña);

        try{
            usuarioService.save(usuario);
            JOptionPane.showMessageDialog(null,"Usuario creado exitosamente");
        }catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    private static void actuContraseña(){
        String nombre0 = JOptionPane.showInputDialog(null,"ingrese el nombre del usuario al que quiere cambiar la contraseña");

        Usuario usuario = usuarioService.findByNombre(nombre0);
        if(usuario == null){
            JOptionPane.showMessageDialog(null,"No se encontro el usuario con nombre de: " + nombre0);
            return;
        }

        String contraseña = JOptionPane.showInputDialog("ingrese la nueva contraseña");
        if(!contraseña.isEmpty()){
            usuario.setContraseña(contraseña);
        }
        try{
            usuarioService.update(usuario);
            JOptionPane.showMessageDialog(null,"Usuario actualizado exitosamente");
        }catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    private static void eliminarUsuario(){
        String nombre = JOptionPane.showInputDialog("ingrese el nombre del usuario que desea eliminar");
        Usuario usuario = usuarioService.findByNombre(nombre);
        if(usuario == null){
            JOptionPane.showMessageDialog(null,"No se encontro el usuario con nombre: " + nombre);
            return;
        }
        usuarioService.delete(nombre);
        JOptionPane.showMessageDialog(null,"Usuario eliminado exitosamente");
    }
}
