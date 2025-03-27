package es.ieslavereda.componentes_2425.model;

import java.io.Serial;
import java.io.Serializable;

public class Usuario implements Serializable {
    private String nombre;
    private String apellido;

    public Usuario(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    @Override
    public String toString(){
        return nombre + " " + apellido;
    }

}
