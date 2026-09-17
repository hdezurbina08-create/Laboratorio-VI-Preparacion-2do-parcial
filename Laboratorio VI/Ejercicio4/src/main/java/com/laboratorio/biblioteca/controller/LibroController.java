package com.laboratorio.biblioteca.controller;

import com.laboratorio.biblioteca.model.Libro;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final List<Libro> libros = new ArrayList<>();
    private Long nextId = 1L;

    public LibroController() {
        libros.add(new Libro(nextId++, "Cien Años de Soledad", "Gabriel García Márquez", "978-0307474728", 1967));
        libros.add(new Libro(nextId++, "Don Quijote de la Mancha", "Miguel de Cervantes", "978-8424116033", 1605));
    }

    @GetMapping
    public List<Libro> obtenerTodos() {
        return libros;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerPorId(@PathVariable Long id) {
        Optional<Libro> libro = libros.stream().filter(l -> l.getId().equals(id)).findFirst();
        return libro.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro nuevoLibro) {
        nuevoLibro.setId(nextId++);
        libros.add(nuevoLibro);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoLibro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable Long id, @RequestBody Libro libroActualizado) {
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getId().equals(id)) {
                libroActualizado.setId(id);
                libros.set(i, libroActualizado);
                return ResponseEntity.ok(libroActualizado);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        boolean removido = libros.removeIf(l -> l.getId().equals(id));
        if (removido) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}