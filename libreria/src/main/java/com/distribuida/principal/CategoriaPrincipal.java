package com.distribuida.principal;

import com.distribuida.entities.Autor;
import com.distribuida.entities.Categoria;

public class CategoriaPrincipal {
    public static void main(String[] arg){
        Categoria categoria = new Categoria();
        categoria.setId_categoria(1);
        categoria.setCategoria("Poemas");
        categoria.setDescripcion("Mil poemas");
        System.out.println(categoria.toString());



    }



}
