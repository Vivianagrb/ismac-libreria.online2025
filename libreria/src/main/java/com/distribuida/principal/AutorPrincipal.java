package com.distribuida.principal;

import com.distribuida.entities.Autor;
import com.distribuida.entities.Cliente;
import com.distribuida.entities.Factura;

import java.util.Date;

public class AutorPrincipal {
    public static void main(String[] arg){
        Autor autor = new Autor();
        autor.setId_autor(1);
        autor.setNombre("Juan");
        autor.setApellido("Viteri");
        autor.setPais("Ecuador");
        autor.setDireccion("Guayaquil");
        autor.setTelefono("098765432");
        autor.setCorreo("juan@gamil.com");


        System.out.println(autor.toString());



    }
}
