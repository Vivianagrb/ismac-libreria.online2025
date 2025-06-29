package com.distribuida.service;
import com.distribuida.dao.AutorRepository;
import com.distribuida.model.Autor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //es un bean de logica de negocio

public class AutorServiceImpl implements AutorService{

    @Autowired //Anotacion para inyeccion de dependencia
    private AutorRepository autorRepository;

    @Override
    public List<Autor> findAll() {
        return autorRepository.findAll();
    }

    @Override
    public Autor findOne(int id_autor) {
        Optional<Autor> autor= autorRepository.findById(id_autor);
        return autor.orElse(null);
    }
    @Override
    public Autor save(Autor autor) {
        return autorRepository.save(autor);
    }

    @Override
    public Autor update(int id_autor, Autor autor) {
        Autor autorExistente = findOne(id_autor) ;
        if (autorExistente==null){
            return null;
        }
        autorExistente.setNombre(autor.getNombre());
        autorExistente.setApellido(autor.getApellido());
        autorExistente.setPais(autor.getPais());
        autorExistente.setDireccion(autor.getDireccion());
        autorExistente.setTelefono(autor.getTelefono());
        autorExistente.setCorreo(autor.getCorreo());
        return autorRepository.save(autorExistente);

    }

    @Override
    public void delete(int id_autor) {
        if (autorRepository.existsById(id_autor) ){
            autorRepository.deleteById(id_autor);
        }
    }
}
