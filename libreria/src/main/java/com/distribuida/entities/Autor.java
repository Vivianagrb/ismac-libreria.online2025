package com.distribuida.entities;

public class Autor {
    private int id_autor;
    private String nombre;
    private String apellido;
    private String pais;
    private String direccion;
    private String telefono;
    private String correo;

    public Autor() {
    }
    public Autor(int id_autor, String nombre, String apellido, String pais, String direccion, String telefono, String correo) {

        this.id_autor = id_autor;
        this.nombre = nombre;
        this.apellido = apellido;
        this.pais = pais;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
    }

    public int getId_autor() {
        return id_autor;
    }
    public void setId_autor(int id_autor) {
        this.id_autor = id_autor;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id_autor=" + id_autor +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", pais='" + pais + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                '}';
    }
}