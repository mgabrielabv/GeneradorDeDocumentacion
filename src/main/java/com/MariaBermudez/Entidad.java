package com.MariaBermudez;

import com.MariaBermudez.generador.annotations.DocElemento;

public class Entidad {

    @DocElemento(name = "Identificador Unico", description = "Codigo interno de la entidad")
    private int id;

    @DocElemento(name = "Estado", description = "Si la entidad esta activa o no")
    protected boolean active;

    public Entidad(int id) {
        this.id = id;
        this.active = true;
    }

    @DocElemento(name = "Obtener ID", description = "Retorna el identificador unico")
    public int getId() {
        return id;
    }

    @DocElemento(name = "Esta Activo", description = "Verifica el estado de la entidad")
    public boolean isActive() {
        return active;
    }
}