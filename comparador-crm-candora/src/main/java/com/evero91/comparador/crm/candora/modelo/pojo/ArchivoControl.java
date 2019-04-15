/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evero91.comparador.crm.candora.modelo.pojo;

/**
 *
 * @author sistemas
 */
public class ArchivoControl {

    private String nombreArchivo;
    private String idOportunidadClasificacionPadre;
    private String alias;

    public ArchivoControl() {
    }

    public ArchivoControl(String nombreArchivo, String idOportunidadClasificacionPadre, String alias) {
        this.nombreArchivo = nombreArchivo;
        this.idOportunidadClasificacionPadre = idOportunidadClasificacionPadre;
        this.alias = alias;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getIdOportunidadClasificacionPadre() {
        return idOportunidadClasificacionPadre;
    }

    public void setIdOportunidadClasificacionPadre(String idOportunidadClasificacionPadre) {
        this.idOportunidadClasificacionPadre = idOportunidadClasificacionPadre;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

}
