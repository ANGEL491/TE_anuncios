package com.emergentes.modelo;

public class Aviso {
//ATRIBUTOS

    private int id;
    private String titulo;
    private String contenido;

    //METODOS GETTER Y SETTER
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    //
    @Override
    public String toString() {
        return "Aviso{" + "id=" + id + ", titulo=" + titulo + ", contenido=" + contenido + '}';
    }

}
/*Para mapear la tabla se usa el JavaBean Aviso*/
 /*POSTERIORMENTE CREAMOS EL PATRON DAO Y CREAMOS LA INTERFACE 'AvisoDAO'*/
