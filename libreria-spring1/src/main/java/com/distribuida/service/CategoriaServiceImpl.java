package com.distribuida.service;

import com.distribuida.dao.CategoriaRepository;
import com.distribuida.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //es un bean de logica de negocio
public class CategoriaServiceImpl  implements CategoriaService {

    @Autowired //Anotacion para inyeccion de dependencia
    private CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria findOne(int id_categoria) {
        Optional<Categoria> categoria= categoriaRepository.findById(id_categoria);
        return categoria.orElse(null);
    }
    @Override
    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria update(int id_categoria, Categoria categoria) {
        Categoria categoriaExistente = findOne(id_categoria) ;
        if (categoriaExistente==null){
            return null;
        }
        categoriaExistente.setCategoria(categoria.getCategoria());
        categoriaExistente.setDescripcion(categoria.getDescripcion());
        return categoriaRepository.save(categoriaExistente);
    }

    @Override
    public void delete(int id_categoria) {
        if (categoriaRepository.existsById(id_categoria) ){
            categoriaRepository.deleteById(id_categoria);
        }
    }
}
