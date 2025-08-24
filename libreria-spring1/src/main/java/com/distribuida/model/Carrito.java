package com.distribuida.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "id_carrito")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrito")
    private Long idCarrito;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @Column(name = "token", unique = true)
    private String token;

    @JsonManagedReference
    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CarritoItem> items = new ArrayList<>();

    @Column(name = "subtotal", precision = 12, scale = 2)
    private BigDecimal subtotal = BigDecimal.ZERO;

    @Column(name = "descuento", precision = 12, scale = 2)
    private BigDecimal descuento = BigDecimal.ZERO;

    @Column(name = "impuesto", precision = 12, scale = 2)
    private BigDecimal impuesto = BigDecimal.ZERO;

    @Column(name = "total", precision = 12, scale = 2)
    private BigDecimal total = BigDecimal.ZERO;

    @Column(name = "actualizado_en")
    private LocalDateTime actualizadoEn;

    public void recomputarTotales(BigDecimal tasaIva) {
        subtotal = items.stream()
                .map(it -> {
                    BigDecimal pu = it.getPrecioUnitario() != null ? it.getPrecioUnitario() : BigDecimal.ZERO;
                    int cant = it.getCantidad() != null ? it.getCantidad() : 0;
                    BigDecimal tot = it.getTotal();
                    return (tot != null ? tot : pu.multiply(BigDecimal.valueOf(cant)));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (descuento == null) descuento = BigDecimal.ZERO;
        BigDecimal base = subtotal.subtract(descuento);
        if (base.signum() < 0) base = BigDecimal.ZERO;

        impuesto = base.multiply(tasaIva != null ? tasaIva : BigDecimal.ZERO)
                .setScale(2, RoundingMode.HALF_UP);
        total = base.add(impuesto).setScale(2, RoundingMode.HALF_UP);
        actualizadoEn = LocalDateTime.now();
    }

    public void recomprobacionTotalesCompat() {
        if (total == null) total = BigDecimal.ZERO;
        if (descuento == null) descuento = BigDecimal.ZERO;
        if (impuesto == null) impuesto = BigDecimal.ZERO;
    }

    // Getters y Setters
    public Long getIdCarrito() {
        return idCarrito;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getToken() {
        return token;
    }

    public List<CarritoItem> getItems() {
        return items;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public BigDecimal getImpuesto() {
        return impuesto;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public LocalDateTime getActualizadoEn() {
        return actualizadoEn;
    }

    public void setIdCarrito(Long idCarrito) {
        this.idCarrito = idCarrito;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setItems(List<CarritoItem> items) {
        this.items = items;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public void setImpuesto(BigDecimal impuesto) {
        this.impuesto = impuesto;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void setActualizadoEn(LocalDateTime actualizadoEn) {
        this.actualizadoEn = actualizadoEn;
    }
}
