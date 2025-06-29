package com.distribuida.service;

import com.distribuida.model.Libro;

import java.util.List;

public interface LibroService {
    List<Libro> findAll();
    Libro findOne(int id_libro);
    Libro save(Libro libro);
    Libro update(int id_libro, Libro libro);
    void delete(int id_libro);
}
