package com.MariaBermudez.generador.core;

import com.MariaBermudez.generador.annotations.*;
import com.MariaBermudez.generador.util.EscritorMarkdown;
import java.lang.reflect.*;

public class DocGenerator {

    public static void procesar(Class<?> clase) {
        StringBuilder sb = new StringBuilder();

        sb.append("# Documentacion: ").append(clase.getSimpleName()).append("\n\n");

        if (clase.isAnnotationPresent(DocClase.class)) {
            DocClase dc = clase.getAnnotation(DocClase.class);
            sb.append("**Nombre:** ").append(dc.nombre().isEmpty() ? clase.getSimpleName() : dc.nombre()).append("  \n");
            sb.append("**Autor:** ").append(dc.autor()).append("  \n");
            sb.append("**Version:** ").append(dc.version()).append("  \n");
            sb.append("**Descripcion:** ").append(dc.descripcion()).append("  \n");
        }

        Class<?> padre = clase.getSuperclass();
        sb.append("**Subclase de:** ").append(padre != null && padre != Object.class ? padre.getSimpleName() : "Ninguna").append("\n\n");

        sb.append("## Atributos\n");
        sb.append("| Modificador | Tipo | Nombre | Descripcion |\n| :--- | :--- | :--- | :--- |\n");
        for (Field f : clase.getDeclaredFields()) {
            String desc = f.isAnnotationPresent(DocElemento.class) ? f.getAnnotation(DocElemento.class).descripcion() : "-";
            sb.append(String.format("| %s | %s | %s | %s |\n",
                    Modifier.toString(f.getModifiers()), f.getType().getSimpleName(), f.getName(), desc));
        }

        sb.append("\n## Metodos y Constructores\n");
        for (Method m : clase.getDeclaredMethods()) {
            String desc = m.isAnnotationPresent(DocElemento.class) ? m.getAnnotation(DocElemento.class).descripcion() : "Sin descripción";

            sb.append("### ").append(m.getName()).append("\n");
            sb.append("- **Descripcion:** ").append(desc).append("\n");
            sb.append("- **Retorno:** ").append(m.getReturnType().getSimpleName()).append("\n");
            sb.append("- **Modificadores:** ").append(Modifier.toString(m.getModifiers())).append("\n");

            boolean isGS = m.getName().startsWith("get") || m.getName().startsWith("set") || m.getName().startsWith("is");
            sb.append("- **¿Es Getter/Setter?:** ").append(isGS ? "Sí" : "No").append("\n");

            if (m.getParameterCount() > 0) {
                sb.append("- **Parametros:** ");
                for (Parameter p : m.getParameters()) {
                    sb.append("`").append(p.getType().getSimpleName()).append(" ").append(p.getName()).append("` ");
                }
                sb.append("\n");
            }
            sb.append("\n---\n");
        }

        EscritorMarkdown.guardar(clase.getSimpleName(), sb.toString());
    }
}