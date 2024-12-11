<%@ page import="java.sql.*" %>
<%@ page import="org.iesvdm.intento_examen_jsp.model.Estudiante" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <link rel="stylesheet" type="text/css" href="estilos.css" />
  </head>
  <body>
    <table>
      <tr><th>Código</th><th>Nombre</th><th>Edad</th></tr>
    <%
        List<Estudiante> listado = (List<Estudiante>) request.getAttribute("listado");

        for(Estudiante estudiante: listado) {
    %>

      <tr>
        <td><%=estudiante.getEstudianteId() %>
        </td>
        <td><%=estudiante.getNombre() %>
        </td>
        <td><%=estudiante.getEdad() %>
        </td>
      <td>

      <form method="get" action="borraEstudiante.jsp">
        <input type="hidden" name="codigo" value="<%=estudiante.getEstudianteId() %>"/>
        <input type="submit" value="Borrar">
      </form>
      </td></tr>
    <%
      // FIN DEL BUCLE FOR CON HTML INCRUSTADO
      } // for
     %>
    </table>
  </body>
</html>