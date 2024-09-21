package Punto2.infraestructure.repositoryImpl;


import Punto2.domain.Tarea;
import Punto2.interfaces.TareaRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TareaRepositoryImpl implements TareaRepository {
    private static final String FILE_NAME = "tareas.dat";

    @Override
    public List<Tarea> findAll() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (ArrayList<Tarea>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Tarea findById(int id) {
        return findAll().stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(Tarea tarea) {
        List<Tarea> tareas = findAll();
        tareas.add(tarea);
        saveAll(tareas);
    }

    @Override
    public void update(Tarea tarea) {
        List<Tarea> tareas = findAll();
        tareas = tareas.stream()
                .map(t -> t.getId() == tarea.getId() ? tarea : t)
                .collect(Collectors.toList());
        saveAll(tareas);
    }

    @Override
    public void delete(int id) {
        List<Tarea> tareas = findAll();
        tareas = tareas.stream()
                .filter(t -> t.getId() != id)
                .collect(Collectors.toList());
        saveAll(tareas);
    }

    @Override
    public List<Tarea> findByEmpleado(int idEmpleado) {
        return findAll().stream()
                .filter(t -> t.getEmpleado().getId() == idEmpleado)
                .collect(Collectors.toList());
    }

    private void saveAll(List<Tarea> tareas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(tareas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
