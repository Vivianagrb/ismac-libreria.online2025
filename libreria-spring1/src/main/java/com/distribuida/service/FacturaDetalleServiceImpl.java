package com.distribuida.service;

import com.distribuida.dao.FacturaDetalleRepository;
import com.distribuida.model.Factura_Detalle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public abstract class FacturaDetalleServiceImpl implements FacturaDetalleService {

    @Autowired
    private FacturaDetalleRepository facturaDetalleRepository;

    @Override
    public List<Factura_Detalle > findAll() {
        return facturaDetalleRepository.findAll();
    }

    @Override
    public Factura_Detalle findOne(int id) {

        Optional<Factura_Detalle> factura_detalle = facturaDetalleRepository.findById(id);
        return factura_detalle.orElse(null);
    }

    @Override
    public Factura_Detalle save(Factura_Detalle factura) {
        return facturaDetalleRepository.save(factura);
    }

    @Override
    public Factura_Detalle update(int id, Factura_Detalle factura_detalle) {

        Factura_Detalle facturaDetalleExistente = findOne(id);

        if(facturaDetalleExistente == null){
            return null;
        }
        facturaDetalleExistente.setCantidad(factura_detalle.getCantidad());
        facturaDetalleExistente.setSubtotal(factura_detalle.getSubtotal(22.00));
        return facturaDetalleRepository.save(facturaDetalleExistente);
    }

    @Override
    public void delete(int id) {


        if (facturaDetalleRepository.existsById(id)){
            facturaDetalleRepository.deleteById(id);
        }

    }
}

