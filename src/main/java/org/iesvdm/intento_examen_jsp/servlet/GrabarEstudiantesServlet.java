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
import java.util.Optional;

@WebServlet(name = "GrabarEstudiantesServlet", value = "/GrabarEstudiantesServlet")
public class GrabarEstudiantesServlet extends HttpServlet {

    private EstudianteDAO estudianteDAO = new EstudianteDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/formularioEstudianteB.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = null;
//-
        Optional<Estudiante> optionalEstudiante = UtilServlet.validaGrabar(request);

        if (optionalEstudiante.isPresent()) {

            Estudiante estudiante = optionalEstudiante.get();

            this.estudianteDAO.create(estudiante);

            List<Estudiante> listado = this.estudianteDAO.getAll();

            request.setAttribute("listado", listado);

            request.setAttribute("newEstudianteID", estudiante.getEstudianteId() );

            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/listadoEstudiantesB.jsp");
        } else {

            request.setAttribute("error", "Error de validación!");

            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/formularioEstudianteB.jsp");
        }

        dispatcher.forward(request,response);

    }

}