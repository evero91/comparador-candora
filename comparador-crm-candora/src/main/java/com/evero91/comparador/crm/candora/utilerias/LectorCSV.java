/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evero91.comparador.crm.candora.utilerias;

import com.opencsv.CSVReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sistemas
 */
public class LectorCSV {

    public List<String[]> leerArchivo(String archivo) {
        try {
            CSVReader reader = new CSVReader(new FileReader(archivo));
            List<String[]> contenidoArchivo = new ArrayList<>();
            String[] line;

            while ((line = reader.readNext()) != null) {
                contenidoArchivo.add(line);
            }

            return contenidoArchivo;
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
        return null;
    }

}
