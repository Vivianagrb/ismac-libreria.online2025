package com.distribuida.dao;

import com.distribuida.model.Autor;
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
public class AutorRepositoryTestIt {

    @Autowired
    private AutorRepository autorRepository;

    @Test
    public void findAll() {
        List<Autor> autores = autorRepository.findAll();
        assertNotNull(autores);
        assertTrue(autores.size() > 0);
        for (Autor item : autores) {
            System.out.println(item);
        }
    }

    @Test
    public void findOne() {
        Optional<Autor> autor = autorRepository.findById(1);
        assertTrue(autor.isPresent(), "El autor con id=1 debería existir");
        System.out.println(autor.get());
    }

    @Test
    public void save() {
        Autor autor = new Autor(0, "Juan", "Viteri", "Colombia", "Marianas 11", "0986951824", "juan@gmail.com");
        Autor autorGuardado = autorRepository.save(autor);

        assertNotNull(autorGuardado);
        assertEquals("Juan", autorGuardado.getNombre());
        assertEquals("Viteri", autorGuardado.getApellido());
    }

    @Test
    public void update() {
        Optional<Autor> autor = autorRepository.findById(54);
        assertTrue(autor.isPresent(), "El autor con id=54 debería existir");

        Autor existente = autor.get();
        existente.setNombre("Viviana22");
        existente.setApellido("Rodriguez22");
        existente.setPais("Venezuela");
        existente.setDireccion("Mariana1");
        existente.setTelefono("098695182424");
        existente.setCorreo("viviana1@mail.com");

        Autor autorActualizado = autorRepository.save(existente);
        assertNotNull(autorActualizado);
        assertEquals("Viviana22", autorActualizado.getNombre());
        assertEquals("Rodriguez22", autorActualizado.getApellido());
    }

    @Test
    public void delete() {
        int id = 8;
        if (autorRepository.existsById(id)) {
            autorRepository.deleteById(id);
        }
        assertFalse(autorRepository.existsById(id), "El autor con id=8 debería haberse eliminado");
    }
}
