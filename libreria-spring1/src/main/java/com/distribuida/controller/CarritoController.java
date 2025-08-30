package com.distribuida.controller;
import com.distribuida.model.Carrito;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carrito")

public class CarritoController {

    @GetMapping
    public List<Carrito> findAll() {
        // Lógica para devolver todos los carritos
        return List.of();
    }

    @PostMapping
    public Carrito save(@RequestBody Carrito carrito) {
        // Lógica para guardar carrito
        return carrito;
    }

    @PutMapping("/{id}")
    public Carrito update(@PathVariable Long id, @RequestBody Carrito carrito) {
        // Lógica para actualizar carrito
        return carrito;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return null;
    }

    public ResponseEntity<Carrito> findOne(int i) {
    }
}
