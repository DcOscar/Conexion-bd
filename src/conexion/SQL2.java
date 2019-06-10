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
public class SQL2 {
 // url del driver JDBC y la base de datos
 static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
 static final String DB_URL = "jdbc:mariadb://localhost/Comida";
 // usuario y contraseña de la base de datos
 static final String USER = "root";
 static final String PASS = "";

 public static void main(String[] args) {
 Connection conn = null;
 Statement stmt = null;
 try{
 //Paso 2: Cargar driver JDBC
 Class.forName("org.mariadb.jdbc.Driver");
 //Paso 3: Abrir una conexion
 System.out.println("Conectando a la base de datos...");
 conn = DriverManager.getConnection(DB_URL,USER,PASS);
 //Paso 4: Ejecutar insert
 System.out.println("Creando declaracion...");
 stmt = conn.createStatement();
 String sql;

 sql = "INSERT INTO Pizza " +
 "VALUES (4, 4000, 'Queso y pepperoni')";
 stmt.executeUpdate(sql);
 sql = "INSERT INTO Pizza " +
 "VALUES (5, 5000, 'Hawaiana')";
 stmt.executeUpdate(sql);
 sql = "INSERT INTO Pizza " +
 "VALUES(6, 6000, 'Pollo')";
 stmt.executeUpdate(sql);
 System.out.println("Insertados...");
 //Paso 5: Limpiar
 //rs.close();
 stmt.close();
 conn.close();
 }catch(SQLException se){
 //Errores de jdbc
 se.printStackTrace();
 }catch(Exception e){
 //Errores de Class.forName
 e.printStackTrace();
 }finally{
 //bloque usado para cerrar recursos
 try{
 if(stmt!=null)
 stmt.close();
 }catch(SQLException se2){
 }// nada que hacer
 try{
 if(conn!=null)
 conn.close();
 }catch(SQLException se){
 se.printStackTrace();
 }
 }
 System.out.println("Adios!");
 }

}
