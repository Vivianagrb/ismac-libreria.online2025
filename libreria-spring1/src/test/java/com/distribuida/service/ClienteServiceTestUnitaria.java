package com.distribuida.service;

import com.distribuida.dao.ClienteRepository;
import com.distribuida.model.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTestUnitaria {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;
    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente();
        cliente.setIdCliente(1);
        cliente.setCedula("0802304949");
        cliente.setNombre("Vivi");
        cliente.setApellido("Rodri");
        cliente.setDireccion("Marianas");
        cliente.setTelefono("0986951824");
        cliente.setCorreo("viviana@gmail.com");


    }

    @Test
    public void testFindAll() {
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));
        List<Cliente> clientes = clienteService.findAll();
        assertNotNull(clientes);
        assertEquals(1, clientes.size());
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    public void testFindOneExiste() {
        when(clienteRepository.findById(1)).thenReturn(Optional.ofNullable(cliente));
        Cliente cliente = clienteService.findOne(1);
        assertNotNull(cliente);
        assertEquals("Vivi", cliente.getNombre());
    }

    @Test

    public void testFindOneNoExistente() {
        when(clienteRepository.findById(2)).thenReturn(Optional.empty());
        Cliente cliente = clienteService.findOne(2);
        assertNull(cliente);
    }

    @Test
    public void testSave() {
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        Cliente cliente1 = clienteService.save(cliente);
        assertNotNull(cliente1);
        assertEquals("Vivi", cliente1.getNombre());
    }

    @Test
    public void testUpdateExistente() {
        Cliente clienteActualizado = new Cliente();
        clienteActualizado.setCedula("080230494922");
        clienteActualizado.setNombre("Vivi22");
        clienteActualizado.setApellido("Rodriguez");
        clienteActualizado.setDireccion("Marianas22");
        clienteActualizado.setTelefono("098695182422");
        clienteActualizado.setCorreo("vivianag22@gmail.com");

        when(clienteRepository.findById(1)).thenReturn(Optional.ofNullable(cliente));
        when(clienteRepository.save(any())).thenReturn(clienteActualizado);
        Cliente clienteResultado = clienteService.update(1, clienteActualizado);
        assertNotNull(clienteResultado);
        assertEquals("Vivi22", clienteResultado.getNombre());
        verify(clienteRepository, times(1)).save(cliente);

    }

    @Test
    public void testUpdateNoExistente() {
        Cliente clienteNuevo = new Cliente();
        when(clienteRepository.findById(999)).thenReturn(Optional.empty());
        Cliente clienteResultado = clienteService.update(999, clienteNuevo);
        assertNotNull(clienteResultado);
        verify(clienteRepository, never()).save(cliente);
    }

    @Test
    public void testDeleteExistente() {
        when(clienteRepository.existsById(1)).thenReturn(true);
        clienteService.delete(1);
        verify(clienteRepository).deleteById(1);

    }

    @Test
    public void testDeleteNoExistente() {
        when(clienteRepository.existsById(999)).thenReturn(false);
        clienteService.delete(999);
        verify(clienteRepository, never()).deleteById(anyInt());
    }
}
