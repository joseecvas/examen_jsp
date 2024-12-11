package org.iesvdm.intento_examen_jsp.dao;

import org.iesvdm.intento_examen_jsp.model.Estudiante;

import java.util.List;
import java.util.Optional;

public interface EstudianteDAO {

    public void create(Estudiante estudiante);

    public List<Estudiante> getAll();
    public Optional<Estudiante> find(int id);

    public void update(Estudiante estudiante);

    public void delete(int id);
}
