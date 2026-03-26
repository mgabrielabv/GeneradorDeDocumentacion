package com.MariaBermudez;

import com.MariaBermudez.generador.core.DocGenerator;

public class Main {
    public static void main(String[] args) {
        // Procesar la clase de ejemplo y generar el markdown en /docs
        DocGenerator.process(Persona.class);
    }
}
