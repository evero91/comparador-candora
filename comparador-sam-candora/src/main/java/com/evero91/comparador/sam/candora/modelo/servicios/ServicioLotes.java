/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evero91.comparador.sam.candora.modelo.servicios;

import com.evero91.comparador.sam.candora.modelo.AccesoBD;
import com.evero91.comparador.sam.candora.modelo.pojo.Lotes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sistemas
 */
public class ServicioLotes {

    public Lotes obtenerLote(AccesoBD accesoBD, int numLote, String manzana) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT idLote, Radio, x, y, \n"
                + "Lote, Etapa, Manzana, Superficie, \n"
                + "Precio, Ubicacion, Forma, Medidas, \n"
                + "Layer, idTipoLote, idSeccion, idModelo\n"
                + "FROM Lotes \n"
                + "WHERE Lote = ? \n"
                + "AND Manzana = ?";
        try {
            ps = accesoBD.getConexion().prepareStatement(sql);
            ps.setInt(1, numLote);
            ps.setString(2, manzana);
            rs = ps.executeQuery();

            Lotes lote = new Lotes();

            if (rs.next()) {
                recuperarLote(lote, rs);
            }

            return lote;
        } catch (SQLException e) {
            System.out.println("ServicioLotes -> obtenerLote: " + e);
        } finally {
            accesoBD.liberarRecursos(ps);
            accesoBD.liberarRecursos(rs);
        }

        return null;
    }

    public List<Lotes> listarLotes(AccesoBD accesoBD) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT idLote, Radio, x, y, \n"
                + "Lote, Etapa, Manzana, Superficie, \n"
                + "Precio, Ubicacion, Forma, Medidas, \n"
                + "Layer, idTipoLote, idSeccion, idModelo\n"
                + "FROM Lotes";
        try {
            ps = accesoBD.getConexion().prepareStatement(sql);
            rs = ps.executeQuery();

            List<Lotes> lotes = new ArrayList<>();

            if (rs.next()) {
                Lotes lote = new Lotes();
                recuperarLote(lote, rs);
                lotes.add(lote);
            }

            return lotes;
        } catch (SQLException e) {
            System.out.println("ServicioLotes -> listarLotes: " + e);
        } finally {
            accesoBD.liberarRecursos(ps);
            accesoBD.liberarRecursos(rs);
        }

        return null;
    }

    public String strInsertLote(AccesoBD accesoBD, Lotes lote) {
        PreparedStatement ps = null;
        String sql = "INSERT INTO Lotes("
                + "Radio, Lote, Etapa, Manzana, "
                + "Superficie, Precio, Forma, Layer, "
                + "idSeccion) "
                + "VALUES(?, ?, ?, ?, "
                + "?, ? ,? ,?, "
                + "?);";
        try {
            ps = accesoBD.getConexion().prepareStatement(sql);
            int i = 1;
            ps.setObject(i++, lote.getRadio());
            ps.setObject(i++, lote.getLote());
            ps.setString(i++, lote.getEtapa());
            ps.setString(i++, lote.getManzana());

            ps.setString(i++, lote.getSuperficie());
            ps.setDouble(i++, lote.getPrecio());
            ps.setObject(i++, lote.getForma());
            ps.setObject(i++, lote.getLayer());

            ps.setObject(i++, lote.getIdSeccion());

            String strPs = ps.toString();
            return strPs.substring(strPs.indexOf(":") + 2);
        } catch (SQLException e) {
            System.out.println("ServicioLotes -> strInsertLote: " + e);
        } finally {
            accesoBD.liberarRecursos(ps);
        }

        return null;
    }

    public String strUpdateLote(AccesoBD accesoBD, Lotes lote) {
        PreparedStatement ps = null;
        String sql = "UPDATE Lotes SET "
                + "Superficie = ?, Medidas = ?, Precio = ? "
                + "WHERE idLote = ?;";
        try {
            ps = accesoBD.getConexion().prepareStatement(sql);
            int i = 1;
            ps.setString(i++, lote.getSuperficie());
            ps.setString(i++, lote.getMedidas());
            ps.setDouble(i++, lote.getPrecio());

            ps.setObject(i++, lote.getIdLote());

            String strPs = ps.toString();
            return strPs.substring(strPs.indexOf(":") + 2);
        } catch (SQLException e) {
            System.out.println("ServicioLotes -> strUpdateLote: " + e);
        } finally {
            accesoBD.liberarRecursos(ps);
        }

        return null;
    }
    
    public String strDeleteLote(AccesoBD accesoBD, Lotes lote) {
        PreparedStatement ps = null;
        String sql = "DELETE FROM Lotes "
                + "WHERE idLote = ?;";
        try {
            ps = accesoBD.getConexion().prepareStatement(sql);
            ps.setObject(1, lote.getIdLote());

            String strPs = ps.toString();
            return strPs.substring(strPs.indexOf(":") + 2);
        } catch (SQLException e) {
            System.out.println("ServicioLotes -> strDeleteLote: " + e);
        } finally {
            accesoBD.liberarRecursos(ps);
        }

        return null;
    }

    private void recuperarLote(Lotes lote, ResultSet rs) throws SQLException {
        lote.setIdLote(rs.getInt("idLote"));
        lote.setRadio((Integer) rs.getObject("Radio"));
        lote.setX((Integer) rs.getObject("x"));
        lote.setY((Integer) rs.getObject("y"));

        lote.setLote((Integer) rs.getObject("Lote"));
        lote.setEtapa(rs.getString("Etapa"));
        lote.setManzana(rs.getString("Manzana"));
        lote.setSuperficie(rs.getString("Superficie"));

        lote.setPrecio(rs.getDouble("Precio"));
        lote.setUbicacion(rs.getString("Ubicacion"));
        lote.setForma((Integer) rs.getObject("Forma"));
        lote.setMedidas(rs.getString("Medidas"));

        lote.setLayer((Integer) rs.getObject("Layer"));
        lote.setIdTipoLote((Integer) rs.getObject("idTipoLote"));
        lote.setIdSeccion((Integer) rs.getObject("idSeccion"));
        lote.setIdModelo(rs.getString("idModelo"));
    }
}
