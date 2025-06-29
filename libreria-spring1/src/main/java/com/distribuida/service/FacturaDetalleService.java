package com.distribuida.service;
import com.distribuida.model.Factura_Detalle;

import java.util.List;

public interface FacturaDetalleService {
    public List<Factura_Detalle> findAll();
    public Factura_Detalle findOne(int id_factura_detalle);
    public  Factura_Detalle save(Factura_Detalle factura_detalle);
    public  Factura_Detalle update(int id_factura_detalle, Factura_Detalle factura_detalle);
    public void  delete(int id_factura_detalle);
}
