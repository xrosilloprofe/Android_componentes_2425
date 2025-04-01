package es.ieslavereda.componentes_2425.model;

import java.io.Serial;
import java.io.Serializable;

public class Usuario implements Serializable {
    private String nombre;
    private String apellido;
    private Profesion profesion;

    public Usuario(String nombre, String apellido, Profesion profesion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.profesion = profesion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Profesion getProfesion() {return profesion;}

    @Override
    public String toString(){
        return nombre + " " + apellido + "->" + profesion.getProfesion();
    }

}
