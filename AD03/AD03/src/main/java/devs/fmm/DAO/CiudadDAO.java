package devs.fmm.DAO;

import devs.fmm.Entities.Ciudad;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CiudadDAO {
    // Creamos un atributo Connection para usarlo cualquier método del servicio
    private Conexion c = new Conexion("world", "super", "alumno");

    // Obtener todos los registros de la tabla city
    public ArrayList<Ciudad> getAllCiudades() {
        ArrayList<Ciudad> ciudades = new ArrayList<>();

        // Conectamos con la base de datos a través del método conectar de nuestro objeto Conexion
        if (c.conectar()) {
            try {
                // A partir del objeto Connection obtenemos le objeto Statement para ejecutar una sentencia simple sin parámetros
                Statement ps = c.getConexion().createStatement();

                // Creamos la query, aunque esta query actualmente sea equivalente a un "SELECT * FROM city" es mejor
                // que indiquemos los parámetros de forma explícita, de esta forma, si en algún momento se añade un campo
                // esta query no fallaría (nuestra entidad tiene los campos de esta query).
                String queryAll = "SELECT ID, Name, CountryCode, District, Population FROM city";

                // Ejecutamos la query y la recibimos en el objeto de tipo ResultSet
                ResultSet rs = ps.executeQuery(queryAll);

                // Creamos un objeto de tipo ciudad sin inicializar
                Ciudad c;

                // Variables con el nombre del atributo de la clase Ciudad y que se corresponden con los campos de la tabla city
                // No son necesarios pero los he añadido por legibilidad
                int id, poblacion;
                String nombre, codigoPais, distrito;

                // En cada iteracion de ResultSet hay una fila
                while (rs.next()) {
                    // Obtenemos los campos de la fila en el objeto ResultSet y lo asignamos a la variable correspondiente
                    id = Integer.parseInt(rs.getString(1));
                    nombre = rs.getString(2);
                    codigoPais = rs.getString(3);
                    distrito = rs.getString(4);
                    poblacion = Integer.parseInt(rs.getString(5));

                    // Creamos un objeto de tipo Ciudad con los valores obtenidos de la query
                    c = new Ciudad(id, nombre, codigoPais, distrito, poblacion);

                    // Añadimos la Ciudad al ArrayList<Ciudad> ciudades.
                    ciudades.add(c);
                }
            } catch (SQLException e) {
                System.err.println("Error en la consulta " + e.getMessage());
            } finally {
                // Desconectamos la conexión pase lo que pase
                c.desconectar();
            }
        } else {
            System.err.println("Error en la conexión con la BBDD");
        }

        return ciudades;
    }

    // Método para insertar una ciudad en la tabla city
    public boolean crearCiudad(Ciudad ciudad) {

        boolean correcto;

        //Conecto a la base de datos, ya definida en el atributo de la clase
        //Solo seguiré el proceso si consigo conectar
        if (c.conectar()) {

            try {
                // Utilizamos un PreparedStatement para nuestra consulta con parámetros ya que es mucho más seguro que
                // utilizar un Statement y concatenar los valores (nos pueden injectar código SQL malicioso)
                //
                PreparedStatement ps = c.getConexion().prepareStatement("INSERT INTO `city` (`ID`,`Name`, `CountryCode`, `District`, `Population`) VALUES ( ?, ?, ?, ?, ?)");


                ps.setInt(1, ciudad.getId());
                ps.setString(2, ciudad.getNombre());
                ps.setString(3, ciudad.getCodigoPais());
                ps.setString(4, ciudad.getDistrito());
                ps.setInt(5, ciudad.getPoblacion());

                ps.execute();

                correcto = true;
            } catch (SQLException se) {
                System.err.println("Error al insertar el registro " + ciudad.getNombre() + " en la tabla city -> " + se.getMessage());
                correcto = false;
            } finally {
                // Desconectamos la conexión pase lo que pase
                c.desconectar();
            }

        } else {
            correcto = false;
            System.err.println("Error al conectar la base de datos");
        }


        return correcto;
    }


    // Método para borrar una ciudad en base a su id
    public boolean borrarCiudad(int id) {
        boolean eliminado;

        if (c.conectar()) {
            try {
                PreparedStatement ps = c.getConexion().prepareStatement("DELETE FROM city WHERE ID=?");
                ps.setInt(1, id);
                // si executeUpdate devuelve 0 no se ha actualizado ninguna fila
                // por lo que no se ha borrado la ciudad por que no existía ese un registro con ese id
                if (ps.executeUpdate() == 0) {
                    System.err.println("No se ha encontrado ciudad con id " + id);
                    eliminado = false;
                } else {

                    eliminado = true;
                }

            } catch (SQLException e) {
                System.err.println("Error al borrar el registro con ID " + id);
                eliminado = false;
            } finally {
                // Desconectamos la conexión pase lo que pase
                c.desconectar();
            }
        } else {
            eliminado = false;
            System.err.println("Error al conectar con la base de datos");
        }
        return eliminado;
    }

    // Metodo para cambiar el pais al que pertenece una ciudad en base a su id
    public boolean cambiarPaisCiudad(int id, String codigoPais) {
        boolean cambiado;

        if (c.conectar()) {
            try {
                PreparedStatement ps = c.getConexion().prepareStatement("UPDATE city " +
                        "SET CountryCode=? WHERE ID=?");

                ps.setString(1, codigoPais);
                ps.setInt(2, id);
                // si executeUpdate devuelve 0 no se ha actualizado ninguna fila
                // por lo que no se ha borrado y la Ciudad con esa id no existía
                if (ps.executeUpdate() == 0) {
                    System.err.println("No se ha encontrado ciudad con id " + id);
                    cambiado = false;
                } else {

                    cambiado = true;
                }

            } catch (SQLException e) {
                System.err.println("Error al cambiar el CountryCode del registro con ID " + id);
                cambiado = false;
            } finally {
                // Desconectamos la conexión pase lo que pase
                c.desconectar();
            }
        } else {
            cambiado = false;
            System.err.println("Error al conectar con la base de datos");
        }
        return cambiado;
    }

}
