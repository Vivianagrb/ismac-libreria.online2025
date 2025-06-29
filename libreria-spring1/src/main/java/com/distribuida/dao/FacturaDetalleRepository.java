package com.distribuida.dao;

import com.distribuida.model.Factura_Detalle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaDetalleRepository extends JpaRepository<Factura_Detalle, Integer> {

}
