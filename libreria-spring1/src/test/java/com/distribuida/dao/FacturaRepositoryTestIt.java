package com.distribuida.dao;

import com.distribuida.model.Cliente;
import com.distribuida.model.Factura;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(false)
public class FacturaRepositoryTestIt {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void findAll() {
        List<Factura> facturas = facturaRepository.findAll();
        assertNotNull(facturas);
        assertTrue(facturas.size() > 0, "La lista de facturas debería contener elementos.");
        for (Factura item : facturas) {
            System.out.println(item);
        }
    }

    @Test
    public void findOne() {
        Optional<Factura> factura = facturaRepository.findById(82);
        assertTrue(factura.isPresent(), "La factura con id=82 debería existir.");
        assertEquals("FAC-0061", factura.get().getNum_factura());
        System.out.println(factura.get());
    }

    @Test
    public void save() {
        Optional<Cliente> cliente = clienteRepository.findById(1);
        assertTrue(cliente.isPresent(), "El cliente con id=1 debería existir.");
        Factura factura = new Factura();
        factura.setNum_factura("FAC-0083");
        factura.setFecha(new Date());
        factura.setTotal_neto(100.00);
        factura.setIva(15.00);
        factura.setTotal(115.00);
        factura.setCliente(cliente.orElse(null));

        Factura facturaGuardada = facturaRepository.save(factura);
        assertNotNull(facturaGuardada);
        assertEquals("FAC-0083", facturaGuardada.getNum_factura());
        assertEquals(115.00, facturaGuardada.getTotal());
    }

    @Test
    public void update() {
        Optional<Factura> facturaExistente = facturaRepository.findById(86);
        Optional<Cliente> clienteExistente = clienteRepository.findById(2);

        assertNotNull(facturaExistente);
        assertNotNull(clienteExistente);

        facturaExistente.orElse(null).setNum_factura("FAC000099");
        facturaExistente.orElse(null).setFecha(new Date());
        facturaExistente.orElse(null).setTotal_neto(200.00);
        facturaExistente.orElse(null).setIva(30.00);
        facturaExistente.orElse(null).setTotal(230.00);
        facturaExistente.orElse(null).setCliente(clienteExistente.orElse(null));

        Factura facturaActualizado = facturaRepository.save(facturaExistente.orElse(null));
        assertNotNull(facturaActualizado);
        assertEquals("FAC000099", facturaActualizado.getNum_factura());

    }

    @Test
    public void delete() {
        if (facturaRepository.existsById(86)) {
            facturaRepository.deleteById(86);
        }
        assertFalse(facturaRepository.existsById(86), "El id=86 deberia haberse eliminado");
    }


}
