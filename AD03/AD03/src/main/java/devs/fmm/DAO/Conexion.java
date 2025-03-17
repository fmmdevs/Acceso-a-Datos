package devs.fmm.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    // Creamos un objeto Connection
    private Connection conexion;

    // Creamos los parámetros de la conexión.
    private String BBDD;

    private String usuario;

    private String password;

    public Conexion(String nombreBBDD, String usuario, String password) {
        this.BBDD = nombreBBDD;
        this.usuario = usuario;
        this.password = password;
    }

    // Método para conectar con la BBDD que controla los errores que se puedan dar y devuelve un booleano en función
    // de si la conexión se ha establecido o no.
    public boolean conectar() {
        boolean correcto;

        try {
            this.conexion = DriverManager.getConnection("jdbc:mysql://localhost/" + this.BBDD, this.usuario, this.password);
            correcto = true;

        } catch (SQLException e) {
            System.err.println("Error al establecer la conexión " + e.getMessage());
            correcto = false;

        }
        return correcto;

    }

    // Método para cerrar la conexión que controla los errores
    public boolean desconectar() {
        boolean correcto;

        try {
            this.conexion.close();
            correcto = true;
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión " + e.getMessage());
            correcto = false;
        }

        return correcto;
    }

    // Para obtener el objeto Connection, que lo necesitaremos en alguna ocasión
    public Connection getConexion() {
        return conexion;
    }

}
