package com.distribuida.dao;

import com.distribuida.model.Categoria;
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
public class CategoriaRepositoryTestIt {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    public void findAll() {
        List<Categoria> categorias = categoriaRepository.findAll();
        assertNotNull(categorias, "La lista de categorías no debe ser nula.");
        assertFalse(categorias.isEmpty(), "La lista de categorías debería contener al menos un elemento.");
        categorias.forEach(System.out::println);
    }

    @Test
    public void findOne() {
        int id = 1;
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        assertTrue(categoria.isPresent(), "La categoría con id=" + id + " debería existir.");
        System.out.println(categoria.get());
    }

    @Test
    public void save() {
        Categoria categoria = new Categoria(0, "Comedia 1", "En esta categoría están comedias importantes");
        Categoria categoriaGuardada = categoriaRepository.save(categoria);
        assertNotNull(categoriaGuardada, "La categoría guardada no debe ser nula.");
        assertEquals("Comedia 1", categoriaGuardada.getCategoria());
        assertEquals("En esta categoría están comedias importantes", categoriaGuardada.getDescripcion());
    }

    @Test
    public void update() {
        Optional<Categoria> categoria = categoriaRepository.findById(51);
        assertTrue(categoria.isPresent(), "La categoría  51 debería existir.");
        Categoria existente = categoria.get();
        existente.setCategoria("Comedia 2");
        existente.setDescripcion("En esta categoría están comedias importantes 2");

        Categoria categoriaActualizada = categoriaRepository.save(existente);
        assertNotNull(categoriaActualizada);
        assertEquals("Comedia 2", categoriaActualizada.getCategoria());
        assertEquals("En esta categoría están comedias importantes 2", categoriaActualizada.getDescripcion());
    }

    @Test
    public void delete() {
        if (categoriaRepository.existsById(52)) {
            categoriaRepository.deleteById(52);
        }
        assertFalse(categoriaRepository.existsById(52), "La categoría con id=52 debería haberse eliminado.");
    }
}
