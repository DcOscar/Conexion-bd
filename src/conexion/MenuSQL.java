/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.*;
import java.util.*;

/**
 *
 * @author Oscar
 */
public class MenuSQL {

    private Statement stmt;
    private Connection conn;
    private String baseDatos;

    public MenuSQL(String baseDatos) {
        this.stmt = null;
        this.conn = null;
        this.baseDatos = baseDatos;
    }

    public String getBaseDatos() {
        return baseDatos;
    }

    public void setBaseDatos(String baseDatos) {
        this.baseDatos = baseDatos;
    }

    public void inicializarBaseDatos() {

        // url del driver JDBC y la base de datos
        String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
        String DB_URL = "jdbc:mariadb://localhost/" + this.baseDatos;
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
            System.out.println("error al ingresar a la base de datos");
        }

    }

    public void crearTabla(String datosTabla) {
        try {
            this.stmt.executeUpdate("create table " + datosTabla);
        } catch (Exception e) {
            System.out.println("error al crear tabla");
        }

    }

    public void ingresarDato(String tabla, String[] datos) {

        try {

            ResultSet rs = this.stmt.executeQuery("select COUNT (*) as cantidad from " + tabla);
            rs.next();
            int id = rs.getInt("cantidad") + 1;
            String sql = "INSERT INTO " + tabla
                    + " VALUES (" + id + ",'" + datos[0] + "','" + datos[1] + "','" + datos[2] + "')";
            this.stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("error al ingresar datos");
        }

    }

    public void agregarClaveForanea(String tabla, String tablaArelacionar, String nombreClaveForanea) {
        try {

            this.stmt.executeUpdate("alter table " + tabla + " add " + nombreClaveForanea + "int unsigned");
            this.stmt.executeUpdate("alter table " + tabla + " add foreign key(" + nombreClaveForanea + ") references " + tablaArelacionar + "("
                    + nombreClaveForanea + ")");

        } catch (Exception e) {
            System.out.println("Error al agregar clave foranea");
        }
    }

    public void agregarColumna(String tabla, String nombreColumna, String tipoColumna) {
        try {

            this.stmt.executeUpdate("alter table " + tabla + " add column " + nombreColumna + " " + tipoColumna);
        } catch (Exception e) {
            System.out.println("Error al agregar columna");
        }
    }

    public void borrarTabla(String tabla) {
        try {
            this.stmt.execute("drop table " + tabla);
        } catch (Exception e) {
            System.out.println("error al borrar tabla");
        }
    }

    public void borrarClaveForanea(String tabla, String nombreConstraint, String nombreClaveForanea) {
        try {
            this.stmt.executeUpdate("alter table " + tabla + " drop foreign key " + nombreConstraint);
            this.stmt.executeUpdate("alter table " + tabla + " drop column " + nombreClaveForanea);
        } catch (Exception e) {
        }
        System.out.println("error al borrar clave foranea");
    }

    public void mostrarTodo(String tabla) {
        // HAY QUE CAMBIAR LOS NOMBRES PARA ESPECIFICAR LUEGO
        try {
            String sql;
            sql = "SELECT * FROM " + tabla;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //Recuperar por el nombre de la columna
                int idContacto = rs.getInt("idContacto");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String telefono = rs.getString("telefono");
                String ciudad= rs.getString("ciudad");
                //Mostar resultados
                    System.out.println("id= "+ idContacto+ ", Nombre: " + nombre + ", apellido: " + apellido + ", telefono: " + telefono+", ciudad: "+ciudad);
            }
        } catch (Exception e) {

            System.out.println("error al mostrar datos");
        }
    }

    public void mostrar(String tabla, String condicion) {
        // HAY QUE CAMBIAR LOS NOMBRES PARA ESPECIFICAR LUEGO
        try {
            String sql;
            sql = "SELECT * FROM " + tabla + condicion;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //Recuperar por el nombre de la columna
                int idContacto = rs.getInt("idContacto");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String telefono = rs.getString("telefono");
                //Mostar resultados
                System.out.println("id="+ idContacto+ ", Nombre: " + nombre + ", apellido: " + apellido + ", telefono: " + telefono);
            }
        } catch (Exception e) {

            System.out.println("error al mostrar datos");
        }
    }

    public void mostrarDato(String tabla, String dato, String condicion) {
        // HAY QUE CAMBIAR LOS NOMBRES PARA ESPECIFICAR LUEGO
        try {
            String sql;
            sql = "SELECT " + dato + " FROM " + tabla + condicion;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //Recuperar por el nombre de la columna
                String resultadoDato = rs.getString("columnaDato");

                //Mostar resultados
                System.out.println("dato: " + resultadoDato);
            }
        } catch (Exception e) {

            System.out.println("error al mostrar datos");
        }
    }

    public void actualizarDatos(String tabla, int id, String columna, String nuevoDato) {
        try {
            String sql = "UPDATE " + tabla + " SET " +columna+" = '" + nuevoDato + "' where idContacto= " + id;
            //se actualiza el registro
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("error al actualizar datos");
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

}
