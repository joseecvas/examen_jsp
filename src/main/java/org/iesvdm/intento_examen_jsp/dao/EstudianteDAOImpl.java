package org.iesvdm.intento_examen_jsp.dao;

import org.iesvdm.intento_examen_jsp.model.Estudiante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EstudianteDAOImpl extends AbstractDAOImpl implements EstudianteDAO {
    @Override
    public void create(Estudiante estudiante) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSet rsGenKeys = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("INSERT INTO estudiante (nombre, edad) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);

            int idx = 1;
            ps.setString(idx++, estudiante.getNombre());
            ps.setInt(idx++, estudiante.getEdad());

            int rows = ps.executeUpdate();
            if (rows == 0)
                System.out.println("INSERT de estudiante con 0 filas insertadas.");

            rsGenKeys = ps.getGeneratedKeys();
            if (rsGenKeys.next())
                estudiante.setEstudianteId(rsGenKeys.getInt(1));

        } catch (SQLException | ClassNotFoundException  e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }

    @Override
    public List<Estudiante> getAll() {

        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;

        List<Estudiante> listEstudiante = new ArrayList<>();

        try {
            conn = connectDB();

            s = conn.createStatement();

            rs = s.executeQuery("SELECT * FROM estudiante");
            while (rs.next()) {
                Estudiante estudiante = new Estudiante();

                estudiante.setEstudianteId(rs.getInt("estudianteID"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setEdad(rs.getInt("edad"));
                listEstudiante.add(estudiante);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, s, rs);
        }
        return listEstudiante;

    }

    @Override
    public Optional<Estudiante> find(int id) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("SELECT * FROM estudiante WHERE estudianteID = ?");

            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                Estudiante estudiante = new Estudiante();

                estudiante.setEstudianteId(rs.getInt("estudianteID"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setEdad(rs.getInt("edad"));

                return Optional.of(estudiante);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

        return Optional.empty();

    }

    @Override
    public void update(Estudiante estudiante) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("UPDATE estudiante SET nombre = ?, edad = ? WHERE estudianteID = ?");
            int idx = 1;
            ps.setString(idx++, estudiante.getNombre());
            ps.setInt(idx++, estudiante.getEdad());
            ps.setInt(idx, estudiante.getEstudianteId());

            int rows = ps.executeUpdate();

            if (rows == 0)
                System.out.println("Update de estudiante con 0 registros actualizados.");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }

    @Override
    public void delete(int id) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("DELETE FROM estudiante WHERE estudianteID = ?");
            int idx = 1;
            ps.setInt(idx, id);

            int rows = ps.executeUpdate();

            if (rows == 0)
                System.out.println("Delete de estudiante con 0 registros eliminados.");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }
}
