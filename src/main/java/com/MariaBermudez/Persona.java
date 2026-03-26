package com.MariaBermudez;

import com.MariaBermudez.generador.annotations.DocClase;
import com.MariaBermudez.generador.annotations.DocElemento;

@DocClase(nombre = "Persona", autor = "Maria Bermudez", descripcion = "Entidad para el manejo de usuarios.")
public class Persona extends Entidad {

    @DocElemento(name = "Nombre Completo", description = "Nombre unico del usuario")
    private String name;

    @DocElemento(name = "Edad Cronologica", description = "Edad en años")
    private int age;

    @DocElemento(name = "Constructor Principal", description = "Crea una persona vinculada a una entidad base")
    public Persona(int id, String name, int age) {
        super(id);
        this.name = name;
        this.age = age;
    }

    @DocElemento(name = "Obtener Nombre", description = "Retorna el nombre del usuario")
    public String getName() {
        return name;
    }

    @DocElemento(name = "Actualizar Edad", description = "Modifica la edad del usuario")
    public void setAge(int age) {
        this.age = age;
    }

    @DocElemento(name = "Obtener ID Personalizado", description = "Retorna el ID heredado pero con logica propia")
    @Override
    public int getId() {
        return super.getId();
    }
}