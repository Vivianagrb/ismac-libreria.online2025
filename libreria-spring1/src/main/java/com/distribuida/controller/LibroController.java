package com.distribuida.controller;

import com.distribuida.model.Libro;
import com.distribuida.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/libro")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping
    public ResponseEntity<List<Libro>> findAll() {
        List<Libro> libros = libroService.findAll();
        return ResponseEntity.ok(libros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> findOne(@PathVariable int id) {
        Libro libros = libroService.findOne(id);
        if (libros == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(libros);
    }

    @PostMapping
    public ResponseEntity<Libro> save(@RequestBody Libro libros) {
        Libro libros1 = libroService.save(libros);
        return ResponseEntity.ok(libros);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> update(@PathVariable int id, @RequestBody Libro libros) {
        Libro libros1 = libroService.update(id, libros);
        return ResponseEntity.ok(libros);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        libroService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

