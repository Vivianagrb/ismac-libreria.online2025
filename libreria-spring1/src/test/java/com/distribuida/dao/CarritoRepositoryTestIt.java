package com.distribuida.dao;

import com.distribuida.model.Carrito;
import com.distribuida.model.Cliente;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(false)
public class CarritoRepositoryTestIt {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void findAll() {
        List<Carrito> carritos = carritoRepository.findAll();
        assertNotNull(carritos);
        assertTrue(carritos.size() > 0, "La lista de carritos debería contener elementos.");
        carritos.forEach(System.out::println);
    }

    @Test
    public void findOne() {
        Optional<Carrito> carritoOpt = carritoRepository.findById(1L);
        assertTrue(carritoOpt.isPresent(), "El carrito con id=1 debería existir.");

        Carrito carrito = carritoOpt.get();
        assertEquals(1L, carrito.getIdCarrito());
        System.out.println(carrito);
    }

    @Test
    public void save() {
        Optional<Cliente> clienteOpt = clienteRepository.findById(1);
        assertTrue(clienteOpt.isPresent(), "El cliente con id=1 debería existir.");

        Cliente cliente = clienteOpt.get();

        Carrito carrito = new Carrito();
        carrito.setCliente(cliente);
        carrito.setToken("c3");
        carrito.setSubtotal(BigDecimal.valueOf(100.00));
        carrito.setDescuento(BigDecimal.valueOf(10.00)); // corregido
        carrito.setImpuesto(BigDecimal.valueOf(12.00));  // corregido
        carrito.setTotal(BigDecimal.valueOf(102.00));    // corregido
        carrito.setActualizadoEn(LocalDateTime.now());

        Carrito carritoGuardado = carritoRepository.save(carrito);
        assertNotNull(carritoGuardado);
        assertEquals("c3", carritoGuardado.getToken());
        assertEquals(BigDecimal.valueOf(102.00), carritoGuardado.getTotal());
    }

    @Test
    public void update() {
        Optional<Carrito> carritoOpt = carritoRepository.findById(1L);
        assertTrue(carritoOpt.isPresent(), "El carrito con id=1 debería existir.");

        Carrito carrito = carritoOpt.get();
        carrito.setToken("c11");
        carrito.setSubtotal(BigDecimal.valueOf(150.00));
        carrito.setDescuento(BigDecimal.valueOf(15.00));
        carrito.setImpuesto(BigDecimal.valueOf(18.00));
        carrito.setTotal(BigDecimal.valueOf(153.00));
        carrito.setActualizadoEn(LocalDateTime.now());

        Carrito carritoActualizado = carritoRepository.save(carrito);
        assertNotNull(carritoActualizado);
        assertEquals("c11", carritoActualizado.getToken());
        assertEquals(BigDecimal.valueOf(153.00), carritoActualizado.getTotal());
    }
}
