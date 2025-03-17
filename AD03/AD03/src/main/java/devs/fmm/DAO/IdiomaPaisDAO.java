package devs.fmm.DAO;

import devs.fmm.Entities.IdiomaPais;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class IdiomaPaisDAO {
    // Creamos un atributo Connection para usarlo cualquier método del servicio
    private Conexion c = new Conexion("world", "super", "alumno");

    // Obtener todos los registros de la tabla countrylanguage
    public ArrayList<IdiomaPais> getAllIdiomasPaises() {
        ArrayList<IdiomaPais> idiomasPaises = new ArrayList<>();

        // Conectamos con la base de datos a través del método conectar de nuestro objeto Conexion
        if (c.conectar()) {
            try {
                // Usamos Statement para ejecutar una sentencia simple sin parámetros
                Statement ps = c.getConexion().createStatement();

                // Aunque queremos todos los campos de la tabla no utilizamos * por que si en algún momento se añade algun
                // la entidad asociada a esta tabla dejaria de ser compatible.
                String queryAll = "SELECT CountryCode, Language, isOfficial,Percentage FROM countrylanguage";

                // Ejecutamos la query y almacenamos el resultado en un ResultSet
                ResultSet rs = ps.executeQuery(queryAll);

                // Creamos una variable de tipo IdiomaPais sin inicializar
                IdiomaPais ip;

                // Variables para cada uno de los campos, que se corresponden con los atributos de IdiomaPais
                String codigoPais, idioma;
                boolean esOficial;
                double porcentaje;

                // En cada iteracion de ResultSet hay una fila, esta fila se corresponde a un Objeto IdiomaPais
                while (rs.next()) {
                    // Obtenemos los valores de cada una de las columnas de la fila
                    codigoPais = rs.getString(1);
                    idioma = rs.getString(2);
                    esOficial = rs.getString(3).equals("T") ? true : false;
                    porcentaje = Double.parseDouble(rs.getString(4));

                    // Creamos un objeto IdiomaPais con los valores obtenidos
                    ip = new IdiomaPais(codigoPais, idioma, esOficial, porcentaje);

                    // Añadimos el objeto IdiomaPais con los datos obtenidos de la fila
                    idiomasPaises.add(ip);
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

        return idiomasPaises;
    }

    // Funcion que inserta un nuevo registro en la tabla countrylanguage
    public boolean crearIdiomaPais(IdiomaPais ip) {

        boolean creado;

        //Conecto a la base de datos, ya definida en el atributo de la clase
        //Solo seguiré el proceso si consigo conectar
        if (c.conectar()) {

            try {
                // Usamos PreparedStatement para evitar inyecciones de código SQL malicioso
                PreparedStatement ps = c.getConexion().prepareStatement("INSERT INTO `countrylanguage` (`CountryCode`,`Language`, `IsOfficial`, `Percentage`) VALUES (?, ?, ?, ?)");
                ps.setString(1, ip.getCodigoPais());
                ps.setString(2, ip.getIdioma());
                ps.setString(3, ip.esOficial() ? "T" : "F");
                ps.setDouble(4, ip.getPorcentaje());

                // Este método ejecuta la query y devuelve el numero de filas que se han modificado.
                // Si es 0 el insert no ha surtido efecto
                if (ps.executeUpdate() == 0) {
                    System.err.println("No se ha podido crear el registro : \n" + ip);
                    creado = false;
                } else {
                    creado = true;
                }


            } catch (SQLException se) {
                System.err.println("Error al insertar el registro " + ip.getIdioma() + " en la tabla countrylanguage -> " + se.getMessage());
                creado = false;
            } finally {
                // Desconectamos la conexión pase lo que pase
                c.desconectar();
            }

        } else {
            creado = false;
            System.err.println("Error al conectar la base de datos");
        }


        return creado;
    }
}
