package com.distribuida.controller;

import com.distribuida.model.Autor;
import com.distribuida.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/autor")

public class AutorController {

        @Autowired
        private AutorService autorService;

        @GetMapping
        public ResponseEntity<List<Autor>> findAll() {
            List<Autor> autor = autorService.findAll();
            return ResponseEntity.ok(autor);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Autor> findOne(@PathVariable int id) {
            Autor autor = autorService.findOne(id);
            if (autor == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(autor);
        }

        @PostMapping
        public ResponseEntity<Autor> save(@RequestBody Autor autor) {
            Autor autor1 = autorService.save(autor);
            return ResponseEntity.ok(autor);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Autor> update(@PathVariable int id, @RequestBody Autor autor) {
            Autor autor1 = autorService.update(id, autor);
            return ResponseEntity.ok(autor);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable int id) {
            autorService.delete(id);
            return ResponseEntity.noContent().build();
        }

    }

