/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionpaquetesturisticos;
import java.sql.Connection;
import java.sql.Statement;

public class GenerarBaseDeDatos {

    public static void main(String[] args) {

        String[] sql = {
            "create table paquetes (id_paquete integer primary key autoincrement, nombre text, ciudad text, pais text, estado integer, descripcion text, precio real, dias_duracion integer);",
            "create table clientes (id_cliente integer primary key autoincrement, nombre text, documento text, telefono text);",
            "create table tipos_empleado (id_tipo integer primary key autoincrement, nombre text);",
            "create table empleados (id_empleado integer primary key autoincrement, nombre text, documento text, tipo integer, salario real, direccion text, foreign key (tipo) references tipos_empleado(id_tipo));",
            "create table ventas (id_venta integer primary key autoincrement, cliente integer, paquete integer, empleado integer, fecha date, pago real, foreign key (cliente) references clientes(id_cliente), foreign key (empleado) references empleados(id_empleado), foreign key (paquete) references paquetes(id_paquete))"};

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
