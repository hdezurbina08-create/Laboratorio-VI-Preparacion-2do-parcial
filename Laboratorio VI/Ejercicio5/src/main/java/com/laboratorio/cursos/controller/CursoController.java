package com.laboratorio.cursos.controller;

import com.laboratorio.cursos.model.Curso;
import com.laboratorio.cursos.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    // 1. Obtener todos los cursos
    @GetMapping
    public List<Curso> obtenerTodos() {
        return cursoRepository.findAll();
    }

    // 2. Obtener un curso por ID
    @GetMapping("/{id}")
    public ResponseEntity<Curso> obtenerPorId(@PathVariable Long id) {
        return cursoRepository.findById(id)
                .map(curso -> ResponseEntity.ok(curso))
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Crear un nuevo curso
    @PostMapping
    public ResponseEntity<Curso> crearCurso(@RequestBody Curso curso) {
        Curso nuevoCurso = cursoRepository.save(curso);
        return new ResponseEntity<>(nuevoCurso, HttpStatus.CREATED);
    }

    // 4. Actualizar un curso
    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizarCurso(@PathVariable Long id, @RequestBody Curso cursoDetalles) {
        return cursoRepository.findById(id)
                .map(cursoExistente -> {
                    cursoExistente.setNombre(cursoDetalles.getNombre());
                    cursoExistente.setCodigo(cursoDetalles.getCodigo());
                    cursoExistente.setCreditos(cursoDetalles.getCreditos());
                    cursoExistente.setEstado(cursoDetalles.getEstado());
                    Curso actualizado = cursoRepository.save(cursoExistente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 5. Eliminar un curso
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable Long id) {
        if (cursoRepository.existsById(id)) {
            cursoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
