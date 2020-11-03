package com.emergentes.controlador;

import com.emergentes.dao.AvisoDAO;
import com.emergentes.dao.AvisoDAOimpl;
import com.emergentes.modelo.Aviso;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*desde aqui gestionamos el proceso DAO Y realizamos las 
operaciones CRUD*/
@WebServlet(name = "inicio", urlPatterns = {"/inicio"})
public class inicio extends HttpServlet {

//PETICION DESDE LA URL EN DO GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            /*el objeto dao nos facilitara hacer las opciones del switch
            creamos el obj 'dao' a partir de su interfaz 'AvisoDAO' y su 
            implementacion 'AvisoDAOimpl'*/
            AvisoDAO dao = new AvisoDAOimpl();
            //id para  la eliminacion o edicion
            int id;
            //para gestionar los registros
            Aviso avi = new Aviso();
            /*pedir cual es la peticion que estan haciendo
            En la variable action verificamos el valor action del formulario
            pero action podria estar vacio y generar algun problema........
            si es null le damos a action un valor por defecto que sera 'view'*/
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            //verificamos la opcion que tiene action
            switch (action) {
                case "add":
                    //nuevo registro
                    //
                    request.setAttribute("aviso", avi);
                    request.getRequestDispatcher("frmaviso.jsp").forward(request, response);
                    break;
                case "edit":
                    //para editar un registro
                    //verificamos el id que nos estan enviando
                    id = Integer.parseInt(request.getParameter("id"));
                    //sacamos el objeto que tiene id en 'avi'
                    avi = dao.getById(id);
                    System.out.println("aqui!!!!!!" + avi);
                    System.out.println("aqui!!!!!!" + avi);
                    System.out.println("aqui!!!!!!" + avi);
                    System.out.println("aqui!!!!!!" + avi);
                    //avi colocamos como atributo de request en aviso
                    request.setAttribute("aviso", avi);
                    //transferimos los datos a el formulario y tambien el control
                    request.getRequestDispatcher("frmaviso.jsp").forward(request, response);
                    break;
                case "delete":
                    //para eliminar un registro
                    //obtener el parametro de id en id
                    id = Integer.parseInt(request.getParameter("id"));
                    //eliminamos
                    dao.delete(id);
                    response.sendRedirect(request.getContextPath() + "/inicio");
                    break;
                case "view":
                    //listar los registros
                    /*creamos la coleccion lista y con getAll() 
                    obtenemos todos los registros*/
                    List<Aviso> lista = dao.getAll();
                    //colocamos lista como atributo de request para mandarlo a la vista(index)
                    request.setAttribute("avisos", lista);
                    //mandamos a la 'vista' index(para mostrar los registros))
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    break;

                default:
                    break;
            }

        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String titulo = request.getParameter("titulo");
        String contenido = request.getParameter("contenido");

        Aviso avi = new Aviso();

        avi.setId(id);
        avi.setTitulo(titulo);
        avi.setContenido(contenido);
        if (id == 0) {
            //NUEVO REGISTRO
            try {
                //PARA INSERTAR CREAMOS EL OBJ DAO
                AvisoDAO dao = new AvisoDAOimpl();
                //INSERTAMOS EL OBJ AVI
                dao.insert(avi);
                //REDIRECIONAMOS A INICIO
                response.sendRedirect(request.getContextPath() + "/inicio");

            } catch (Exception ex) {
                System.out.println("Error " + ex.getMessage());
            }
        } else {
            //ACTUALIZACION DE UN REGISTRO        
            try {
                //OBJ DAO PARA ACTUALIZAR
                AvisoDAO dao = new AvisoDAOimpl();
                //ACTUALIZAMOS
                dao.update(avi);
                //REDIRECCIONAMOS A INICIO
                response.sendRedirect(request.getContextPath() + "/inicio");
            } catch (Exception ex) {
                System.out.println("Error " + ex.getMessage());
            }
        }
    }

}
