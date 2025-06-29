import com.distribuida.entities.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTestUnitaria {
    private Cliente cliente;

    @BeforeEach
    public void setUp(){
        cliente= new Cliente(1,"0802304949","Viviana","Rodriguez","Marianas","2063386","viviana@gmail.com");
    }

    @Test

    public void testClienteConstructorAndGetters(){
        assertAll("Valida datos de Cliente",
                ()-> assertEquals(1,cliente.getIdCliente()),
                ()-> assertEquals("0802304949",cliente.getCedula()),
                ()-> assertEquals("Viviana",cliente.getNombre()),
                ()-> assertEquals("Rodriguez",cliente.getApellido()),
                ()-> assertEquals("Marianas",cliente.getDireccion()),
                ()-> assertEquals("2063386",cliente.getTelefono()),
                ()-> assertEquals("viviana@gmail.com",cliente.getCorreo())
               );
    }

@Test
    public void testClienteSetter(){
        cliente= new Cliente();
        cliente.setIdCliente(2);
        cliente.setCedula("08023049492");
        cliente.setNombre("Viviana2");
        cliente.setApellido("Rodriguez2");
        cliente.setDireccion("Marianas2");
        cliente.setTelefono("20633862");
        cliente.setCorreo("viviana2@gmail.com");

    assertAll("Valida datos de Cliente",
            ()-> assertEquals(2,cliente.getIdCliente()),
            ()-> assertEquals("08023049492",cliente.getCedula()),
            ()-> assertEquals("Viviana2",cliente.getNombre()),
            ()-> assertEquals("Rodriguez2",cliente.getApellido()),
            ()-> assertEquals("Marianas2",cliente.getDireccion()),
            ()-> assertEquals("20633862",cliente.getTelefono()),
            ()-> assertEquals("viviana2@gmail.com",cliente.getCorreo())
    );
}

@Test
    public void testClienteToString(){
        String str = cliente.toString();

        assertAll("Validar datos de cliente",
                () ->assertTrue(str.contains("1")),
                () ->assertTrue(str.contains("0802304949")),
                () ->assertTrue(str.contains("Viviana")),
                () ->assertTrue(str.contains("Rodriguez")),
                () ->assertTrue(str.contains("Marianas")),
                () ->assertTrue(str.contains("2063386")),
                () ->assertTrue(str.contains("viviana@gmail.com"))
                );
        }
}
