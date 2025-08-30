package com.distribuida.controller;

import com.distribuida.model.Carrito;
import com.distribuida.model.Cliente;
import com.distribuida.service.CarritoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class CarritoControllerTestUnitaria {
    @InjectMocks
    private CarritoController carritoController;
    @Mock
    private CarritoService carritoService;
    private Carrito carrito;
    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        carrito = new Carrito();
        cliente = new Cliente(1, "0802304949", "Viviana", "Rodri", "maiana", "222222", "viviana@hotmail.cpm");
        carrito.setIdCarrito(1L);
        carrito.setCliente(cliente);
        carrito.getToken("001");
        carrito.setSubtotal(BigDecimal.valueOf(10.00));
        carrito.setDescuento(BigDecimal.valueOf(1.00));
        carrito.setImpuesto(BigDecimal.valueOf(5.00));
        carrito.setTotal(BigDecimal.valueOf(10150.00));
        carrito.setActualizadoEn(new Date());

    }

    @Test
    public void testFindAll() {
        when(carritoService.findAll()).thenReturn(List.of(carrito));
        ResponseEntity<List<Carrito>> respuesta = (ResponseEntity<List<Carrito>>) carritoController.findAll();
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(1, respuesta.getBody().size());
        verify(carritoService, times(1)).findAll();
    }

    @Test
    public void testFindOneExiste() {
        when(carritoService.findOne(1)).thenReturn(carrito);
        ResponseEntity<Carrito> respuesta = carritoController.findOne(1);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(carrito.getToken("001"), respuesta.getBody().getToken("001"));
    }

    @Test
    public void testFindOneNoExistente() {
        when(carritoService.findOne(2)).thenReturn(null);
        ResponseEntity<Carrito> respuesta = carritoController.findOne(2);
        assertEquals(404, respuesta.getStatusCodeValue());
    }

    @Test
    public void testSave() {
        when(carritoService.save(carrito)).thenReturn(carrito);
        Carrito respuesta = carritoController.save(carrito);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals("001", respuesta.getBody().getToken("001"));
    }

    @Test
    public void testUpdateExistente() {
        when(carritoService.update(1, carrito)).thenReturn(carrito);
        Carrito respuesta = carritoController.update(Long.valueOf(1), carrito);
        assertEquals(200, respuesta.getSubtotal() );
    }

    @Test
    public void testUpdateNoExistente() {
        when(carritoService.update(eq(2), any(Carrito.class))).thenReturn(null);
        Carrito respuesta = carritoController.update(Long.valueOf(2), carrito);
        assertEquals(200, respuesta.getSubtotal());
    }

    @Test
    public void testDelete() {
        doNothing().when(carritoService).delete(1);
        ResponseEntity<Void> respuesta = carritoController.delete(Long.valueOf(1));
        assertEquals(204, respuesta.getStatusCodeValue());
        verify(carritoService, times(1)).delete(1);

    }


}
