/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;
import java.sql.*;
/**
 *
 * @author Oscar
 */
public class Conexion {

public static void main(String[] args) {
 Connection conexion = null;
 try {
 // Cargar el driver
 Class.forName("org.mariadb.jdbc.Driver");
 // Se obtiene una conexión con la base de datos.
 // En este caso nos conectamos a la base de datos prueba
 // con el usuario root y contraseña 1daw
 conexion = DriverManager.getConnection("jdbc:mariadb://localhost/Comida", "root", "");
 // Se crea un Statement, para realizar la consulta
 Statement s = conexion.createStatement();
 // Se realiza la consulta. Los resultados se guardan en el ResultSet rs
 ResultSet rs = s.executeQuery("select * from pizza");
 // Se recorre el ResultSet, mostrando por pantalla los resultados.
 while (rs.next()) {
 System.out.println(rs.getInt("codigoPizza") + " " + rs.getString("nombrePizza"));
 }
 } catch (SQLException e) {
 System.out.println(e.getMessage());
 } catch (ClassNotFoundException e) {
 System.out.println(e.getMessage());
 } finally { // Se cierra la conexión con la base de datos.
 try {
 if (conexion != null) {
 conexion.close();
 }
 } catch (SQLException ex) {
 System.out.println(ex.getMessage());
 }
 }
 }
    
}
