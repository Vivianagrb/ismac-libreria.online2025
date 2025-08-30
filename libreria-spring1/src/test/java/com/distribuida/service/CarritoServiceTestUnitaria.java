package com.distribuida.service;

import com.distribuida.dao.CarritoRepository;
import com.distribuida.model.Carrito;
import com.distribuida.model.Cliente;
import com.distribuida.model.Libro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class CarritoServiceTestUnitaria {

    @Mock
    private CarritoRepository carritoRepository;

    @InjectMocks
    private CarritoServiceImpl carritoService;

    private Carrito carrito;
    private Cliente cliente;
    private Libro libro;
    private int cantidad;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente(1, "0802304949", "Viviana", "Rodri", "maiana", "222222", "viviana@hotmail.com");

        carrito = new Carrito();
        carrito.setCliente(cliente);
        carrito.setToken("c1");
        carrito.setSubtotal(BigDecimal.valueOf(100.00));
        carrito.setDescuento(BigDecimal.valueOf(10.00));
        carrito.setImpuesto(BigDecimal.valueOf(12.00));
        carrito.setTotal(BigDecimal.valueOf(115.00));
        carrito.setActualizadoEn(LocalDateTime.now());

       }


}