package com.jhonlopera.nerd30;

/**
 * Created by Mateo_Velez on 29/10/2017.
 */

public class Jugador {

    private String id, correo, nombre;

    public Jugador() {
    }

    public Jugador(String id, String correo, String nombre) {
        this.id = id;
        this.correo = correo;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
