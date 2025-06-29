package com.distribuida.entities;

public class Factura_Detalle {
    private int id_factura_detalle;
    private int cantidad;
    private Double subtotal;
    //patron de inyeccion de dependencia
    private Factura factura;
    private Libro libro;


    public Factura_Detalle() {
    }

    public Factura_Detalle(int id_factura_detalle, int cantidad, Double subtotal, Factura factura,Libro libro) {
        this.id_factura_detalle = id_factura_detalle;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.factura = factura;
        this.libro = libro;

    }

    public int getId_factura_detalle() {
        return id_factura_detalle;
    }

    public void setId_factura_detalle(int id_factura_detalle) {
        this.id_factura_detalle = id_factura_detalle;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
    //metodo inyector de dependencia
        public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    @Override
    public String toString() {
        return "Factura_Detalle{" +
                "id_factura_detalle=" + id_factura_detalle +
                ", cantidad=" + cantidad +
                ", subtotal=" + subtotal +
                ", factura=" + factura +
                ", libro=" + libro +
                '}';
    }
}
