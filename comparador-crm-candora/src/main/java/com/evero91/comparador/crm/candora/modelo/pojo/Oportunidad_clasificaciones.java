/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evero91.comparador.crm.candora.modelo.pojo;

import java.util.Date;

/**
 *
 * @author sistemas
 */
public class Oportunidad_clasificaciones {

    private String idOportunidadClasificacion;
    private String descripcion;
    private String idOportunidadClasificacionPadre;
    private Integer borrar;
    private Integer idEmpresa;
    private Date fechaModificacion;
    private String tipo;
    private String metros2;
    private String precioLista;

    public Oportunidad_clasificaciones() {
    }

    public Oportunidad_clasificaciones(ArchivoLote archivoLote) {
        this.descripcion = archivoLote.getLote().toString();
        this.tipo = archivoLote.getTipo();
        this.metros2 = archivoLote.getM2().toString();
        this.precioLista = String.valueOf(archivoLote.getM2() * archivoLote.getPrecioM2());
    }

    public String getIdOportunidadClasificacion() {
        return idOportunidadClasificacion;
    }

    public void setIdOportunidadClasificacion(String idOportunidadClasificacion) {
        this.idOportunidadClasificacion = idOportunidadClasificacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIdOportunidadClasificacionPadre() {
        return idOportunidadClasificacionPadre;
    }

    public void setIdOportunidadClasificacionPadre(String idOportunidadClasificacionPadre) {
        this.idOportunidadClasificacionPadre = idOportunidadClasificacionPadre;
    }

    public Integer getBorrar() {
        return borrar;
    }

    public void setBorrar(Integer borrar) {
        this.borrar = borrar;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMetros2() {
        return metros2;
    }

    public void setMetros2(String metros2) {
        this.metros2 = metros2;
    }

    public String getPrecioLista() {
        return precioLista;
    }

    public void setPrecioLista(String precioLista) {
        this.precioLista = precioLista;
    }
}
