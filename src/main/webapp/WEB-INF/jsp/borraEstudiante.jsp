<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  </head>
  <body>
    <%
    //CÓDIGO DE VALIDACIÓN
    boolean valida = true;
    int codigo = -1;
    try {
    	codigo = Integer.parseInt(request.getParameter("codigo"));
    } catch (NumberFormatException nfe) {
    	nfe.printStackTrace();
    	valida = false;
    }
    //FIN CÓDIGO DE VALIDACIÓN

    if (valida) {

	Connection conn = null;
	PreparedStatement ps = null;
// 	ResultSet rs = null;

	 try {

		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/baloncesto","root", "user");
		String sql = "DELETE FROM estudiante WHERE estudianteID = ?";

		ps = conn.prepareStatement(sql);

		ps.setInt(1, codigo);

		int filasAfectadas = ps.executeUpdate();
		
		System.out.println("estudianteS BORRADOS:  " + filasAfectadas);
		  
	 } catch (Exception ex) {
		 ex.printStackTrace();
	 } finally {

		    try { ps.close(); } catch (Exception e) {}
		    try { conn.close(); } catch (Exception e) {}
	 }
	  
    }
    %>
    <script>document.location = "pideNumeroEstudiante.jsp"</script>
  </body>
</html>