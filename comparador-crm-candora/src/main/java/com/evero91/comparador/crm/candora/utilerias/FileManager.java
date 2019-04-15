/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evero91.comparador.crm.candora.utilerias;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 *
 * @author sistemas
 */
public class FileManager {

    public static boolean crearArchivo(String pathname, String... opciones) {
        File archivo = new File(pathname);

        if (archivo.exists()) {
            if (archivo.delete()) {
                System.out.println("Archivo eliminado");
            } else {
                System.out.println("No se pudo eliminar archivo");
                return false;
            }
        }

        try {
            if (archivo.createNewFile()) {
                return true;
            } else {
                System.out.println("No se pudo crear archivo: " + pathname);
                return false;
            }
        } catch (IOException ex) {
            System.out.println("FileManager -> crearArchivo -> " + ex);
            return false;
        }
    }
    
    public static boolean guardarContenido(String pathname, List<String> contenido) {
        try {
            Files.write(Paths.get(pathname), contenido, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            return true;
        } catch (IOException ex) {
            System.out.println("FileManager -> guardarContenido -> " + ex);
            return false;
        }
    }

}
