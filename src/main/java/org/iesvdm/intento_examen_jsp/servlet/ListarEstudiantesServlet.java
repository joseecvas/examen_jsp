package org.iesvdm.intento_examen_jsp.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesvdm.intento_examen_jsp.dao.EstudianteDAO;
import org.iesvdm.intento_examen_jsp.dao.EstudianteDAOImpl;
import org.iesvdm.intento_examen_jsp.model.Estudiante;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListarEstudiantesServlet", value = "/ListarEstudiantesServlet")
public class ListarEstudiantesServlet extends HttpServlet {

    private EstudianteDAO estudianteDAO = new EstudianteDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/listadoEstudiantesB.jsp");

        List<Estudiante> listado = this.estudianteDAO.getAll();
        request.setAttribute("listado", listado);

        dispatcher.forward(request, response);

    }

}
