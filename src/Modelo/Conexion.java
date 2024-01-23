/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.DriverManager;

public class Conexion {

    private String url;
    private java.sql.Connection con;
    private String driver;
    

    public java.sql.Connection abrirConexion() {
        url = "jdbc:sqlite:Database/Store.db";
        driver = "org.sqlite.JDBC";
        
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url);
 
        } catch (Exception x) {
            System.out.println("No se pudo establecer la conexión");
        }

       
        return con;
    }

    public void cerrarConexion() {
        try {
            con.close();
            System.out.println("Conexión cerrada con éxito");
        } catch (java.sql.SQLException x) {
            System.out.println("No se pudo cerrar la conexión.");
        }
    }

    
    
}
