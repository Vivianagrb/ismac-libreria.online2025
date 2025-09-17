import { Autor } from "./autor.model"
import { Categoria } from "./categoria.model"

export interface Libro {
    id_libro: number
    titulo: string
    editorial: string
    num_paginas: number
    edicion: string
    idioma: string
    fecha_publicacion: string
    descripcion: string
    tipo_pasta: string
    iSBN: string
    num_ejemplares: number
    portada: string
    presentacion: string
    precio: number
    categoria: Categoria
    autor: Autor
    [key: string]: any;
    
}