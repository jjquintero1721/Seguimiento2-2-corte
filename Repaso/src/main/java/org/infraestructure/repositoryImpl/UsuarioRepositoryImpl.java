package org.infraestructure.repositoryImpl;

import org.domain.Usuario;
import org.interfaces.UsuarioRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UsuarioRepositoryImpl implements UsuarioRepository {
    private static final String FILE_NAME = "Usuarios.dat";

    @Override
    public List<Usuario> findAll() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (ArrayList<Usuario>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
    @Override
    public  Usuario findByNombre(String nombre){
        return  findAll().stream()
                .filter(p -> Objects.equals(p.getNombre(), nombre))
                .findFirst()
                .orElse(null);
    }
    @Override
    public void save(Usuario usuario) {
        List<Usuario> usuarios = findAll();
        usuarios.add(usuario);
        saveAll(usuarios);
    }

    @Override
    public void update(Usuario usuario) {
        List<Usuario> usuarios = findAll();
        usuarios = usuarios.stream()
                .map(p -> Objects.equals(p.getNombre(), usuario.getNombre()) ? usuario : p)
                .collect(Collectors.toList());
        saveAll(usuarios);
    }

    @Override
    public void delete(String nombre) {
        List<Usuario> usuarios = findAll();
        usuarios = usuarios.stream()
                .filter(p -> !Objects.equals(p.getNombre(), nombre))
                .collect(Collectors.toList());
        saveAll(usuarios);
    }

    private void saveAll(List<Usuario> usuarios) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(usuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}