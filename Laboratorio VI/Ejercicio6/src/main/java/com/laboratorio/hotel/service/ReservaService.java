package com.laboratorio.hotel.service;

import com.laboratorio.hotel.model.Reserva;
import com.laboratorio.hotel.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {
    private final ReservaRepository repository;

    public ReservaService(ReservaRepository repository) {
        this.repository = repository;
    }

    public Reserva crearReserva(Reserva reserva) {
        return repository.save(reserva);
    }

    public List<Reserva> obtenerTodas() {
        return repository.findAll();
    }

    public Optional<Reserva> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public Optional<Reserva> actualizarReserva(Long id, Reserva reservaActualizada) {
        return repository.findById(id).map(existente -> {
            reservaActualizada.setId(id);
            return repository.save(reservaActualizada);
        });
    }

    public boolean cancelarReserva(Long id) {
        return repository.deleteById(id);
    }
}