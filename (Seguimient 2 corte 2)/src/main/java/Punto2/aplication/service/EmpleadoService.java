package Punto2.aplication.service;


import Punto2.domain.Empleado;

import java.util.List;


public interface EmpleadoService {
    List<Empleado> findAll();
    Empleado findById(int id);
    void save(Empleado empleado);
    void update(Empleado empleado);
    void delete(int id);
    List<Empleado> findByDepartamento(String departamento);
    double calcularSalarioTotal(String departamento);
}

