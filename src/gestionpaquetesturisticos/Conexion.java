/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionpaquetesturisticos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

//    private static final String URL = "jdbc:sqlite:gestionpaquetes.db";

//    public static Connection conectar() {
//        try {
//            Connection conexion = DriverManager.getConnection(URL);
//            System.out.println("conexion exitosa");
//            return conexion;
//        } catch (SQLException e) {
//            System.out.println("error al conectar: " + e.getMessage());
//            return null;
//        }
//    }
    
    private static final String URL = "jdbc:mysql://localhost:3306/gestion";

    private static final String USUARIO = "root";
    private static final String CONTRASENA = "Aurorita";

    public static Connection conectar() {
        try {
            Connection conexion = DriverManager.getConnection(
                    URL, USUARIO, CONTRASENA
            );

            System.out.println("Conexion exitosa a MySQL");
            return conexion;

        } catch (SQLException e) {
            System.out.println("Error al conectar con MySQL: "
                    + e.getMessage());
            return null;
        }
    }
    
}
