/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evero91.comparador.crm.candora.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author sistemas
 */
public class AccesoBD {

    private Connection conexion;
    private final Properties properties;

    public AccesoBD() {
        properties = new Properties();
        properties.setProperty("user", "ever");
        properties.setProperty("password", "3v3r");
        properties.setProperty("serverTimezone", "America/Monterrey");
    }

    public boolean establecerConexion() {
        String host = "localhost";
        String port = "3306";
        String database = "crm-candora-produccrion";
        String url = "jdbc:mysql://" + host + ":" + port + "/" + database;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, properties);
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean establecerConexionSQLite() {
        String url = "jdbc:sqlite:/Users/sistemas/Documents/Ever/flopper/candora/bd/sam.db";

        try {
            Class.forName("org.sqlite.JDBC");
            conexion = DriverManager.getConnection(url);
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public void liberarRecursos(PreparedStatement... pss) {
        if (pss != null) {
            for (PreparedStatement ps : pss) {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException e) {
                        System.out.println("liberarRecursos pss: " + e);
                    }
                }
            }
        }
    }

    public void liberarRecursos(ResultSet... rss) {
        if (rss != null) {
            for (ResultSet rs : rss) {
                if (rs != null) {
                    try {
                        rs.close();

                    } catch (SQLException e) {
                        System.out.println("liberarRecursos rss: " + e);
                    }
                }
            }
        }
    }

    public void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException ex) {
                System.out.println("cerrarConexion: " + ex);
            }
        }
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

}
