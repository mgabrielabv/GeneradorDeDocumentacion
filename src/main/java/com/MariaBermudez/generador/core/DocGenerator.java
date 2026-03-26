package com.MariaBermudez.generador.core;

import com.MariaBermudez.generador.annotations.*;
import com.MariaBermudez.generador.util.EscritorMarkdown;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class DocGenerator {

    public static void process(Class<?> clazz) {
        StringBuilder sb = new StringBuilder();
        Class<?> superClass = clazz.getSuperclass();

        sb.append("# Documentacion de Clase: ").append(clazz.getSimpleName()).append("\n\n");

        if (clazz.isAnnotationPresent(DocClase.class)) {
            DocClase dc = clazz.getAnnotation(DocClase.class);
            sb.append("## Resumen\n\n");
            sb.append("| Campo | Valor |\n| :--- | :--- |\n");
            sb.append("| **Nombre** | ").append(dc.nombre().isEmpty() ? clazz.getSimpleName() : dc.nombre()).append(" |\n");
            sb.append("| **Autor** | ").append(dc.autor()).append(" |\n");
            sb.append("| **Version** | ").append(dc.version()).append(" |\n");
            sb.append("| **Herencia** | ").append(superClass != null && superClass != Object.class ? superClass.getSimpleName() : "N/A").append(" |\n\n");
            sb.append("**Descripcion:** ").append(dc.descripcion()).append("\n\n");
        }

        sb.append("## Atributos\n\n");
        sb.append("| Nombre Documentado | Tipo | Modificadores | Descripcion |\n");
        sb.append("| :--- | :--- | :--- | :--- |\n");

        for (Field f : clazz.getDeclaredFields()) {
            DocElemento de = f.getAnnotation(DocElemento.class);
            String label = (de != null && !de.name().isEmpty()) ? de.name() : f.getName();
            String desc = (de != null) ? de.description() : "N/A";

            sb.append("| **").append(label).append("** | `")
                    .append(f.getType().getSimpleName()).append("` | `")
                    .append(Modifier.toString(f.getModifiers())).append("` | ")
                    .append(desc).append(" |\n");
        }

        sb.append("\n## Metodos y Operaciones\n\n");

        for (Constructor<?> c : clazz.getDeclaredConstructors()) {
            documentMember(sb, c.getName(), c.getModifiers(), "void", c.getParameters(), c, true, clazz);
        }

        for (Method m : clazz.getDeclaredMethods()) {
            documentMember(sb, m.getName(), m.getModifiers(), m.getReturnType().getSimpleName(), m.getParameters(), m, false, clazz);
        }

        EscritorMarkdown.guardar(clazz.getSimpleName(), sb.toString());
    }

    private static void documentMember(StringBuilder sb, String name, int mods, String ret, Parameter[] params, AnnotatedElement element, boolean isCons, Class<?> clazz) {
        DocElemento de = element.getAnnotation(DocElemento.class);

        String title = (de != null && !de.name().isEmpty()) ? de.name() : name;
        String desc = (de != null) ? de.description() : "Sin descripcion.";

        sb.append("### ").append(title).append("\n");
        sb.append("| Propiedad | Detalle |\n| :--- | :--- |\n");

        String pStr = Arrays.stream(params)
                .map(p -> p.getType().getSimpleName() + " " + p.getName())
                .collect(Collectors.joining(", "));

        sb.append("| **Firma Tecnica** | `").append(name).append("(").append(pStr).append(")` |\n");
        sb.append("| **Retorno** | `").append(ret).append("` |\n");
        sb.append("| **Modificadores** | `").append(Modifier.toString(mods)).append("` |\n");
        sb.append("| **Descripcion** | ").append(desc).append(" |\n");

        if (!isCons && element instanceof Method) {
            Method m = (Method) element;
            boolean isG = m.getName().startsWith("get") && m.getParameterCount() == 0;
            boolean isS = m.getName().startsWith("set") && m.getParameterCount() == 1;

            boolean isO = false;
            try {
                if (clazz.getSuperclass() != null && clazz.getSuperclass() != Object.class) {
                    clazz.getSuperclass().getMethod(m.getName(), m.getParameterTypes());
                    isO = true;
                }
            } catch (NoSuchMethodException e) {}

            sb.append("| **Tipo** | ").append(isG ? "Getter " : "").append(isS ? "Setter " : "")
                    .append(isO ? "Sobrescrito " : "").append((!isG && !isS && !isO) ? "Normal" : "").append(" |\n");
        }
        sb.append("\n---\n\n");
    }
}