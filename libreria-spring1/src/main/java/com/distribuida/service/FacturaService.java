package com.distribuida.service;
import com.distribuida.model.Factura;


import java.util.List;

public interface FacturaService {
    public List<Factura> findAll();
    public Factura findOne(int id_factura);
    public  Factura save(Factura factura);
    public  Factura update(int id_factura, Factura factura);
    public void delete(int id_factura);

}
