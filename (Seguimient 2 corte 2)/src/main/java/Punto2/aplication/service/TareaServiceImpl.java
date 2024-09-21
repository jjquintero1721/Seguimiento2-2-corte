package Punto2.aplication.service;


import Punto2.domain.Tarea;
import Punto2.interfaces.TareaRepository;

import java.util.List;

public class TareaServiceImpl implements TareaService {
    private final TareaRepository tareaRepository;

    public TareaServiceImpl(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    @Override
    public List<Tarea> findAll() {
        return tareaRepository.findAll();
    }

    @Override
    public Tarea findById(int id) {
        return tareaRepository.findById(id);
    }

    @Override
    public void save(Tarea tarea) {
        tareaRepository.save(tarea);
    }

    @Override
    public void update(Tarea tarea) {
        tareaRepository.update(tarea);
    }

    @Override
    public void delete(int id) {
        tareaRepository.delete(id);
    }

    @Override
    public List<Tarea> findByEmpleado(int idEmpleado) {
        return tareaRepository.findByEmpleado(idEmpleado);
    }
}
