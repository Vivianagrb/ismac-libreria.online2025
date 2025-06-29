package com.distribuida.principal;

import com.distribuida.entities.Cliente;

public class ClientePrincipal {
    public static void main(String[] arg){
        Cliente cliente = new Cliente(1,"0802304949","Viviana","Rodriguez","Quito","2604033","viviana@gmail.com");
    System.out.println(cliente.toString());
    }

}
