package com.distribuida.dao;

import com.distribuida.model.Factura;
import com.distribuida.model.Factura_Detalle;
import com.distribuida.model.Libro;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(false)
public class FacturaDetalleRepositoryTestIt {

    @Autowired
    private FacturaDetalleRepository facturaDetalleRepository;

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private FacturaRepository facturaRepository;


    @Test
    public void findAll() {
        List<Factura_Detalle> factura_detalle = facturaDetalleRepository.findAll();
        assertNotNull(factura_detalle);
        assertTrue(factura_detalle.size() > 0, "La lista de facturas debería contener elementos.");
        for (Factura_Detalle item : factura_detalle) {
            System.out.println(item);
        }
    }

    @Test
    public void findOne() {
        Optional<Factura_Detalle> factura_detalle = facturaDetalleRepository.findById(20);
        assertTrue(factura_detalle.isPresent(), "La factura detalle con id=20 debería existir.");
        assertEquals(16, factura_detalle.get().getFactura().getId_Factura());
        System.out.println(factura_detalle.get());
    }
    @Test
    public void save() {
        Optional<Libro> libro = libroRepository.findById(1);
        Optional<Factura> factura = facturaRepository.findById(20);
        assertTrue(libro.isPresent(), "El libro con id=1 debería existir.");
        assertTrue(factura.isPresent(), "La factura con id=20 debería existir.");

        Factura_Detalle factura_detalle = new Factura_Detalle();
        factura_detalle.setCantidad(55);
        factura_detalle.setSubtotal(100.10);
        factura_detalle.setFactura(factura.orElse(null));
        factura_detalle.setLibro(libro.orElse(null));

        Factura_Detalle facturaDetalleGuardada = facturaDetalleRepository.save(factura_detalle);
        assertNotNull(facturaDetalleGuardada);
        assertEquals(55, facturaDetalleGuardada.getCantidad());
        assertEquals(100.10, facturaDetalleGuardada.getSubtotal(22.00));
    }
    @Test
    public void update() {
        Optional<Factura_Detalle> facturaDetalleExistente = facturaDetalleRepository.findById(201);
        Optional<Factura> facturaExistente = facturaRepository.findById(2);
        Optional<Libro> libroExistente = libroRepository.findById(1);

        assertNotNull(facturaDetalleExistente);
        assertNotNull(facturaExistente);
        assertNotNull(libroExistente);

        facturaDetalleExistente.orElse(null).setCantidad(3);
        facturaDetalleExistente.orElse(null).setSubtotal(220.00);
        facturaDetalleExistente.orElse(null).setFactura(facturaExistente.orElse(null));
        facturaDetalleExistente.orElse(null).setLibro(libroExistente.orElse(null));
        Factura_Detalle facturaDetalleActualizado = facturaDetalleRepository.save(facturaDetalleExistente.orElse(null));
        assertNotNull(facturaDetalleActualizado);
        assertEquals(3,facturaDetalleActualizado.getCantidad());

    }
    @Test
    public void delete() {
        if (facturaDetalleRepository.existsById(211)) {
            facturaDetalleRepository.deleteById(211);
        }
        assertFalse(facturaDetalleRepository.existsById(211), "El id=86 deberia haberse eliminado");
    }


}