package org.example.aplication.service;

import org.example.domain.Cita;
import org.example.interfaces.CitaRepository;

import java.util.List;

public class CitaServiceImpl implements CitaService {
    private final CitaRepository citaRepository;

    public CitaServiceImpl(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    @Override
    public List<Cita> findAllCita() {
        return citaRepository.findAllCita();
    }

    @Override
    public Cita findByIdCita(int idCita) {
        return citaRepository.findByIdCita(idCita);
    }

    @Override
    public void saveCita(Cita cita) {
        validarCita(cita);
        citaRepository.saveCita(cita);
    }

    @Override
    public void updateCita(Cita cita) {
        validarCita(cita);
        citaRepository.updateCita(cita);
    }

    @Override
    public void deleteCita(int idCita) {
        citaRepository.deleteCita(idCita);
    }

    private void validarCita(Cita cita) {
        if (cita.getMotivo().isEmpty()) {
            throw new IllegalArgumentException("El motivo de la cita no puede estar vacío");
        }
    }
}
