import com.distribuida.entities.Autor;
import com.distribuida.entities.Categoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CategoriaTestUnitaria {
    private Categoria categoria;

    @BeforeEach
    public void setUp(){
        categoria= new Categoria(1,"Poesia","Romanticismo");
    }

    @Test
    public void testCategoriaConstructorAndGetters(){
        assertAll("Valida datos de Categoria",
                ()-> assertEquals(1,categoria.getId_categoria()),
                ()-> assertEquals("Poesia",categoria.getCategoria()),
                ()-> assertEquals("Romanticismo",categoria.getDescripcion())
        );
    }
    @Test
    public void testCategoriaSetter(){
        categoria= new Categoria();
        categoria.setId_categoria(2);
        categoria.setCategoria("Poesia2");
        categoria.setDescripcion("Romanticismo2");

        assertAll("Valida datos de Cliente",
                ()-> assertEquals(2,categoria.getId_categoria()),
                ()-> assertEquals("Poesia2",categoria.getCategoria()),
                ()-> assertEquals("Romanticismo2",categoria.getDescripcion())
        );
    }

    @Test
    public void testCategoriaToString(){
        String str = categoria.toString();

        assertAll("Validar datos de Categoria",
                () ->assertTrue(str.contains("1")),
                () ->assertTrue(str.contains("Poesia")),
                () ->assertTrue(str.contains("Romanticismo"))
        );
    }
}


