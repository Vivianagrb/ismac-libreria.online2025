package com.distribuida.controller;

import com.distribuida.model.*;
import com.distribuida.service.LibroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class LibroControllerTestUnitaria {
    @InjectMocks
    private LibroController libroController;
    @Mock
    private LibroService libroService;
    private Libro libro;
    private Categoria categoria;
    private Autor autor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        libro = new Libro();
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
    }

    @Test
    public void testFindAll() {
        when(libroService.findAll()).thenReturn(List.of(libro));
        ResponseEntity<List<Libro>> respuesta = libroController.findAll();
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(1, respuesta.getBody().size());
        verify(libroService, times(1)).findAll();
    }

    @Test
    public void testFindOneExiste() {
        when(libroService.findOne(1)).thenReturn(libro);
        ResponseEntity<Libro> respuesta = libroController.findOne(1);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(libro.getTitulo(), respuesta.getBody().getTitulo());
    }

    @Test
    public void testFindOneNoExistente() {
        when(libroService.findOne(2)).thenReturn(null);
        ResponseEntity<Libro> respuesta = libroController.findOne(2);
        assertEquals(404, respuesta.getStatusCodeValue());
    }

    @Test
    public void testSave() {
        when(libroService.save(libro)).thenReturn(libro);
        ResponseEntity<Libro> respuesta = libroController.save(libro);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals("A", respuesta.getBody().getTitulo());
    }

    @Test
    public void testUpdateExistente() {
        when(libroService.update(1, libro)).thenReturn(libro);
        ResponseEntity<Libro> respuesta = libroController.update(1, libro);
        assertEquals(200, respuesta.getStatusCodeValue());
    }

    @Test
    public void testUpdateNoExistente() {
        when(libroService.update(eq(2), any(Libro.class))).thenReturn(null);
        ResponseEntity<Libro> respuesta = libroController.update(2, libro);
        assertEquals(200, respuesta.getStatusCodeValue());
    }

    @Test
    public void testDelete() {
        doNothing().when(libroService).delete(1);
        ResponseEntity<Void> respuesta = libroController.delete(1);
        assertEquals(204, respuesta.getStatusCodeValue());
        verify(libroService, times(1)).delete(1);

    }


}
