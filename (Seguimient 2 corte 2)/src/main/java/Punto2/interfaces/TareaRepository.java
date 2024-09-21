package Punto2.interfaces;


import Punto2.domain.Tarea;

import java.util.List;

import java.util.List;


public interface TareaRepository {
    List<Tarea> findAll();
    Tarea findById(int id);
    void save(Tarea tarea);
    void update(Tarea tarea);
    void delete(int id);
    List<Tarea> findByEmpleado(int idEmpleado);
}

