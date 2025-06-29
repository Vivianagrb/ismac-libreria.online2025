package com.distribuida.service;

import com.distribuida.dao.LibroRepository;
import com.distribuida.model.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public abstract class LibroServiceImpl implements LibroService {
    @Autowired
    private LibroRepository libroRepository;

    @Override
    public List<Libro> findAll() {
        return libroRepository.findAll();
    }

    @Override
    public Libro findOne(int id) {

        Optional<Libro> libro = libroRepository.findById(id);
        return libro.orElse(null);
    }

    @Override
    public Libro save(Libro libro) {
        return libroRepository.save(libro);
    }

    @Override
    public Libro update(int id, Libro libro) {

        Libro libroExistente = findOne(id);

        if(libroExistente == null){
            return null;
        }
        libroExistente.setTitulo(libro.getTitulo());
        libroExistente.setEditorial(libro.getEditorial());
        libroExistente.setNum_paginas(libro.getNum_paginas()) ;
        libroExistente.setEdicion(libro.getEdicion());
        libroExistente.setIdioma(libro.getIdioma()) ;
        libroExistente.setFecha_publicacion(libro.getFecha_publicacion());
        libroExistente.setDescripcion(libro.getDescripcion());
        libroExistente.setTipo_pasta(libro.getTipo_pasta());
        libroExistente.setIsbn(libro.getIsbn());
        libroExistente.setNum_ejemplares(libro.getNum_ejemplares());
        libroExistente.setPortada(libro.getPortada());
        libroExistente.setPresentacion(libro.getPresentacion());
        libroExistente.setPrecio(libro.getPrecio());
        return libroRepository.save(libroExistente);
    }

    @Override
    public void delete(int id) {

        if (libroRepository.existsById(id)){
            libroRepository.deleteById(id);
        }

    }
}
