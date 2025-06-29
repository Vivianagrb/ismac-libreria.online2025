package com.distribuida.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "factura")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura")
    private Integer id_Factura;
    @Column(name = "num_factura")
    private String num_factura;
    @Column(name = "fecha")
    private Date fecha;
    @Column(name = "total_neto")
    private Double total_neto;
    @Column(name = "iva")
    private Double iva;
    @Column(name = "total")
    private Double total;

    //patron de inyeccion de dependencia
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    public Factura() {
    }

    public Factura(int id_Factura, String num_factura, Date fecha, Double total_neto, Double iva, Double total, Cliente cliente) {
        this.id_Factura = id_Factura;
        this.num_factura = num_factura;
        this.fecha = fecha;
        this.total_neto = total_neto;
        this.iva = iva;
        this.total = total;
        this.cliente = cliente;
    }

    public Integer getId_Factura() {
        return id_Factura;
    }
    public void setId_Factura(Integer id_Factura) {
        this.id_Factura = id_Factura;
    }
    public String getNum_factura() {
        return num_factura;
    }
    public void setNum_factura(String num_factura) {
        this.num_factura = num_factura;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public Double getTotal_neto() {
        return total_neto;
    }
    public void setTotal_neto(Double total_neto) {
        this.total_neto = total_neto;
    }
    public Double getIva() {
        return iva;
    }
    public void setIva(Double iva) {
        this.iva = iva;
    }
    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }

    //metodo inyector de dependencia
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "id_Factura=" + id_Factura +
                ", num_factura='" + num_factura + '\'' +
                ", fecha=" + fecha +
                ", total_neto=" + total_neto +
                ", iva=" + iva +
                ", total=" + total +
                ", cliente=" + cliente +
                '}';
    }
}