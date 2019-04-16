/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evero91.comparador.sam.candora.modelo.servicios;

import com.evero91.comparador.sam.candora.modelo.pojo.ArchivoLote;
import com.evero91.comparador.sam.candora.utilerias.LectorCSV;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sistemas
 */
public class ServicioArchivoLotes {
    
    public List<ArchivoLote> leerArchivoLotes(String archivo) {
        LectorCSV lectorCSV = new LectorCSV();
        List<String[]> contenidoArchivo = lectorCSV.leerArchivo(archivo);
        
        if (contenidoArchivo != null) {
            List<ArchivoLote> archivoLotes = new ArrayList<>();
            
            for (String[] linea : contenidoArchivo) {
                int i = 0;
                ArchivoLote archivoLote = new ArchivoLote();
                
                archivoLote.setManzana(linea[i++]);
                archivoLote.setLote(Integer.parseInt(linea[i++]));
                archivoLote.setSuperficie(Float.parseFloat(linea[i++]));
                archivoLote.setMedidas(linea[i++]);
                
                archivoLote.setPrecioM2(Double.parseDouble(linea[i++].replace(",", "")));
                
                archivoLotes.add(archivoLote);
            }
            
            return archivoLotes;
        }
        
        return null;
    }
    
}
