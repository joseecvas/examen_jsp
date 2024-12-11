package org.iesvdm.intento_examen_jsp.model;

import java.util.Objects;

public class Estudiante {
    private int estudianteId;
    private String nombre;
    private int edad;

    public Estudiante() {}

    public Estudiante(int estudianteId, String nombre, int edad) {
        this.estudianteId = estudianteId;
        this.nombre = nombre;
        this.edad = edad;
    }

    public int getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(int estudianteId) {
        this.estudianteId = estudianteId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "estudianteId=" + estudianteId +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estudiante that = (Estudiante) o;
        return estudianteId == that.estudianteId && edad == that.edad && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(estudianteId, nombre, edad);
    }
}
