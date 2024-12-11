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


@WebServlet(name = "BorrarEstudiantesServlet", value = "/BorrarEstudiantesServlet")
public class BorrarEstudiantesServlet extends HttpServlet {

    private EstudianteDAO estudianteDAO = new EstudianteDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;

        Optional<Integer> optionalCodigo = UtilServlet.validaBorrar(request);

        if (optionalCodigo.isPresent()) {
            int codigo = optionalCodigo.get();

            estudianteDAO.delete(codigo);

            List<Estudiante> listado = this.estudianteDAO.getAll();
            request.setAttribute("listado", listado);

            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/listadoEstudiantesB.jsp");
            dispatcher.forward(request, response);

        } else {
            // Parametro no valido
            response.sendRedirect("ListarEstudiantesServlet?err-cod=1");
        }
    }
}
