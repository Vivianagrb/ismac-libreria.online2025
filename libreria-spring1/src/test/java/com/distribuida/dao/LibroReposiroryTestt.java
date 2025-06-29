package com.distribuida.dao;

import com.distribuida.model.*;
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
public class LibroReposiroryTestt {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    LibroRepository libroRepository;

    @Test

    public void findAll() {
        List<Libro> libros = libroRepository.findAll();
        assertNotNull(libros);
        assertTrue(libros.size() > 0, "La lista de facturas debería contener elementos.");
        for (Libro item : libros) {
            System.out.println(item);
        }
    }

    @Test
    public void findOne() {
        Optional<Libro> libro = libroRepository.findById(20);
        assertTrue(libro.isPresent(), "El libro con id=20 debería existir.");
        assertEquals(7, libro.get().getCategoria().getId_categoria());
        assertEquals(26, libro.get().getAutor().getId_autor());
        System.out.println(libro.get());
    }

    @Test
    public void save() {
        Optional<Libro> libro = libroRepository.findById(1);
        Optional<Categoria> categoria = categoriaRepository.findById(21);
        Optional<Autor> autor = autorRepository.findById(5);
        assertTrue(libro.isPresent(), "El libro con id=1 debería existir.");
        Libro libros = new Libro();
        libros.setTitulo("pinocho2");
        libros.setEditorial("mappa2");
        libros.setNum_paginas(22);
        libros.setEdicion("Ed 22");
        libros.setIdioma("Español");
        libros.setFecha_publicacion(new Date());
        libros.setDescripcion("Infantil");
        libros.setTipo_pasta("pasta roja");
        libros.setIsbn("444-333333");
        libros.setNum_ejemplares(77);
        libros.setPortada("portada");
        libros.setPresentacion("fisica");
        libros.setPrecio(20.00);
        libros.setCategoria(categoria.orElse(null));
        libros.setAutor(autor.orElse(null));
        Libro libroGuardado = libroRepository.save(libros);
        assertNotNull(libroGuardado);
        assertEquals("pinocho1", libroGuardado.getTitulo());
        assertEquals("mappa1", libroGuardado.getEditorial());
    }

    @Test
    public void update() {
        Optional<Libro> libroExistente = libroRepository.findById(80);
        Optional<Categoria> categoriaExistente = categoriaRepository.findById(2);
        Optional<Autor> autotExistente = autorRepository.findById(2);
        assertNotNull(libroExistente);
        assertNotNull(categoriaExistente);
        assertNotNull(autotExistente);

        libroExistente.orElse(null).setTitulo("pinocho 22");
        libroExistente.orElse(null).setEditorial("mappa 222");
        libroExistente.orElse(null).setNum_paginas(40);
        libroExistente.orElse(null).setEdicion("Ed 44");
        libroExistente.orElse(null).setIdioma("Español");
        libroExistente.orElse(null).setFecha_publicacion(new Date());
        libroExistente.orElse(null).setDescripcion("Infdantil 44");
        libroExistente.orElse(null).setTipo_pasta("pasta negra");
        libroExistente.orElse(null).setIsbn("444-5555555");
        libroExistente.orElse(null).setNum_ejemplares(10);
        libroExistente.orElse(null).setPortada("portada");
        libroExistente.orElse(null).setPresentacion("virtual");
        libroExistente.orElse(null).setPrecio(20.00);
        libroExistente.orElse(null).setCategoria(categoriaExistente.orElse(null));
        libroExistente.orElse(null).setAutor(autotExistente.orElse(null));
        Libro libroActualizado = libroRepository.save(libroExistente.orElse(null));
        assertNotNull(libroActualizado);
        assertEquals("pinocho 22",libroActualizado.getTitulo());
    }
    @Test
    public void delete() {
        if (libroRepository.existsById(80)) {
            libroRepository.deleteById(80);
        }
        assertFalse(libroRepository.existsById(80), "El id=80 deberia haberse eliminado");
    }

}