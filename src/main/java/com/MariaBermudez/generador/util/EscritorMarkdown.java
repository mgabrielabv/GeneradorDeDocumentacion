package com.MariaBermudez.generador.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EscritorMarkdown {
    public static void guardar(String nombreArchivo, String contenido) {
        try {
            Path docs = Paths.get("docs");
            if (!Files.exists(docs)) {
                Files.createDirectories(docs);
            }
            Path archivo = docs.resolve(nombreArchivo + ".md");
            try (PrintWriter writer = new PrintWriter(new FileWriter(archivo.toFile()))) {
                writer.print(contenido);
                System.out.println("Archivo " + archivo.toString() + " generado con éxito.");
            }
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }
    }
}