/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionpaquetesturisticos;

import java.sql.Connection;
import java.sql.Statement;

public class BaseDatosDefault {
    public static void main(String[] args) {

        String[] sql = {
            "insert to tipo_usuarios;",
            "insert to users (name, age) values ('Alice', 30);",
        };

        try (Connection conexion = Conexion.conectar();
            Statement statement = conexion.createStatement()) {
            for (int i = 0; i < 5; i++) {
                statement.execute(sql[i]);
                System.out.println("tabla creada");
            }

        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }
}
