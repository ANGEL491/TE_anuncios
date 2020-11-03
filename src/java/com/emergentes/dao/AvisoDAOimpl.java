/*Se crea la implementación de la interface, 
al mismo tiempo la clase se hereda de ConexionDB*/
package com.emergentes.dao;

import com.emergentes.modelo.Aviso;
import com.emergentes.utiles.ConexionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//IMPLEMENTAR LA INTERFACE CON 'implements AvisoDAO' la interface anterior
//ADEMAS VAMOS A HEREDAR DE LA CLASE 'ConexionBD' CON 'extends ConexionBD'
public class AvisoDAOimpl extends ConexionBD implements AvisoDAO {

    @Override
    //PARA INSERTAR UN REGISTRO
    public void insert(Aviso aviso) throws Exception {
        try {
            //CONECTAR A LA BD
            this.conectar();
            //PARA LA REALIZAR LA CONSULTA SQL Y ALMACENADOS EN 'ps'
            PreparedStatement ps = this.conn.prepareStatement("insert into avisos(titulo,contenido)values(?, ?)");
            //ahora pasamos los parametros de 'ps'
            ps.setString(1, aviso.getTitulo());
            ps.setString(2, aviso.getContenido());
            //ejecutar la consulta
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Aviso aviso) throws Exception {

        try {
            //CONECTAR A LA BD
            this.conectar();
            //PARA LA REALIZAR LA CONSULTA SQL Y ALMACENADOS EN 'ps'
            PreparedStatement ps = this.conn.prepareStatement("update avisos set titulo =?,contenido=? where id=?");
            //ahora pasamos los parametros de 'ps'
            ps.setString(1, aviso.getTitulo());
            ps.setString(2, aviso.getContenido());
            ps.setInt(3, aviso.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("delete from avisos where id= ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public Aviso getById(int id) throws Exception {
        //variable que vamos a devolver
        Aviso avi = new Aviso();
        try {
            //conectar a la bd
            this.conectar();
            //preparamos la consulta
            PreparedStatement ps = this.conn.prepareStatement("select * from avisos where id=? limit 1");
            //pasamos los datos
            ps.setInt(1, id);
            //ejecutamos la consulta y guardamos los datos en un ResultSet 'rs'
            ResultSet rs = ps.executeQuery();
            /*una vez que ya  hemos obtenido el registro tenemos que convertirlo en 
            un obj tipo 'avi'*/
            //VERIFICAMOS SI RS TIENE REGISTROS
            if (rs.next()) {
                //DE RS OBTENEMOS EL ID
                avi.setId(rs.getInt("id"));
                //OBTENEMOS TITULO
                avi.setTitulo(rs.getString("titulo"));
                //OBTENEMOS EL CONTENIDO
                avi.setContenido(rs.getString("contenido"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        //DEVOLVEMOS EL REGISTRO COMPLETO EN AVI CONSULTADO CON EL ID PROPORCIONADO
        return avi;
    }
//TENEMOS QUE DEVOLVER UNA COLECCION TIPO AVISO

    @Override
    public List<Aviso> getAll() throws Exception {
        //ESTA LISTA ES LA QUE SE VA A DEVOLVER AL FINAL
        List<Aviso> lista = null;
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("select * from avisos");
            //COMO NO HAY PARAMETROS EN LA CONSULTA 
            //DIRECTAMENTE EJECUTAMOS LA CONSULTA Y LO GUARDAMOS EN RS
            ResultSet rs = ps.executeQuery();
//AHORA 'RS' EN UN CONJUNTO DE REGISTROS Y TENEMOS QUE PASAR LA INFORMACION EN 'LISTA'
//ENTONCES INICIALIZAMOS LA LISTA QUE VA A CONTENER OBT TIPO 'AVISOS'
            lista = new ArrayList<Aviso>();
//AVERIGUAMOS SI RS TIENE REGISTROS
            while (rs.next()) {
                //OBTENEMOS LOS REGISTROS EN AVI
                Aviso avi = new Aviso();
                avi.setId(rs.getInt("id"));
                avi.setTitulo(rs.getString("titulo"));
                avi.setContenido(rs.getString("contenido"));
                
                //ADICI0NAMOS A LA COLECCION LISTA
                lista.add(avi);
            }
            rs.close();
            ps.close();

        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return lista;
    }

}
