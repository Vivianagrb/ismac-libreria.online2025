package com.distribuida.principal;

import com.distribuida.entities.*;
import java.util.Date;

public class LibroPrincipal {
    public static void main(String[] arg){
        Categoria categoria = new Categoria(1,"Poemas","Mil poemas");
        Autor autor = new Autor(1,"Juan","Viteri","Ecuador","Guayaquil","09876543","juan@gmail.com");
        Libro libro = new Libro();
        libro.setId_libro(1);
        libro.setTitulo("A");
        libro.setEditorial("B");
        libro.setNum_paginas(100);
        libro.setEdicion("C");
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

        System.out.println(libro.toString());
    }

}