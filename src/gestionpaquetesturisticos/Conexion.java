/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionpaquetesturisticos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:sqlite:gestionpaquetes.db";

    public static Connection conectar() {
        try {
            Connection conexion = DriverManager.getConnection(URL);
            System.out.println("conexion exitosa");
            return conexion;
        } catch (SQLException e) {
            System.out.println("error al conectar: " + e.getMessage());
            return null;
        }
    }
}
