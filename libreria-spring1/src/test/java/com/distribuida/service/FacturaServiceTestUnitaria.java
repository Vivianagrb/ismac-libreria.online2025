package com.distribuida.service;

import com.distribuida.dao.FacturaRepository;
import com.distribuida.model.Cliente;
import com.distribuida.model.Factura;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FacturaServiceTestUnitaria {
    @Mock
    private FacturaRepository facturaRepository;

    @InjectMocks
    private FacturaServiceImpl facturaService;
    private Factura factura;
    private Cliente cliente;

    @BeforeEach
    public void setUp(){
        factura = new Factura();
        cliente = new Cliente(1, "0802304949", "Viviana", "Rodri", "maiana", "222222", "viviana@hotmail.cpm");
        factura.setId_Factura(1);
        factura.setNum_factura("FAC-001");
        factura.setFecha(new Date());
        factura.setTotal_neto(1000.000);
        factura.setIva(15.000);
        factura.setTotal(10150.000);
        factura.setCliente(cliente);


    }

    @Test
    public void testFindAll(){
        when(facturaRepository.findAll()).thenReturn(List.of(factura));
        List<Factura> facturas= facturaService.findAll();
        assertEquals(1,facturas.size());
        assertNotNull(facturas);
        verify(facturaRepository,times(1)).findAll();
    }

    @Test
    public void testFindOneExiste(){
        when(facturaRepository.findById(1)).thenReturn(Optional.ofNullable(factura));
        Factura factura= facturaService.findOne(1);
        assertNotNull(factura);
        assertEquals("FAC-001",factura.getNum_factura());
    }

    @Test
    public void testFindOneNoExistente(){
        when(facturaRepository.findById(2)).thenReturn(Optional.empty());
        Factura factura= facturaService.findOne(2);
        assertNull(factura);
    }

    @Test
    public void testSave(){
        when(facturaRepository.save(factura)).thenReturn(factura);
        Factura factura1= facturaService.save(factura);
        assertNotNull(factura1);
        assertEquals("FAC-001",factura1.getNum_factura());
    }

    @Test
    public void testUpdateExistente(){
        Factura facturaActualizado= new Factura();
        Cliente cliente = new Cliente(2,"0802","Viviana2","Rodri2","Mariana2","222333","vivi@gamail");
        facturaActualizado.setNum_factura("FAC-002");
        facturaActualizado.setFecha(new Date());
        facturaActualizado.setTotal_neto(200.00);
        facturaActualizado.setIva(30.00);
        facturaActualizado.setTotal(230.00);
        facturaActualizado.setCliente(cliente);

        when(facturaRepository.findById(1)).thenReturn(Optional.ofNullable(factura));
        when(facturaRepository.save(any())).thenReturn(facturaActualizado);

        Factura facturaResultado = facturaService.update(1,facturaActualizado);
        assertNotNull(facturaResultado);
        assertEquals("FAC-002",facturaResultado.getNum_factura());
        verify(facturaRepository,times(1)).save(factura);

    }

    @Test
    public void testUpdateNoExistente(){
        Factura factuaraNuevo= new Factura();
        when(facturaRepository.findById(999)).thenReturn(Optional.empty());
        Factura facturaResultado = facturaService.update(999,factuaraNuevo);
        assertNotNull(facturaResultado);
        verify(facturaRepository,never()).save(factura);
    }

    @Test
    public void testDeleteExistente(){
        when(facturaRepository.existsById(1)).thenReturn(true);
        facturaService.delete(1);
        verify(facturaService).delete(1);

    }

    @Test
    public void testDeleteNoExistente(){
        when(facturaRepository.existsById(999)).thenReturn(false);
        facturaService.delete(999);
        verify(facturaRepository,never()).deleteById(anyInt());
    }
}
