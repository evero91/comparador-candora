/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evero91.comparador.crm.candora.modelo.servicios;

import com.evero91.comparador.crm.candora.modelo.AccesoBD;
import com.evero91.comparador.crm.candora.modelo.pojo.Oportunidad_clasificaciones;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sistemas
 */
public class ServicioOportunidad_clasificaciones {

    public List<Oportunidad_clasificaciones> listarOportunidad_clasificaciones(AccesoBD accesoBD, String idOportunidadClasificacionPadre) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT idOportunidadClasificacion, descripcion, idOportunidadClasificacionPadre, borrar, \n"
                + "idEmpresa, fechaModificacion, tipo, metros2, \n "
                + "precioLista \n"
                + "FROM oportunidades_clasificaciones \n"
                + "WHERE idOportunidadClasificacionPadre = ?";
        try {
            ps = accesoBD.getConexion().prepareStatement(sql);
            ps.setString(1, idOportunidadClasificacionPadre);
            rs = ps.executeQuery();

            List<Oportunidad_clasificaciones> oportunidad_clasificaciones = new ArrayList<>();

            while (rs.next()) {
                Oportunidad_clasificaciones oportunidad_clasificacion = new Oportunidad_clasificaciones();
                llenarObjeto(rs, oportunidad_clasificacion);
                oportunidad_clasificaciones.add(oportunidad_clasificacion);
            }

            return oportunidad_clasificaciones;
        } catch (SQLException e) {
            System.out.println("ServicioOportunidad_clasificaciones -> listarLotes: " + e);
        } finally {
            accesoBD.liberarRecursos(ps);
            accesoBD.liberarRecursos(rs);
        }

        return null;
    }

    public String strInsertOportunidad_clasificaciones(AccesoBD accesoBD, Oportunidad_clasificaciones oportunidad_clasificacion) {
        PreparedStatement ps = null;
        String sql = "INSERT INTO oportunidades_clasificaciones "
                + "(idOportunidadClasificacion, descripcion, idOportunidadClasificacionPadre, borrar, "
                + "idEmpresa, fechaModificacion, tipo, metros2, "
                + "precioLista) "
                + "VALUES(UUID(), ?, ?, ?, "
                + "?, NOW(), ?, ?, "
                + "?);";
        try {
            ps = accesoBD.getConexion().prepareStatement(sql);
            int i = 1;

            ps.setString(i++, oportunidad_clasificacion.getDescripcion());
            ps.setString(i++, oportunidad_clasificacion.getIdOportunidadClasificacionPadre());
            ps.setObject(i++, oportunidad_clasificacion.getBorrar());

            ps.setObject(i++, oportunidad_clasificacion.getIdEmpresa());
            ps.setString(i++, oportunidad_clasificacion.getTipo());
            ps.setString(i++, oportunidad_clasificacion.getMetros2());

            ps.setString(i++, oportunidad_clasificacion.getPrecioLista());

            String strPs = ps.toString();
            return strPs.substring(strPs.indexOf(":") + 2);
        } catch (SQLException e) {
            System.out.println("ServicioOportunidad_clasificaciones -> insertOportunidad_clasificaciones: " + e);
        } finally {
            accesoBD.liberarRecursos(ps);
        }

        return null;
    }
    
    public String strDeleteOportunidad_clasificaciones(AccesoBD accesoBD, String idOportunidadClasificacion) {
        PreparedStatement ps = null;
        String sql = "DELETE FROM oportunidades_clasificaciones "
                + "WHERE idOportunidadClasificacion = ?;";
        try {
            ps = accesoBD.getConexion().prepareStatement(sql);
            ps.setString(1, idOportunidadClasificacion);

            String strPs = ps.toString();
            return strPs.substring(strPs.indexOf(":") + 2);
        } catch (SQLException e) {
            System.out.println("ServicioOportunidad_clasificaciones -> strDeleteOportunidad_clasificaciones: " + e);
        } finally {
            accesoBD.liberarRecursos(ps);
        }

        return null;
    }

    private void llenarObjeto(ResultSet rs, Oportunidad_clasificaciones oportunidad_clasificaciones) throws SQLException {
        oportunidad_clasificaciones.setIdOportunidadClasificacion(rs.getString("idOportunidadClasificacion"));
        oportunidad_clasificaciones.setDescripcion(rs.getString("descripcion"));
        oportunidad_clasificaciones.setIdOportunidadClasificacionPadre(rs.getString("idOportunidadClasificacionPadre"));
        oportunidad_clasificaciones.setBorrar((Integer) rs.getObject("borrar"));

        oportunidad_clasificaciones.setIdEmpresa((Integer) rs.getObject("idEmpresa"));
        oportunidad_clasificaciones.setFechaModificacion(rs.getTimestamp("fechaModificacion"));
        oportunidad_clasificaciones.setTipo(rs.getString("tipo"));
        oportunidad_clasificaciones.setMetros2(rs.getString("metros2"));

        oportunidad_clasificaciones.setPrecioLista(rs.getString("precioLista"));
    }

}
