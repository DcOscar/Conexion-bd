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
public class SQL4 {

    private Statement stmt;
    private Connection conn;

    public SQL4() {
        this.stmt = null;
        this.conn = null;
        this.inicializarBaseDatos();

    }

    public void inicializarBaseDatos() {

        // url del driver JDBC y la base de datos
        String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
        String DB_URL = "jdbc:mariadb://localhost/Persona";
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

    public void ingresarContacto(String nombre, String apellido, String telefono) {

        try {

            ResultSet rs = this.stmt.executeQuery("select COUNT (*) as cantidad from contacto");
            rs.next();
            int id = rs.getInt("cantidad") + 1;
            String sql = "INSERT INTO Contacto "
                    + "VALUES (" + id + ",'" + nombre + "','" + apellido + "','" + telefono + "')";
            this.stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("error");
        }

    }

    public void añadirContactos() {
        String nombres[] = {"Juan", "Pedro", "Antonio"};
        String apellidos[] = {"Gomez", "Lopez", "Alvarez"};
        String telefonos[] = {"987452154", "989654125", "985321478"};
        for (int i = 0; i < 3; i++) {
            this.ingresarContacto(nombres[i], apellidos[i], telefonos[i]);
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
                int idContacto = rs.getInt("idContacto");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String telefono = rs.getString("telefono");
                //Mostar resultados
                System.out.println("Nombre: " + nombre + ", apellido: " + apellido + ", telefono: " + telefono);
            }
        } catch (Exception e) {

            System.out.println("error");
        }

    }

    public void actualizarDatos(int id, String telefono) {
        try {
            String sql = "UPDATE contacto SET telefono = '" +telefono+"' where idContacto= " + id;
            //se actualiza el registro
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("error");
        }
    }
}
