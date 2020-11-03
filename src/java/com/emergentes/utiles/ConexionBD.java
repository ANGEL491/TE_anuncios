/*Esta clase permitira la conexion a la base de datos*/
package com.emergentes.utiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    /*variables para ACCEDER A al la BD*/
    static String driver = "com.mysql.jdbc.Driver";
    static String url = "jdbc:mysql://localhost:3306/bd_anuncios";
    static String usuario = "root";
    static String password = "";

    //VARIABLE PARA CONECTAR Y EJECUTAR UNA QUERY EN LA BD
    protected Connection conn = null;

    //CONSTRUCTOR 
    public ConexionBD() {

        try {
            //ESPECIFICACION DEL DRIVER
            Class.forName(driver);
            //CREAR LA CONEXION A LA BASE DE DATOS
            conn = DriverManager.getConnection(url, usuario, password);

//VERIFICAR SI LA CONEXION ES VALIDA
            if (conn != null) {
                System.out.println("Conexion ok Existosa....:" + conn);

            }
            //ClassNotFoundException SI HAY UN ERROR EN EL DRIVER
        } catch (ClassNotFoundException ex) {
            System.out.println("Error en Driver : " + ex.getMessage());
        //SQLException SI ES QUE HAY ERROR EN LA CONSULTA
        } catch (SQLException ex) {
            System.out.println("Error al conectar: " + ex.getMessage());
        }

    }

    public Connection conectar() {
        return conn;
    }

    public void desconectar() {
        System.out.println("Cerrando la BD : " + conn);
        try {
            conn.close();
        } catch (Exception ex) {
            System.out.println("Error de SQL 2222: " + ex.getMessage());
        }
    }
}
