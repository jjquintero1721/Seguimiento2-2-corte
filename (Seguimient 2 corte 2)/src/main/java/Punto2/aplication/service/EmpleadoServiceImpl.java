package Punto2.aplication.service;


import Punto2.domain.Empleado;
import Punto2.interfaces.EmpleadoRepository;

import java.util.List;

public class EmpleadoServiceImpl implements EmpleadoService {
    private final EmpleadoRepository empleadoRepository;

    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public List<Empleado> findAll() {
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado findById(int id) {
        return empleadoRepository.findById(id);
    }

    @Override
    public void save(Empleado empleado) {
        empleadoRepository.save(empleado);
    }

    @Override
    public void update(Empleado empleado) {
        empleadoRepository.update(empleado);
    }

    @Override
    public void delete(int id) {
        empleadoRepository.delete(id);
    }

    @Override
    public List<Empleado> findByDepartamento(String departamento) {
        return empleadoRepository.findByDepartamento(departamento);
    }

    @Override
    public double calcularSalarioTotal(String departamento) {
        List<Empleado> empleados = findByDepartamento(departamento);
        return empleados.stream().mapToDouble(Empleado::getSalario).sum();
    }
}

