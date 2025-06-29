package com.distribuida.dao;

import com.distribuida.model.Cliente;
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
@Rollback(value = false)

public class ClienteRepositoryTestIt {

    @Autowired
    private ClienteRepository clienteRepository;


    @Test
    public void findAll() {
        List<Cliente> clientes = clienteRepository.findAll();
        assertNotNull(clientes);
        assertTrue(clientes.size() > 0);
        for (Cliente item : clientes) {
            System.out.println(item.toString());
        }

    }

    @Test
    public void findOne() {
        Optional<Cliente> cliente = clienteRepository.findById(1);
        System.out.println(cliente.toString());
    }

    @Test
    public void save() {
        Cliente cliente = new Cliente(0, "0802304949", "Viviana", "Rodriguez", "Marianas", "0986951824", "viviana@gmail.com");
        Cliente clienteGuargado = clienteRepository.save(cliente);
        assertNotNull(clienteGuargado);
        assertEquals("Viviana", clienteGuargado.getNombre());
        assertEquals("Rodriguez", clienteGuargado.getApellido());
    }

    @Test
    public void update() {
        Optional<Cliente> cliente = clienteRepository.findById(2);
        assertTrue(cliente.isPresent(), "El cliente no id =41 deberia existir");
        cliente.orElse(null).setCedula("080230494949");
        cliente.orElse(null).setNombre("Viviana22");
        cliente.orElse(null).setApellido("Rodriguez22");
        cliente.orElse(null).setDireccion("Mariana1");
        cliente.orElse(null).setTelefono("098695182424");
        cliente.orElse(null).setCorreo("viviana1@mail.com");
        Cliente clienteActualizado = clienteRepository.save(cliente.orElse(null));
        assertNotNull(clienteActualizado);
        assertEquals("Viviana22", clienteActualizado.getNombre());
        assertEquals("Rodriguez22", clienteActualizado.getApellido());
    }

    @Test
    public void delete() {
        if (clienteRepository.existsById(39)) {
            clienteRepository.deleteById(39);
        }
        assertFalse(clienteRepository.existsById(39), "El id=2 deberia haberse eliminado");
    }

}
