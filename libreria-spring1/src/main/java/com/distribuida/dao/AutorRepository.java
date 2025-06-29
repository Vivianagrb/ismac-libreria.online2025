package com.distribuida.dao;

//@Repository //Esto es un beans para gestionar persistencia con jpa

import com.distribuida.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Integer> {

}

