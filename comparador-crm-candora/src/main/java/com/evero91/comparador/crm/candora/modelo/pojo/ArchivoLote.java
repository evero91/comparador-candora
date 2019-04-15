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
public class ArchivoLote {

    private String manzana;
    private Integer lote;
    private Float m2;
    private String tipo;
    private String medidas;
    private double precioM2;
    private String estatus;

    public String getMedidas() {
        return medidas;
    }

    public void setMedidas(String medidas) {
        this.medidas = medidas;
    }

    public String getManzana() {
        return manzana;
    }

    public void setManzana(String manzana) {
        this.manzana = manzana;
    }

    public Integer getLote() {
        return lote;
    }

    public void setLote(Integer lote) {
        this.lote = lote;
    }

    public Float getM2() {
        return m2;
    }

    public void setM2(Float m2) {
        this.m2 = m2;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecioM2() {
        return precioM2;
    }

    public void setPrecioM2(double precioM2) {
        this.precioM2 = precioM2;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

}
