package com.distribuida.controller;

import com.distribuida.model.Factura;
import com.distribuida.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/factura")
public class FacturaController {

        @Autowired
        private FacturaService facturaService;

        @GetMapping
        public ResponseEntity<List<Factura>> findAll() {
            List<Factura> facturas = facturaService.findAll();
            return ResponseEntity.ok(facturas);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Factura> findOne(@PathVariable int id) {
            Factura facturas = facturaService.findOne(id);
            if (facturas == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(facturas);
        }

        @PostMapping
        public ResponseEntity<Factura> save(@RequestBody Factura facturas) {
            Factura facturas1 = facturaService.save(facturas);
            return ResponseEntity.ok(facturas);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Factura> update(@PathVariable int id, @RequestBody Factura facturas) {
            Factura facturas1 = facturaService.update(id, facturas);
            return ResponseEntity.ok(facturas);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable int id) {
            facturaService.delete(id);
            return ResponseEntity.noContent().build();
        }

    }

