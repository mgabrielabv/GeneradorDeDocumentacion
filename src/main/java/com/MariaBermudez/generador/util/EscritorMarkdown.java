package com.MariaBermudez.generador.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class EscritorMarkdown {
    public static void guardar(String nombreArchivo, String contenido) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo + ".md"))) {
            writer.print(contenido);
            System.out.println("rchivo " + nombreArchivo + ".md generado con éxito.");
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }
    }
}