package com.distribuida.principal;

import com.distribuida.entities.*;
import java.util.Date;

public class Factura_DetallePrincipal {

    public static void main(String[] arg){
        Cliente cliente = new Cliente(1,"0802304949","Viviana","Rodriguez","Quito","2604033","viviana@gmail.com");
        Categoria categoria = new Categoria(1,"Poemas","Mil poemas");
        Autor autor = new Autor(1,"Juan","Viteri","Ecuador","Guayaquil","09876543","juan@gmail.com");
        Libro libro = new Libro();
        libro.setId_libro(1);
        libro.setTitulo("A");
        libro.setEditorial("B");
        libro.setNum_paginas(100);
        libro.setEditorial("C");
        libro.setIdioma("Español");
        libro.setFecha_publicacion(new Date());
        libro.setDescripcion("D");
        libro.setTipo_pasta("E");
        libro.setIsbn("F");
        libro.setNum_ejemplares(10);
        libro.setPortada("G");
        libro.setPresentacion("H");
        libro.setPrecio(25.0);

        //inyeccion de dependencia
        libro.setCategoria(categoria);
        libro.setAutor(autor);

        Factura factura = new Factura();
        factura.setId_Factura(1);
        factura.setNum_factura("FACT-001");
        factura.setFecha(new Date());
        factura.setTotal_neto(100.0);
        factura.setIva(15.00);
        factura.setTotal(115.0);

        //inyeccion de dependencia
        factura.setCliente(cliente);

        Factura_Detalle factura_detalle = new Factura_Detalle();
        factura_detalle.setId_factura_detalle(1);
        factura_detalle.setCantidad(3);
        factura_detalle.setSubtotal(1000.0);

        //inyeccion de dependencia
        factura_detalle.setFactura(factura);
        factura_detalle.setLibro(libro);

        System.out.println(factura_detalle.toString());
    }

}












