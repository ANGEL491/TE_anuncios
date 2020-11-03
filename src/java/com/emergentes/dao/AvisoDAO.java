package com.emergentes.dao;

import com.emergentes.modelo.Aviso;
import java.util.List;

public interface AvisoDAO {

    //METODOS DE LA INTERFACE CRUD(Create,Read,Update,Delete)
    
    /*CREAMOS EL METODOS INSERT Y LANZARA UN (THROWS)
    EN CASO DE EXISTIR UNA EXCEPCION (ERROR)*/
    public void insert(Aviso aviso) throws Exception;

    public void update(Aviso aviso) throws Exception;

    public void delete(int id) throws Exception;

    public Aviso getById(int id) throws Exception;
    
//METODO QUE DEVUELVE COLECCION DE TIPO AVISO
    public List<Aviso> getAll() throws Exception;
}
/*SE crea la interfaz para implementar el CRUD de la tabla avisos*/
/*AHORA VAMOS A IMPLEMENTAR LA INTERFACE  CREADO LA CLASE 'AvisoDAOimpl'*/
