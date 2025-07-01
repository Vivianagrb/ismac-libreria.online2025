package com.distribuida.controller;

import com.distribuida.model.Factura_Detalle;
import com.distribuida.service.FacturaDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/factura_detalle")
public class FacturaDetalleController {

        @Autowired
        private FacturaDetalleService facturaDetalleService;

        @GetMapping
        public ResponseEntity<List<Factura_Detalle>> findAll() {
            List<Factura_Detalle> facturas_detalle = facturaDetalleService.findAll();
            return ResponseEntity.ok(facturas_detalle);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Factura_Detalle> findOne(@PathVariable int id) {
            Factura_Detalle facturas_detalle = facturaDetalleService.findOne(id);
            if (facturas_detalle == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(facturas_detalle);
        }

        @PostMapping
        public ResponseEntity<Factura_Detalle> save(@RequestBody Factura_Detalle facturas_detalle) {
            Factura_Detalle facturas_detalle1 = facturaDetalleService.save(facturas_detalle);
            return ResponseEntity.ok(facturas_detalle);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Factura_Detalle> update(@PathVariable int id, @RequestBody Factura_Detalle facturas_detalle) {
            Factura_Detalle facturas_detalle1 = facturaDetalleService.update(id, facturas_detalle);
            return ResponseEntity.ok(facturas_detalle);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable int id) {
            facturaDetalleService.delete(id);
            return ResponseEntity.noContent().build();
        }

    }

