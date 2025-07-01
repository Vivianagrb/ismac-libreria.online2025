package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AutorTestUnitaria {

    private Autor autor;

    @BeforeEach
    public void setUp(){
        autor= new Autor(1,"Juan","Viteri","Ecuador","Marianas","09876543","juan@gmail.com");
    }

    @Test
    public void testAutorConstructorAndGetters(){
        assertAll("Valida datos de Autor",
                ()-> assertEquals(1,autor.getId_autor()),
                ()-> assertEquals("Juan",autor.getNombre()),
                ()-> assertEquals("Viteri",autor.getApellido()),
                ()-> assertEquals("Ecuador",autor.getPais()),
                ()-> assertEquals("Marianas",autor.getDireccion()),
                ()-> assertEquals("09876543",autor.getTelefono()),
                ()-> assertEquals("juan@gmail.com",autor.getCorreo())
        );
    }

    @Test
    public void testAutorSetter(){
        autor= new Autor();
        autor.setId_autor(2);
        autor.setNombre("Juan");
        autor.setApellido("Viteri");
        autor.setPais("Ecuador");
        autor.setDireccion("Marianas2");
        autor.setTelefono("09876543");
        autor.setCorreo("juan@gmail.com");

        assertAll("Valida datos de Autor",
                ()-> assertEquals(2,autor.getId_autor()),
                ()-> assertEquals("Juan",autor.getNombre()),
                ()-> assertEquals("Viteri",autor.getApellido()),
                ()-> assertEquals("Ecuador",autor.getPais()),
                ()-> assertEquals("Marianas2",autor.getDireccion()),
                ()-> assertEquals("09876543",autor.getTelefono()),
                ()-> assertEquals("juan@gmail.com",autor.getCorreo())
        );
    }
    @Test
    public void testAutorToString(){
        String str = autor.toString();

        assertAll("Validar datos de Autor",
                () ->assertTrue(str.contains("1")),
                () ->assertTrue(str.contains("Juan")),
                () ->assertTrue(str.contains("Viteri")),
                () ->assertTrue(str.contains("Ecuador")),
                () ->assertTrue(str.contains("Marianas")),
                () ->assertTrue(str.contains("09876543")),
                () ->assertTrue(str.contains("juan@gmail.com"))
        );
    }
}
