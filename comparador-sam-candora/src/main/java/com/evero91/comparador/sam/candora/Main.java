/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evero91.comparador.sam.candora;

import com.evero91.comparador.sam.candora.modelo.AccesoBD;
import com.evero91.comparador.sam.candora.modelo.pojo.ArchivoLote;
import com.evero91.comparador.sam.candora.modelo.pojo.Lotes;
import com.evero91.comparador.sam.candora.modelo.servicios.ServicioArchivoLotes;
import com.evero91.comparador.sam.candora.modelo.servicios.ServicioLotes;
import com.evero91.comparador.sam.candora.utilerias.FileManager;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sistemas
 */
public class Main {

    public static void main(String args[]) {
        AccesoBD accesoBD = new AccesoBD();

        if (!accesoBD.establecerConexion()) {
            System.out.println("Error de conexion");
            return;
        }
        
        String directorioTrabajo = "/Users/sistemas/Documents/Ever/flopper/candora/";
        String archivo = "lotesFer.csv";

        ServicioLotes servicioLotes = new ServicioLotes();
        ServicioArchivoLotes servicioArchivoLotes = new ServicioArchivoLotes();

        List<ArchivoLote> archivoLotes = servicioArchivoLotes.leerArchivoLotes(directorioTrabajo + archivo);
        List<String> queries = new ArrayList<>();
        
        Integer radio = 48;
        Integer etapa = 1;
        Integer forma = 0;
        Integer layer = 0;
        Integer idSeccion = 1;

        if (archivoLotes == null) {
            System.out.println("Error al leer archivo lotes");
            return;
        }

        // OBTENER LOTES QUE EXISTEN Y QUE FALTAN
        for (ArchivoLote archivoLote : archivoLotes) {
            Lotes lote = servicioLotes.obtenerLote(accesoBD, archivoLote.getLote(), archivoLote.getManzana());

            if (lote == null) {
                System.out.println("Error al buscar lote: " + archivoLote.getLote() + " " + archivoLote.getManzana());
                return;
            } else {
                if (lote.getIdLote() == null) {
                    // FALTA LOTE
                    // HACER INSERT
                    lote.setRadio(radio);
                    lote.setLote(archivoLote.getLote());
                    lote.setEtapa(etapa.toString());
                    lote.setManzana(archivoLote.getManzana());
                    
                    lote.setSuperficie(archivoLote.getSuperficie().toString());
                    lote.setPrecio(archivoLote.getPrecioM2());
                    lote.setForma(forma);
                    lote.setLayer(layer);
                    
                    lote.setIdSeccion(idSeccion);
                    
                    queries.add(servicioLotes.strInsertLote(accesoBD, lote));
                } else {
                    // EXISTE LOTE
                    // HACER UPDATE
                    lote.setSuperficie(archivoLote.getSuperficie().toString());
                    lote.setMedidas(archivoLote.getMedidas());
                    lote.setPrecio(archivoLote.getPrecioM2());
                    
                    queries.add(servicioLotes.strUpdateLote(accesoBD, lote));
                }
            }
        }

        // PARA BUSCAR SOBRANTES LEER BASE DE DATOS
        // COMPARAR CON LOS EXISTENTES -> SINO ESTA EN LOS EXISTENTES QUIERE DECIR QUE EL REGISTRO SOBRA
        
        List<Lotes> lotesBD = servicioLotes.listarLotes(accesoBD);
        
        if (lotesBD == null) {
            System.out.println("Error al obtener lotes existentes");
            return;
        }
        
        for (Lotes loteBD : lotesBD) {
            boolean loteEcontrado = false;
            
            for (ArchivoLote archivoLote : archivoLotes) {
                if (loteBD.getLote().intValue() == archivoLote.getLote().intValue() && loteBD.getManzana().equals(archivoLote.getManzana())) {
                    loteEcontrado = true;
                    break;
                }
            }
            
            if (!loteEcontrado) {
                // LOTE SOBRANTE
                queries.add(servicioLotes.strDeleteLote(accesoBD, loteBD));
            }
        }
        
        // CREAR Y GUARDAR CONTENIDO ARCHIVO RESULTADO
        String archivoResultado = directorioTrabajo + "resultado_" + archivo + ".txt";

        if (!FileManager.crearArchivo(archivoResultado, "")) {
            System.out.println("Error al crear archivo: " + archivoResultado);
            return;
        }

        if (!FileManager.guardarContenido(archivoResultado, queries)) {
            System.out.println("Error al guardar contenido: " + archivoResultado);
            return;
        }

        accesoBD.cerrarConexion();

        System.out.println("Exito!");
    }

}
