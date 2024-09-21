package Punto2.infraestructure.repositoryImpl;


import Punto2.domain.Empleado;
import Punto2.interfaces.EmpleadoRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmpleadoRepositoryImpl implements EmpleadoRepository {
    private static final String FILE_NAME = "empleados.dat";

    @Override
    public List<Empleado> findAll() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (ArrayList<Empleado>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Empleado findById(int id) {
        return findAll().stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(Empleado empleado) {
        List<Empleado> empleados = findAll();
        empleados.add(empleado);
        saveAll(empleados);
    }

    @Override
    public void update(Empleado empleado) {
        List<Empleado> empleados = findAll();
        empleados = empleados.stream()
                .map(e -> e.getId() == empleado.getId() ? empleado : e)
                .collect(Collectors.toList());
        saveAll(empleados);
    }

    @Override
    public void delete(int id) {
        List<Empleado> empleados = findAll();
        empleados = empleados.stream()
                .filter(e -> e.getId() != id)
                .collect(Collectors.toList());
        saveAll(empleados);
    }

    @Override
    public List<Empleado> findByDepartamento(String departamento) {
        return findAll().stream()
                .filter(e -> e.getDepartamento().equalsIgnoreCase(departamento))
                .collect(Collectors.toList());
    }

    private void saveAll(List<Empleado> empleados) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(empleados);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

