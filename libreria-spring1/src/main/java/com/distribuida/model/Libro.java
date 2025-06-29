package com.distribuida.model;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "libro")

public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro")
    private Integer id_libro;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "editorial")
    private String editorial;
    @Column(name = "num_paginas")
    private Integer num_paginas;
    @Column(name = "edicion")
    private String edicion;
    @Column(name = "idioma")
    private String idioma;
    @Column(name = "fecha_publicacion")
    private Date fecha_publicacion;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "tipo_pasta")
    private String tipo_pasta;
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "num_ejemplares")
    private int num_ejemplares;
    @Column(name = "portada")
    private String portada;
    @Column(name = "presentacion")
    private String presentacion;
    @Column(name = "precio")
    private Double precio;

    //patron de inyeccion de dependencia
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "id_autor")
    private Autor autor;

    public Libro() {
    }

    public Libro(int id_libro, String titulo, String editorial, int num_paginas, String edicion, String idioma, Date fecha_publicacion, String descripcion, String tipo_pasta, String isbn, int num_ejemplares, String portada, String presentacion, Double precio, Categoria categoria, Autor autor) {
        this.id_libro = id_libro;
        this.titulo = titulo;
        this.editorial = editorial;
        this.num_paginas = num_paginas;
        this.edicion = edicion;
        this.idioma = idioma;
        this.fecha_publicacion = fecha_publicacion;
        this.descripcion = descripcion;
        this.tipo_pasta = tipo_pasta;
        this.isbn = isbn;
        this.num_ejemplares = num_ejemplares;
        this.portada = portada;
        this.presentacion = presentacion;
        this.precio = precio;
        this.categoria = categoria;
        this.autor = autor;
    }

    public int getId_libro() {
        return id_libro;
    }

    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getNum_paginas() {
        return num_paginas;
    }

    public void setNum_paginas(int num_paginas) {
        this.num_paginas = num_paginas;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Date getFecha_publicacion() {
        return fecha_publicacion;
    }

    public void setFecha_publicacion(Date fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo_pasta() {
        return tipo_pasta;
    }

    public void setTipo_pasta(String tipo_pasta) {
        this.tipo_pasta = tipo_pasta;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getNum_ejemplares() {
        return num_ejemplares;
    }

    public void setNum_ejemplares(int num_ejemplares) {
        this.num_ejemplares = num_ejemplares;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    //metodo inyector de dependencia
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id_libro=" + id_libro +
                ", titulo='" + titulo + '\'' +
                ", editorial='" + editorial + '\'' +
                ", num_paginas=" + num_paginas +
                ", edicion='" + edicion + '\'' +
                ", idioma='" + idioma + '\'' +
                ", fecha_publicacion=" + fecha_publicacion +
                ", descripcion='" + descripcion + '\'' +
                ", tipo_pasta='" + tipo_pasta + '\'' +
                ", isbn='" + isbn + '\'' +
                ", num_ejemplares=" + num_ejemplares +
                ", portada='" + portada + '\'' +
                ", presentacion='" + presentacion + '\'' +
                ", precio=" + precio +
                ", categoria=" + categoria +
                ", autor=" + autor +
                '}';
    }
}
