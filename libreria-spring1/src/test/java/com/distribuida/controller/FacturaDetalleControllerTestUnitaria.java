package com.distribuida.controller;

import com.distribuida.model.*;
import com.distribuida.service.FacturaDetalleService;
import com.distribuida.service.LibroService;
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

public class FacturaDetalleControllerTestUnitaria {
    @InjectMocks
    private FacturaDetalleController facturaDetalleController;
    @Mock
    private FacturaDetalleService facturaDetalleService;
    private Factura_Detalle facturaDetalle;
    private Factura factura;
    private Cliente cliente;
    private Libro libro;
    private Categoria categoria;
    private Autor autor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        facturaDetalle = new Factura_Detalle();
        cliente = new Cliente(1,"0802304949","Viviana","Rodriguez","Quito","2604033","viviana@gmail.com");
        factura = new Factura(1,"FAC-001",new Date(),115.00,15.00,120.00,cliente);
        categoria = new Categoria(1,"Poemas","Mil poemas");
        autor = new Autor(1,"Juan","Viteri","Ecuador","Guayaquil","09876543","juan@gmail.com");
        facturaDetalle.setId_factura_detalle(1);
        facturaDetalle.setCantidad(3);
        facturaDetalle.setSubtotal(1000.0);
        facturaDetalle.setFactura(factura);
        facturaDetalle.setLibro(libro);
    }

    @Test
    public void testFindAll() {
        when(facturaDetalleService.findAll()).thenReturn(List.of(facturaDetalle));
        ResponseEntity<List<Factura_Detalle>> respuesta = facturaDetalleController.findAll();
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(1, respuesta.getBody().size());
        verify(facturaDetalleService, times(1)).findAll();
    }
    @Test
    public void testFindOneExiste() {
        when(facturaDetalleService.findOne(1)).thenReturn(facturaDetalle);
        ResponseEntity<Factura_Detalle> respuesta = facturaDetalleController.findOne(1);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(facturaDetalle.getCantidad() , respuesta.getBody().getCantidad());
    }
    @Test
    public void testFindOneNoExistente() {
        when(facturaDetalleService.findOne(2)).thenReturn(null);
        ResponseEntity<Factura_Detalle> respuesta = facturaDetalleController.findOne(2);
        assertEquals(404, respuesta.getStatusCodeValue());
    }
    @Test
    public void testSave() {
        when(facturaDetalleService.save(facturaDetalle)).thenReturn(facturaDetalle);
        ResponseEntity<Factura_Detalle> respuesta = facturaDetalleController.save(facturaDetalle);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(3,  respuesta.getBody().getCantidad());
    }
    @Test
    public void testUpdateExistente() {
        when(facturaDetalleService.update(1,facturaDetalle)).thenReturn(facturaDetalle);
        ResponseEntity<Factura_Detalle> respuesta = facturaDetalleController.update(1,facturaDetalle);
        assertEquals(200, respuesta.getStatusCodeValue());
    }

    @Test
    public void testUpdateNoExistente() {
        when(facturaDetalleService.update(eq(2), any(Factura_Detalle.class))).thenReturn(null);
        ResponseEntity<Factura_Detalle> respuesta = facturaDetalleController.update(2,facturaDetalle);
        assertEquals(200, respuesta.getStatusCodeValue());
    }
    @Test
    public void testDelete() {
        doNothing().when(facturaDetalleService).delete(1);
        ResponseEntity<Void> respuesta = facturaDetalleController.delete(1);
        assertEquals(204, respuesta.getStatusCodeValue());
        verify(facturaDetalleService, times(1)).delete(1);

    }


}
