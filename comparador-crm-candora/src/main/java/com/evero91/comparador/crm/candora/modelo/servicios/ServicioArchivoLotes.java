/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evero91.comparador.crm.candora.modelo.servicios;

import com.evero91.comparador.crm.candora.modelo.AccesoBD;
import com.evero91.comparador.crm.candora.modelo.pojo.ArchivoLote;
import com.evero91.comparador.crm.candora.utilerias.LectorCSV;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
                archivoLote.setM2(Float.parseFloat(linea[i++]));
                archivoLote.setMedidas(linea[i++]);                
                archivoLote.setPrecioM2(Double.parseDouble(linea[i++].replace(",", "")));                
                archivoLotes.add(archivoLote);
            }
            
            return archivoLotes;
        }
        
        return null;
    }
    
    public String getInsertQuery(AccesoBD accesoBD, ArchivoLote archivoLote){
        PreparedStatement ps = null;
        String sql = "INSERT INTO Lotes (Manzana, Lote, Supeficie, Medidas, Precio) VALUES (?,?,?,?,?);";
        try {
            ps = accesoBD.getConexion().prepareStatement(sql);
            int i = 1;

            ps.setString(i++, archivoLote.getManzana());
            ps.setObject(i++, archivoLote.getLote());
            ps.setObject(i++, archivoLote.getM2());
            ps.setString(i++, archivoLote.getMedidas());
            ps.setObject(i++, archivoLote.getPrecioM2());
            
            String strPs = ps.toString();
            return strPs.substring(strPs.indexOf(":") + 2);
        } catch (SQLException e) {
            System.out.println("ServicioOportunidad_clasificaciones -> insertOportunidad_clasificaciones: " + e);
        } finally {
            accesoBD.liberarRecursos(ps);
        }

        return null;
    }
    
}
