/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;
import store.Store;

/**
 *
 * @author luisj
 */
public class Operaciones {

    final Conexion conec = new Conexion();
    java.sql.PreparedStatement ps;
    java.sql.Connection con = null;
    java.sql.Statement st = null;
    java.sql.ResultSet rs = null;
    javax.swing.table.DefaultTableModel model;

    public void ingresar(int day, String month, int year, double ingreso, double gasto, double beneficio) {

        String consulta = "INSERT INTO tienda VALUES (Null, ?, ?, ?, ?, ?, ?)";
        try {
            con = conec.abrirConexion();
            ps = con.prepareStatement(consulta);
            ps.setInt(1, day);
            ps.setString(2, month);
            ps.setInt(3, year);
            ps.setDouble(4, ingreso);
            ps.setDouble(5, gasto);
            ps.setDouble(6, beneficio);
            ps.executeUpdate();
            ps.clearParameters();
            javax.swing.JOptionPane.showMessageDialog(null, "Se ha realizado el ingreso", "Correcto", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            ps.close();
            con.close();
        } catch (java.sql.SQLException x) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error en su consulta", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    public javax.swing.table.DefaultTableModel mostrarDatosTabla() {

        String consulta = "SELECT * FROM tienda ORDER BY id DESC";
        model = new javax.swing.table.DefaultTableModel();
        java.sql.ResultSet resultados = null;
        model.addColumn("id");
        model.addColumn("Día");
        model.addColumn("Mes");
        model.addColumn("Año");
        model.addColumn("Ingreso");
        model.addColumn("Gasto");
        model.addColumn("Beneficio");

        try {
            con = conec.abrirConexion();
            st = con.createStatement();
            rs = st.executeQuery(consulta);
            while (rs.next()) {
                model.addRow(new Object[]{rs.getInt("id"), rs.getInt("day"), rs.getString("month"), rs.getInt("year"), rs.getDouble("ingreso"), rs.getDouble("gasto"), rs.getDouble("beneficio")});
            }

        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "No se pudieron recuperar los datos", "Error de tabla", javax.swing.JOptionPane.ERROR_MESSAGE);
        }

        return model;
    }
    
    public void borrarDatos(int indice)
    {
        String consulta = "DELETE FROM tienda WHERE id = ?";
        con = conec.abrirConexion();
        try
        {
            ps = con.prepareStatement(consulta);
            ps.setInt(1, indice);
            ps.executeUpdate();
            ps.clearParameters();
            ps.close();
            con.close();
        }
        catch (SQLException ex)
        {
            javax.swing.JOptionPane.showMessageDialog(null, "No se pudieron borrar los datos", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        
        
    }
    
    public javax.swing.table.DefaultTableModel mostrarResumen(String month, int year)
    {
        String consulta = "SELECT * FROM tienda WHERE month=? AND year=? ORDER BY id DESC";
        model = new javax.swing.table.DefaultTableModel();
        java.sql.ResultSet resultados = null;
        model.addColumn("id");
        model.addColumn("Día");
        model.addColumn("Mes");
        model.addColumn("Año");
        model.addColumn("Ingreso");
        model.addColumn("Gasto");
        model.addColumn("Beneficio");
        con = conec.abrirConexion();
        try {
            
            ps = con.prepareStatement(consulta);
            ps.setString(1, month);
            ps.setInt(2, year);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                model.addRow(new Object[]{rs.getInt("id"), rs.getInt("day"), rs.getString("month"), rs.getInt("year"), rs.getDouble("ingreso"), rs.getDouble("gasto"), rs.getDouble("beneficio")});
            }

            ps.clearParameters();
            ps.close();
            con.close();
            
        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "No se pudieron recuperar los datos", "Error de tabla", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
            
        return model;
    }
}
