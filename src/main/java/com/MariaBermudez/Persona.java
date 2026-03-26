package com.MariaBermudez;

import com.MariaBermudez.generador.annotations.DocClase;
import com.MariaBermudez.generador.annotations.DocElemento;

@DocClase(nombre = "Persona", autor = "César", descripcion = "Clase que representa a una persona ejemplo.")
public class Persona {

    @DocElemento(descripcion = "Nombre de la persona")
    private String nombre;

    @DocElemento(descripcion = "Edad de la persona")
    private int edad;

    @DocElemento(descripcion = "Indica si la persona está activa")
    private boolean activo;

    public Persona() {
        this.nombre = "Anónimo";
        this.edad = 0;
        this.activo = true;
    }

    @DocElemento(descripcion = "Constructor con parámetros")
    public Persona(String nombre, int edad, boolean activo) {
        this.nombre = nombre;
        this.edad = edad;
        this.activo = activo;
    }

    @DocElemento(descripcion = "Obtiene el nombre")
    public String getNombre() {
        return nombre;
    }

    @DocElemento(descripcion = "Establece el nombre")
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @DocElemento(descripcion = "Obtiene la edad")
    public int getEdad() {
        return edad;
    }

    @DocElemento(descripcion = "Establece la edad")
    public void setEdad(int edad) {
        this.edad = edad;
    }

    @DocElemento(descripcion = "Indica si está activo")
    public boolean isActivo() {
        return activo;
    }

    @DocElemento(descripcion = "Establece estado activo")
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @DocElemento(descripcion = "Ejemplo de método que devuelve saludo")
    public String saludar(String quien) {
        return "Hola " + quien + ", soy " + nombre;
    }
}

