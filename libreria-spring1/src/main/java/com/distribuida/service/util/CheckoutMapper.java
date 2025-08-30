package com.distribuida.service.util;

import com.distribuida.model.Carrito;
import com.distribuida.model.CarritoItem;
import com.distribuida.model.Factura;
import com.distribuida.model.Factura_Detalle;

import java.util.Date;

public class CheckoutMapper {

    private CheckoutMapper(){}
    public static Factura construirFacturadesdeCarrito(Carrito carrito, String numFactura, double tasaIva ){

Factura f = new Factura();
f.setNum_factura(numFactura);
f.setFecha(new Date());
        f.setCliente(carrito.getCliente());
        double subtotal= carrito.getItems().stream()
.mapToDouble( i -> i.getTotal().doubleValue())
                .sum();
        double iva = Math.max(0, subtotal)* tasaIva;
        double total = subtotal + iva;
        f.setTotal_neto(subtotal);
        f.setIva(iva);
        f.setTotal(total);
        return f;
    }

    public static Factura_Detalle construirDetalle (Factura factura, CarritoItem carritoItem) {
        Factura_Detalle d = new Factura_Detalle();
        d.setFactura (factura);
        d.setLibro(carritoItem.getLibro());
        d.setCantidad(carritoItem.getCantidad());
        d.setSubtotal(carritoItem.getTotal().doubleValue());
        return d;
    }





}
