package Punto2.aplication.service;


import Punto2.domain.Tarea;

import java.util.List;


public interface TareaService {
    List<Tarea> findAll();
    Tarea findById(int id);
    void save(Tarea tarea);
    void update(Tarea tarea);
    void delete(int id);
    List<Tarea> findByEmpleado(int idEmpleado);
}
