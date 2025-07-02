package com.distribuida.controller;

import com.distribuida.model.*;
import com.distribuida.service.FacturaDetalleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("removal")
@WebMvcTest(FacturaDetalleController.class)
public class FacturaDetalleControllerTestIntegracion {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacturaDetalleService facturaDetalleService;
    private Factura factura;
    private Cliente cliente;
    private Libro libro;
    private Categoria categoria;
    private Autor autor;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetFacturaDetalle() throws Exception {
        Factura_Detalle factura_detalle = new Factura_Detalle();
        factura_detalle.setCantidad(5);
        factura_detalle.setSubtotal(1000.00);

        cliente = new Cliente(1, "0802304949", "Viviana", "Rodri", "maiana", "222222", "viviana@hotmail.cpm");
        factura = new Factura();
        factura.setId_Factura(1);
        factura.setNum_factura("FAC-001");
        factura.setFecha(new Date());
        factura.setTotal_neto(1000.000);
        factura.setIva(15.000);
        factura.setTotal(10150.000);
        factura.setCliente(cliente);
        categoria = new Categoria(1, "Poemas", "Mil poemas");
        autor = new Autor(1, "Juan", "Viteri", "Ecuador", "Guayaquil", "09876543", "juan@gmail.com");
        libro = new Libro();
        libro.setId_libro(1);
        libro.setTitulo("A");
        libro.setEditorial("B");
        libro.setNum_paginas(100);
        libro.setEdicion("C");
        libro.setIdioma("Español");
        libro.setFecha_publicacion(new Date());
        libro.setDescripcion("D");
        libro.setTipo_pasta("E");
        libro.setIsbn("F");
        libro.setNum_ejemplares(10);
        libro.setPortada("G");
        libro.setPresentacion("H");
        libro.setPrecio(25.0);
        libro.setCategoria(categoria);
        libro.setAutor(autor);
        factura_detalle.setFactura(factura);
        factura_detalle.setLibro(libro);

        Mockito.when(facturaDetalleService.findAll()).thenReturn(List.of(factura_detalle));
        mockMvc.perform(get("/api/factura_detalle"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cantidad").value(5));

    }

    @Test
    public void testPostFacturaDetalle() throws Exception {
        Factura_Detalle factura_detalle = new Factura_Detalle();
        factura_detalle.setCantidad(5);
        factura_detalle.setSubtotal(1000.00);

        cliente = new Cliente(1, "0802304949", "Viviana", "Rodri", "maiana", "222222", "viviana@hotmail.cpm");
        factura = new Factura();
        factura.setId_Factura(1);
        factura.setNum_factura("FAC-001");
        factura.setFecha(new Date());
        factura.setTotal_neto(1000.000);
        factura.setIva(15.000);
        factura.setTotal(10150.000);
        factura.setCliente(cliente);
        categoria = new Categoria(1, "Poemas", "Mil poemas");
        autor = new Autor(1, "Juan", "Viteri", "Ecuador", "Guayaquil", "09876543", "juan@gmail.com");
        libro = new Libro();
        libro.setId_libro(1);
        libro.setTitulo("A");
        libro.setEditorial("B");
        libro.setNum_paginas(100);
        libro.setEdicion("C");
        libro.setIdioma("Español");
        libro.setFecha_publicacion(new Date());
        libro.setDescripcion("D");
        libro.setTipo_pasta("E");
        libro.setIsbn("F");
        libro.setNum_ejemplares(10);
        libro.setPortada("G");
        libro.setPresentacion("H");
        libro.setPrecio(25.0);
        libro.setCategoria(categoria);
        libro.setAutor(autor);
        factura_detalle.setFactura(factura);
        factura_detalle.setLibro(libro);

        Mockito.when(facturaDetalleService.save(any(Factura_Detalle.class))).thenReturn(factura_detalle);

        mockMvc.perform(post("/api/factura_detalle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(factura_detalle)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cantidad").value(5));
    }

    @Test
    public void testDeleteFacturaDetalle() throws Exception {
        mockMvc.perform(delete("/api/factura_detalle/1"))
                .andExpect(status().isNoContent());
    }

}

