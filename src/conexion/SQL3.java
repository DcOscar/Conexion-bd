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
public class SQL3 {

    private Statement stmt;
    private Connection conn;

    public SQL3() {
              this.stmt = null;
        this.conn = null;
        this.inicializarBaseDatos();

    }

    public void inicializarBaseDatos() {

        // url del driver JDBC y la base de datos
        String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
        String DB_URL = "jdbc:mariadb://localhost/Comida";
        // usuario y contraseña de la base de datos
        String USER = "root";
        String PASS = "";
        try {
            //Paso 2: Cargar driver JDBC
            Class.forName("org.mariadb.jdbc.Driver");
            //Paso 3: Abrir una conexion
            System.out.println("Conectando a la base de datos...");

            this.conn = DriverManager.getConnection(DB_URL, USER, PASS);
                        this.stmt = this.conn.createStatement();
        } catch (Exception e) {
            System.out.println("error");
        }

    }

    public void ingresarPizzas(String nombre, int precio) {

        try {

            ResultSet rs = this.stmt.executeQuery("select COUNT (*) as cantidad from pizza");
            rs.next();
            int cantidad= rs.getInt("cantidad")+1;
            String sql = "INSERT INTO Pizza "
                    + "VALUES ("+ cantidad +","+ precio +",'"+ nombre +"')";
            this.stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("error");
        }

    }

    public void cerrarBaseDatos() {
        try {
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Errores de jdbc
            se.printStackTrace();
        } catch (Exception e) {
            //Errores de Class.forName
            e.printStackTrace();
        } finally {
            //bloque usado para cerrar recursos
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nada que hacer
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

    }

    public void mostrar() {
        try {
            String sql;
            sql = "SELECT * FROM contacto";
            ResultSet rs = stmt.executeQuery(sql);
            //Paso 5: Extraer datos del resultado
            while (rs.next()) {
                //Recuperar por el nombre de la columna
                int codigoPizza = rs.getInt("codigoPizza");
                int valorPizza = rs.getInt("valorPizza");
                String nombrePizza = rs.getString("nombrePizza");
                //Mostar resultados
                System.out.print("Codigo: " + codigoPizza);
                System.out.print(", Valor: " + valorPizza);
                System.out.println(", Nombre: " + nombrePizza);
            }
        } catch (Exception e) {

            System.out.println("error");
        }

    }
}
