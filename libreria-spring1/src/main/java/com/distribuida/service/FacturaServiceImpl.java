package com.distribuida.service;


import com.distribuida.dao.FacturaRepository;
import com.distribuida.model.Factura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public abstract class FacturaServiceImpl implements FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Override
    public List<Factura> findAll() {
        return facturaRepository.findAll();
    }

    @Override
    public Factura findOne(int id) {

        Optional<Factura> factura = facturaRepository.findById(id);
        return factura.orElse(null);
    }

    @Override
    public Factura save(Factura factura) {
        return facturaRepository.save(factura);
    }

    @Override
    public Factura update(int id, Factura factura) {

        Factura facturaExistente = findOne(id);

        if(facturaExistente == null){
            return null;
        }
        facturaExistente.setNum_factura(factura.getNum_factura());
        facturaExistente.setFecha(factura.getFecha());
        facturaExistente.setTotal_neto(factura.getTotal_neto());
        facturaExistente.setIva(factura.getIva());
        facturaExistente.setTotal(factura.getTotal());

       return facturaRepository.save(facturaExistente);
    }

    @Override
    public void delete(int id) {

        if (facturaRepository.existsById(id)){
            facturaRepository.deleteById(id);
        }

    }
}
