package com.MariaBermudez.generador.core;

import com.MariaBermudez.generador.annotations.*;
import com.MariaBermudez.generador.util.EscritorMarkdown;
import java.lang.reflect.*;

public class DocGenerator {

    public static void procesar(Class<?> clase) {
        StringBuilder sb = new StringBuilder();

        // Encabezado general similar a la imagen del enunciado
        sb.append("# Programación Visual\n\n");
        sb.append("## Proyecto 4: Generador de documentación\n\n");
        sb.append("**Objetivo:** Desarrollar un programa que reciba una clase de Java y genere una documentación completa de todos sus atributos y métodos, utilizando anotaciones personalizadas.\n\n");

        sb.append("---\n\n");

        // Información de la clase
        sb.append("### Clase: ").append(clase.getSimpleName()).append("\n\n");

        if (clase.isAnnotationPresent(DocClase.class)) {
            DocClase dc = clase.getAnnotation(DocClase.class);
            sb.append("- **Nombre:** ").append(dc.nombre().isEmpty() ? clase.getSimpleName() : dc.nombre()).append("\n");
            sb.append("- **Autor:** ").append(dc.autor()).append("\n");
            sb.append("- **Versión:** ").append(dc.version()).append("\n");
            sb.append("- **Descripción:** ").append(dc.descripcion()).append("\n\n");
        }

        Class<?> padre = clase.getSuperclass();
        sb.append("- **Subclase de:** ").append(padre != null && padre != Object.class ? padre.getSimpleName() : "Ninguna").append("\n\n");

        // Sección que lista qué debe documentar (según enunciado)
        sb.append("## Debe documentar\n");
        sb.append("- En la clase:\n");
        sb.append("  - Nombre\n");
        sb.append("  - Autor\n");
        sb.append("  - Descripción\n");
        sb.append("  - Versión\n");
        sb.append("  - Si es subclase\n\n");

        sb.append("- En los métodos:\n");
        sb.append("  - Parámetros\n");
        sb.append("  - Tipo de retorno\n");
        sb.append("  - Descripción\n");
        sb.append("  - Modificadores\n");
        sb.append("  - Si es getter o setter de algún atributo\n");
        sb.append("  - Si es constructor de la clase\n");
        sb.append("  - Si es un método sobrescrito de otra clase\n\n");

        sb.append("- En los atributos:\n");
        sb.append("  - Tipo\n");
        sb.append("  - Descripción\n");
        sb.append("  - Modificadores\n\n");

        // Atributos detallados
        sb.append("## Atributos\n\n");
        for (Field f : clase.getDeclaredFields()) {
            String desc = f.isAnnotationPresent(DocElemento.class) ? f.getAnnotation(DocElemento.class).descripcion() : "-";
            sb.append("- **").append(f.getName()).append("**\n");
            sb.append("  - Tipo: ").append(f.getType().getSimpleName()).append("\n");
            sb.append("  - Modificadores: ").append(Modifier.toString(f.getModifiers())).append("\n");
            sb.append("  - Descripción: ").append(desc).append("\n\n");
        }

        // Métodos y constructores detallados
        sb.append("## Métodos y Constructores\n\n");

        // Constructores
        for (Constructor<?> c : clase.getDeclaredConstructors()) {
            String desc = c.isAnnotationPresent(DocElemento.class) ? c.getAnnotation(DocElemento.class).descripcion() : "Sin descripción";
            sb.append("- **Constructor:** ").append(clase.getSimpleName()).append("\n");
            sb.append("  - Descripción: ").append(desc).append("\n");
            sb.append("  - Modificadores: ").append(Modifier.toString(c.getModifiers())).append("\n");
            if (c.getParameterCount() > 0) {
                sb.append("  - Parámetros: ");
                Parameter[] params = c.getParameters();
                for (int i = 0; i < params.length; i++) {
                    Parameter p = params[i];
                    String pname = p.isNamePresent() ? p.getName() : "param" + i;
                    sb.append("`" + p.getType().getSimpleName() + " " + pname + "` ");
                }
                sb.append("\n");
            }
            sb.append("\n");
        }

        // Métodos
        for (Method m : clase.getDeclaredMethods()) {
            String desc = m.isAnnotationPresent(DocElemento.class) ? m.getAnnotation(DocElemento.class).descripcion() : "Sin descripción";

            sb.append("- **").append(m.getName()).append("**\n");
            sb.append("  - Descripción: ").append(desc).append("\n");
            sb.append("  - Retorno: ").append(m.getReturnType().getSimpleName()).append("\n");
            sb.append("  - Modificadores: ").append(Modifier.toString(m.getModifiers())).append("\n");

            boolean isGS = m.getName().startsWith("get") || m.getName().startsWith("set") || m.getName().startsWith("is");
            sb.append("  - ¿Es Getter/Setter?: ").append(isGS ? "Sí" : "No").append("\n");

            if (m.getParameterCount() > 0) {
                sb.append("  - Parámetros: ");
                Parameter[] params = m.getParameters();
                for (int i = 0; i < params.length; i++) {
                    Parameter p = params[i];
                    String pname = p.isNamePresent() ? p.getName() : "param" + i;
                    sb.append("`" + p.getType().getSimpleName() + " " + pname + "` ");
                }
                sb.append("\n");
            }

            // Indicar si es override (sobrescrito)
            boolean isOverride = false;
            try {
                Method superM = clase.getSuperclass().getMethod(m.getName(), m.getParameterTypes());
                if (superM != null) isOverride = true;
            } catch (Exception ex) {
                // no existe en la superclase
            }
            sb.append("  - ¿Es sobrescrito de otra clase?: ").append(isOverride ? "Sí" : "No").append("\n\n");
        }

        EscritorMarkdown.guardar(clase.getSimpleName(), sb.toString());
    }
}