package com.distribuida.controller;

import com.distribuida.model.Cliente;
import com.distribuida.model.Factura;
import com.distribuida.service.FacturaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("removal")
@WebMvcTest(FacturaController.class)

public class FacturaControllerTestIntegracion {

       @Autowired
        private MockMvc mockMvc;

        @MockBean
        private FacturaService facturaService;
        private Cliente cliente;

        @Autowired
        private ObjectMapper objectMapper;

        @Test
        public void testGetFactura() throws Exception {

            Factura factura = new Factura();
            cliente = new Cliente(1, "0802304949", "Viviana", "Rodri", "maiana", "222222", "viviana@hotmail.cpm");
            factura.setId_Factura(1);
            factura.setNum_factura("FAC-001");
            factura.setFecha(new Date());
            factura.setTotal_neto(1000.000);
            factura.setIva(15.000);
            factura.setTotal(10150.000);
            factura.setCliente(cliente);
            Mockito.when(facturaService.findAll()).thenReturn(List.of(factura));
            mockMvc.perform(get("/api/factura"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].num_factura").value("FAC-001"));

        }

        @Test
        public void testPostFactura() throws Exception {

            Factura factura = new Factura();
            cliente = new Cliente(1, "0802304949", "Viviana", "Rodri", "maiana", "222222", "viviana@hotmail.cpm");
            factura.setId_Factura(1);
            factura.setNum_factura("FAC-001");
            factura.setFecha(new Date());
            factura.setTotal_neto(1000.000);
            factura.setIva(15.000);
            factura.setTotal(10150.000);
            factura.setCliente(cliente);
            Mockito.when(facturaService.save(any(Factura.class))).thenReturn(factura);

            mockMvc.perform(post("/api/factura")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(factura)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.num_factura").value("FAC-001"));
        }

        @Test
        public void testDeleteFactura() throws Exception{
            mockMvc.perform(delete("/api/factura/1"))
                    .andExpect(status().isNoContent());
        }

    }




