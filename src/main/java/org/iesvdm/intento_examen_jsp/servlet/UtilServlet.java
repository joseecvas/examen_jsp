package org.iesvdm.intento_examen_jsp.servlet;

import jakarta.servlet.http.HttpServletRequest;
import org.iesvdm.intento_examen_jsp.model.Estudiante;
import org.iesvdm.intento_examen_jsp.model.Estudiante;

import java.util.Objects;
import java.util.Optional;

public class UtilServlet {

    public static Optional<Estudiante> validaGrabar(HttpServletRequest request) {

        boolean valida = true;
        String nombre = null;
        int edad = -1;
        try {

            Objects.requireNonNull(request.getParameter("nombre"));
            if (request.getParameter("nombre").isBlank()) throw new RuntimeException("Parámetro vacío o todo espacios blancos.");
            nombre = request.getParameter("nombre");
            edad = Integer.parseInt(request.getParameter("edad"));

            return Optional.of(new Estudiante(-1, nombre,edad));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Optional.empty();

    }

    public static Optional<Integer> validaBorrar(HttpServletRequest request) {
        boolean valida = true;

        String codigoStr = request.getParameter("codigo");

        Integer codigo = null;

        try {
            codigo = Integer.parseInt(codigoStr);
            return Optional.of(codigo);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

        return Optional.empty();
    }

    public static Optional<Estudiante> validaEditar(HttpServletRequest request) {
        boolean valida = true;
        String codigoStr = request.getParameter("codigo");
        Integer codigo = null;

        String nombre = null;
        int edad = -1;


        try {

            Objects.requireNonNull(request.getParameter("nombre"));

            if (request.getParameter("nombre").isBlank()) throw new RuntimeException("Parámetro vacío o todo espacios blancos.");
            nombre = request.getParameter("nombre");

            edad = Integer.parseInt(request.getParameter("edad"));

            codigo = Integer.parseInt(codigoStr);

            if (nombre != null && edad != -1) {
                return Optional.of(new Estudiante(codigo, nombre, edad));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return Optional.empty();
    }
}
