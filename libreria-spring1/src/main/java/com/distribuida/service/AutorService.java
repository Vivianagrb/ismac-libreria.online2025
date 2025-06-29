    package com.distribuida.service;

    import com.distribuida.model.Autor;
    import java.util.List;

    public interface AutorService {
        public List<Autor> findAll();
        public Autor findOne(int id_autor);
        public Autor save(Autor autor);
        public Autor update(int id_autor, Autor autor);
        public void delete(int id_autor);

    }
