package com.distribuida.controller;

import com.distribuida.model.Cliente;
import com.distribuida.model.Factura;
import com.distribuida.service.FacturaService;
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

public class FacturaControllerTestUnitaria {
    @InjectMocks
    private FacturaController facturaController;
    @Mock
    private FacturaService facturaService;
    private Factura factura;
    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

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
    public void testFindAll() {
        when(facturaService.findAll()).thenReturn(List.of(factura));
        ResponseEntity<List<Factura>> respuesta = facturaController.findAll();
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(1, respuesta.getBody().size());
        verify(facturaService, times(1)).findAll();
    }

    @Test
    public void testFindOneExiste() {
        when(facturaService.findOne(1)).thenReturn(factura);
        ResponseEntity<Factura> respuesta = facturaController.findOne(1);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(factura.getNum_factura(), respuesta.getBody().getNum_factura());
    }

    @Test
    public void testFindOneNoExistente() {
        when(facturaService.findOne(2)).thenReturn(null);
        ResponseEntity<Factura> respuesta = facturaController.findOne(2);
        assertEquals(404, respuesta.getStatusCodeValue());
    }

    @Test
    public void testSave() {
        when(facturaService.save(factura)).thenReturn(factura);
        ResponseEntity<Factura> respuesta = facturaController.save(factura);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals("FAC-001", respuesta.getBody().getNum_factura());
    }

    @Test
    public void testUpdateExistente() {
        when(facturaService.update(1, factura)).thenReturn(factura);
        ResponseEntity<Factura> respuesta = facturaController.update(1, factura);
        assertEquals(200, respuesta.getStatusCodeValue());
    }

    @Test
    public void testUpdateNoExistente() {
        when(facturaService.update(eq(2), any(Factura.class))).thenReturn(null);
        ResponseEntity<Factura> respuesta = facturaController.update(2, factura);
        assertEquals(200, respuesta.getStatusCodeValue());
    }

    @Test
    public void testDelete() {
        doNothing().when(facturaService).delete(1);
        ResponseEntity<Void> respuesta = facturaController.delete(1);
        assertEquals(204, respuesta.getStatusCodeValue());
        verify(facturaService, times(1)).delete(1);

    }


}
