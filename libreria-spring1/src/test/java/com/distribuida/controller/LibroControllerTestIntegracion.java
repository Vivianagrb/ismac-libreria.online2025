package com.distribuida.controller;

import com.distribuida.model.*;
import com.distribuida.service.LibroService;
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
@WebMvcTest(LibroController.class)
public class LibroControllerTestIntegracion {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibroService libroService;
    private Categoria categoria;
    private Autor autor;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetLibro() throws Exception {
        Libro libro = new Libro();
        categoria = new Categoria(1, "Poemas", "Mil poemas");
        autor = new Autor(1, "Juan", "Viteri", "Ecuador", "Guayaquil", "09876543", "juan@gmail.com");
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

        Mockito.when(libroService.findAll()).thenReturn(List.of(libro));
        mockMvc.perform(get("/api/libro"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("A"));

    }

    @Test
    public void testPostLibro() throws Exception {

        Libro libro = new Libro();
        categoria = new Categoria(1, "Poemas", "Mil poemas");
        autor = new Autor(1, "Juan", "Viteri", "Ecuador", "Guayaquil", "09876543", "juan@gmail.com");
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
        Mockito.when(libroService.save(any(Libro.class))).thenReturn(libro);
        mockMvc.perform(post("/api/libro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(libro)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("A"));
    }

    @Test
    public void testDeleteLibro() throws Exception {
        mockMvc.perform(delete("/api/libro/1"))
                .andExpect(status().isNoContent());
    }

}

