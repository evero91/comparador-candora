/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evero91.comparador.crm.candora;

import com.evero91.comparador.crm.candora.modelo.AccesoBD;
import com.evero91.comparador.crm.candora.modelo.pojo.ArchivoControl;
import com.evero91.comparador.crm.candora.modelo.pojo.ArchivoLote;
import com.evero91.comparador.crm.candora.modelo.pojo.Oportunidad_clasificaciones;
import com.evero91.comparador.crm.candora.modelo.servicios.ServicioArchivoLotes;
import com.evero91.comparador.crm.candora.modelo.servicios.ServicioOportunidad_clasificaciones;
import com.evero91.comparador.crm.candora.utilerias.FileManager;
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
        Integer borrar = 0;
        Integer idEmpresa = 1;

        List<ArchivoControl> archivosControl = new ArrayList<>();
        archivosControl.add(new ArchivoControl("lotesFer.csv", "ba392f22-3f36-451c-80d8-d73c48fc79af", "lotes"));

//        ServicioOportunidad_clasificaciones servicioOportunidad_clasificaciones = new ServicioOportunidad_clasificaciones();
        ServicioArchivoLotes servicioArchivoLotes = new ServicioArchivoLotes();

        for (ArchivoControl archivoControl : archivosControl) {
//            List<Oportunidad_clasificaciones> oportunidad_clasificaciones = servicioOportunidad_clasificaciones.listarOportunidad_clasificaciones(accesoBD, archivoControl.getIdOportunidadClasificacionPadre());
//
//            if (oportunidad_clasificaciones == null) {
//                System.out.println("Error al obtener oportunidad_clasificaciones");
//                return;
//            }

            String archivo = directorioTrabajo + archivoControl.getNombreArchivo();
            List<ArchivoLote> archivoLotes = servicioArchivoLotes.leerArchivoLotes(archivo);

            if (archivoLotes == null) {
                System.out.println("Error al leer archivo lotes");
            }
            
            List<String> queries = new ArrayList<>();

            // BUSCAR LOTES FALTANTES
            for (ArchivoLote archivoLote : archivoLotes) {
                queries.add(servicioArchivoLotes.getInsertQuery(accesoBD, archivoLote));
//                boolean loteEncontrado = false;
//
//                for (Oportunidad_clasificaciones oportunidad_clasificacion : oportunidad_clasificaciones) {
//                    if (archivoLote.getLote().toString().equals(oportunidad_clasificacion.getDescripcion())) {
//                        loteEncontrado = true;
//                        break;
//                    }
//                }
//
//                if (!loteEncontrado) {
//                    Oportunidad_clasificaciones nueva_oportunidad_clasificacion = new Oportunidad_clasificaciones(archivoLote);
//                    nueva_oportunidad_clasificacion.setIdOportunidadClasificacionPadre(archivoControl.getIdOportunidadClasificacionPadre());
//                    nueva_oportunidad_clasificacion.setBorrar(borrar);
//                    nueva_oportunidad_clasificacion.setIdEmpresa(idEmpresa);
//
//                    String query = servicioOportunidad_clasificaciones.strInsertOportunidad_clasificaciones(accesoBD, nueva_oportunidad_clasificacion);
//
//                    if (query == null) {
//                        System.out.println("Error al actualizar registro");
//                    } else {
//                        queries.add(query);
//                    }
//                }
            }

            // BUSCAR LOTES SOBRANTES
//            for (Oportunidad_clasificaciones oportunidad_clasificacion : oportunidad_clasificaciones) {
//                boolean loteEcontrado = false;
//
//                for (ArchivoLote archivoLote : archivoLotes) {
//                    if (oportunidad_clasificacion.getDescripcion().equals(archivoLote.getLote().toString())) {
//                        loteEcontrado = true;
//                        break;
//                    }
//                }
//
//                if (!loteEcontrado) {
//                    String query = servicioOportunidad_clasificaciones.strDeleteOportunidad_clasificaciones(accesoBD, oportunidad_clasificacion.getIdOportunidadClasificacion());
//
//                    if (query == null) {
//                        System.out.println("Error al eliminar registro");
//                    } else {
//                        queries.add(query);
//                    }
//                }
//            }

            // CREAR Y GUARDAR CONTENIDO ARCHIVO RESULTADO
            String archivoResultado = directorioTrabajo + "resultado" + archivoControl.getAlias() + ".txt";

            if (!FileManager.crearArchivo(archivoResultado, "")) {
                System.out.println("Error al crear archivo: " + archivoResultado);
                return;
            }

            if (!FileManager.guardarContenido(archivoResultado, queries)) {
                System.out.println("Error al guardar contenido: " + archivoResultado);
                return;
            }
        }

        accesoBD.cerrarConexion();

        System.out.println("Exito!");
    }

}
