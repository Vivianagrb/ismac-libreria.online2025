package com.distribuida.controller;

import com.distribuida.model.Carrito;
import com.distribuida.model.Cliente;
import com.distribuida.service.CarritoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("removal")
@WebMvcTest(CarritoController.class)

public class CarritoControllerTestIntegracion {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarritoService carritoService;
    private Cliente cliente;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetCarrito() throws Exception {

        Carrito carrito = new Carrito();
        cliente = new Cliente(1, "0802304949", "Viviana", "Rodri", "maiana", "222222", "viviana@hotmail.cpm");
        carrito.setIdCarrito(1L);
        carrito.setCliente(cliente);
        carrito.getToken("001");
        carrito.setSubtotal(BigDecimal.valueOf(10.00));
        carrito.setDescuento(BigDecimal.valueOf(1.00));
        carrito.setImpuesto(BigDecimal.valueOf(5.00));
        carrito.setTotal(BigDecimal.valueOf(10150.00));
        carrito.setActualizadoEn(new Date());
        Mockito.when(carritoService.findAll()).thenReturn(List.of(carrito));
        mockMvc.perform(get("/api/carrito"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].num_carrito").value("FAC-001"));

    }

    @Test
    public void testPostCarrito() throws Exception {

        Carrito carrito = new Carrito();
        cliente = new Cliente(1, "0802304949", "Viviana", "Rodri", "maiana", "222222", "viviana@hotmail.cpm");
        carrito.setIdCarrito(1L);
        carrito.setCliente(cliente);
        carrito.getToken("001");
        carrito.setSubtotal(BigDecimal.valueOf(10.00));
        carrito.setDescuento(BigDecimal.valueOf(1.00));
        carrito.setImpuesto(BigDecimal.valueOf(5.00));
        carrito.setTotal(BigDecimal.valueOf(10150.00));
        carrito.setActualizadoEn(new Date());
        Mockito.when(carritoService.save(any(Carrito.class))).thenReturn(carrito);

        mockMvc.perform(post("/api/carrito")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(carrito)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.num_carrito").value("001"));
    }

    @Test
    public void testDeleteCarrito() throws Exception{
        mockMvc.perform(delete("/api/carrito/1"))
                .andExpect(status().isNoContent());
    }

}




