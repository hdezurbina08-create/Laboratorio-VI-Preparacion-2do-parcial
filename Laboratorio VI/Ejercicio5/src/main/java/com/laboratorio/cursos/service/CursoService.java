package com.laboratorio.cursos.service;

import com.laboratorio.cursos.model.Curso;
import com.laboratorio.cursos.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {
    private final CursoRepository repository;

    public CursoService(CursoRepository repository) {
        this.repository = repository;
    }

    public Curso crearCurso(Curso curso) {
        return repository.save(curso);
    }

    public List<Curso> obtenerTodos() {
        return repository.findAll();
    }

    public Optional<Curso> buscarPorCodigo(String codigo) {
        return repository.findByCodigo(codigo);
    }

    public Optional<Curso> actualizarCurso(Long id, Curso cursoActualizado) {
        return repository.findById(id).map(existente -> {
            cursoActualizado.setId(id);
            return repository.save(cursoActualizado);
        });
    }

    public boolean eliminarCurso(Long id) {
        return repository.deleteById(id);
    }
}