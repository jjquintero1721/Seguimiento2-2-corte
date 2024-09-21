package org.example.aplication.service;

import org.example.domain.Cita;


import java.util.List;

public interface CitaService {
    List<Cita> findAllCita();
    Cita findByIdCita(int idCita);
    void saveCita(Cita cita );
    void updateCita(Cita cita);
    void deleteCita(int idCita);
}
