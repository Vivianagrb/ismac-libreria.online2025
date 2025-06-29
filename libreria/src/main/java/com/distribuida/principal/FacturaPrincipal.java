package com.distribuida.principal;

import com.distribuida.entities.Cliente;
import com.distribuida.entities.Factura;
import java.util.Date;

public class FacturaPrincipal {

        public static void main(String[] arg){
            Factura factura = new Factura();
            Cliente cliente = new Cliente(1,"0802304949","Viviana","Rodriguez","Quito","2604033","viviana@gmail.com");
            factura.setId_Factura(1);
            factura.setNum_factura("FACT-001");
            factura.setFecha(new Date());
            factura.setTotal_neto(100.0);
            factura.setIva(15.00);
            factura.setTotal(115.0);
            //inyeccion de dependencia
            factura.setCliente(cliente);

System.out.println(factura.toString());

        }
}
