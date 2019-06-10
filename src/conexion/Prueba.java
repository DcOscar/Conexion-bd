/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package conexion;
import java.util.*;
/**
 * 
 * @author Oscar
 */
public class Prueba {
    public static void main(String[] args) {
    MenuSQL sql= new MenuSQL("Comida");
    sql.inicializarBaseDatos();
    sql.borrarTabla("tablaEjemplo");
  sql.crearTabla("tablaEjemplo (idContacto INT UNSIGNED AUTO_INCREMENT PRIMARY KEY, nombre varchar(50), apellido varchar(50), telefono varchar(50))");
    String[] dato1= {"oscar","aguayo","14141414"};
    String[] dato2= {"ola","apellido","14131451"};
    String[] dato3={"nombre","apellido2","12345"};
    sql.ingresarDato("tablaEjemplo", dato1);
   sql.ingresarDato("tablaEjemplo", dato2);
   sql.ingresarDato("tablaEjemplo", dato3);
   sql.agregarColumna("tablaEjemplo", "ciudad", "varchar(50)");
    sql.actualizarDatos("tablaEjemplo", 1, "ciudad", "Temuco");
    sql.actualizarDatos("tablaEjemplo", 2, "ciudad","UFRO");
    sql.actualizarDatos("tablaEjemplo", 3, "ciudad","DCI");
    sql.mostrarTodo("tablaEjemplo");
    sql.cerrarBaseDatos();
    }
}
