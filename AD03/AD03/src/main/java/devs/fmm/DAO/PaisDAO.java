package devs.fmm.DAO;

import devs.fmm.Entities.Pais;

import java.sql.*;
import java.util.ArrayList;

public class PaisDAO {
    private Conexion c = new Conexion("world", "super", "alumno");

    public ArrayList<Pais> getAllPaises() {
        ArrayList<Pais> paises = new ArrayList<>();

        // Conectamos con la base de datos a través del método conectar de nuestro objeto Conexion
        if (c.conectar()) {

            try {

                // Usamos Statement para ejecutar una sentencia simple sin parámetros
                Statement s = c.getConexion().createStatement();
                String queryAll = "SELECT Code, Name, Continent, Region, SurfaceArea, IndepYear, Population, LifeExpectancy, GNP, GNPOld, LocalName, GovernmentForm, HeadOfState, Capital, Code2 FROM country";

                ResultSet rs = s.executeQuery(queryAll);


                String codigo, nombre, continente, region, nombreLocal, formaGobierno, jefeEstado, codigo2;
                double superficie, gnp;
                Double gnpOld, expectativaVida;
                Integer anioIndependecia;
                int capital;
                long poblacion;

                Pais p;


                // En cada iteracion de ResultSet hay una fila
                while (rs.next()) {
                    codigo = rs.getString(1);
                    nombre = rs.getString(2);
                    continente = rs.getString(3);
                    region = rs.getString(4);
                    superficie = Double.parseDouble(rs.getString(5));

                    // Comprobar si en NULL
                    anioIndependecia = rs.getObject(6) == null ? null : Integer.parseInt(rs.getString(6));

                    poblacion = Long.parseLong(rs.getString(7));

                    // Comprobar si en NULL
                    expectativaVida = rs.getObject(8) == null ? null : Double.parseDouble(rs.getString(8));

                    gnp = Double.parseDouble(rs.getString(9));
                    // Comprobar si es NULL
                    gnpOld = rs.getObject(10) == null ? null : Double.parseDouble(rs.getString(10));

                    nombreLocal = rs.getString(11);
                    formaGobierno = rs.getString(12);
                    jefeEstado = rs.getString(13);
                    capital = rs.getInt(14);
                    codigo2 = rs.getString(15);

                    p = new Pais(codigo, nombre, continente, region, superficie, anioIndependecia, poblacion, expectativaVida, gnp, gnpOld, nombreLocal, formaGobierno, jefeEstado, capital, codigo2);

                    paises.add(p);
                }
            } catch (SQLException e) {
                System.err.println("Error en la consulta " + e.getMessage());
            }
        } else {
            System.err.println("Error en la conexión con la BBDD");
        }


        return paises;
    }


    public ArrayList<Pais> getPaisesByFormaGobierno(String formaGobierno) {
        ArrayList<Pais> paises = new ArrayList<>();

        // Conectamos con la base de datos a través del método conectar de nuestro objeto Conexion
        if (c.conectar()) {

            try {

                // PreparedStatement para ejecutar una sentencia con parámetros
                PreparedStatement ps = c.getConexion().prepareStatement("SELECT Code, Name, Continent, Region, SurfaceArea, IndepYear, Population, " +
                        "LifeExpectancy, GNP, GNPOld, LocalName, GovernmentForm, HeadOfState, Capital, Code2 " +
                        "FROM country " +
                        "WHERE GovernmentForm = ?");
                ps.setString(1, formaGobierno);


                ResultSet rs = ps.executeQuery();


                String codigo, nombre, continente, region, nombreLocal, jefeEstado, codigo2;
                double superficie, gnp;
                Double gnpOld, expectativaVida;
                Integer anioIndependecia;
                int capital;
                long poblacion;

                Pais p;


                // En cada iteracion de ResultSet hay una fila
                while (rs.next()) {
                    codigo = rs.getString(1);
                    nombre = rs.getString(2);
                    continente = rs.getString(3);
                    region = rs.getString(4);
                    superficie = Double.parseDouble(rs.getString(5));

                    // Comprobar si en NULL
                    anioIndependecia = rs.getObject(6) == null ? null : Integer.parseInt(rs.getString(6));

                    poblacion = Long.parseLong(rs.getString(7));

                    // Comprobar si en NULL
                    expectativaVida = rs.getObject(8) == null ? null : Double.parseDouble(rs.getString(8));

                    gnp = Double.parseDouble(rs.getString(9));
                    // Comprobar si es NULL
                    gnpOld = rs.getObject(10) == null ? null : Double.parseDouble(rs.getString(10));

                    nombreLocal = rs.getString(11);
                    formaGobierno = rs.getString(12);
                    jefeEstado = rs.getString(13);
                    capital = rs.getInt(14);
                    codigo2 = rs.getString(15);

                    p = new Pais(codigo, nombre, continente, region, superficie, anioIndependecia, poblacion, expectativaVida, gnp, gnpOld, nombreLocal, formaGobierno, jefeEstado, capital, codigo2);

                    paises.add(p);
                }
            } catch (SQLException e) {
                System.err.println("Error en la consulta " + e.getMessage());
            }
        } else {
            System.err.println("Error en la conexión con la BBDD");
        }


        return paises;
    }

    public ArrayList<Pais> getPaisesBy(String campo, String valor) {
        ArrayList<Pais> paises = new ArrayList<>();

        // Conectamos con la base de datos a través del método conectar de nuestro objeto Conexion
        if (c.conectar()) {

            try {

                // PreparedStatement para ejecutar una sentencia con parámetros
                PreparedStatement ps = c.getConexion().prepareStatement("SELECT Code, Name, Continent, Region, SurfaceArea, IndepYear, Population, " +
                        "LifeExpectancy, GNP, GNPOld, LocalName, GovernmentForm, HeadOfState, Capital, Code2 " +
                        "FROM country " +
                        "WHERE ? = ?");


                ps.setString(1, campo);
                ps.setString(2, valor);

                ResultSet rs = ps.executeQuery();


                String codigo, nombre, continente, region, nombreLocal, formaGobierno, jefeEstado, codigo2;
                double superficie, gnp;
                Double gnpOld, expectativaVida;
                Integer anioIndependecia;
                int capital;
                long poblacion;

                Pais p;


                // En cada iteracion de ResultSet hay una fila
                while (rs.next()) {
                    codigo = rs.getString(1);
                    nombre = rs.getString(2);
                    continente = rs.getString(3);
                    region = rs.getString(4);
                    superficie = Double.parseDouble(rs.getString(5));

                    // Comprobar si en NULL
                    anioIndependecia = rs.getObject(6) == null ? null : Integer.parseInt(rs.getString(6));

                    poblacion = Long.parseLong(rs.getString(7));

                    // Comprobar si en NULL
                    expectativaVida = rs.getObject(8) == null ? null : Double.parseDouble(rs.getString(8));

                    gnp = Double.parseDouble(rs.getString(9));
                    // Comprobar si es NULL
                    gnpOld = rs.getObject(10) == null ? null : Double.parseDouble(rs.getString(10));

                    nombreLocal = rs.getString(11);
                    formaGobierno = rs.getString(12);
                    jefeEstado = rs.getString(13);
                    capital = rs.getInt(14);
                    codigo2 = rs.getString(15);

                    p = new Pais(codigo, nombre, continente, region, superficie, anioIndependecia, poblacion, expectativaVida, gnp, gnpOld, nombreLocal, formaGobierno, jefeEstado, capital, codigo2);

                    paises.add(p);
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


        return paises;
    }


    // Codigo de 3 letras seguido del pais entre parententesis. ESP(Spain)
    public ArrayList<String> getCodigosNombrePais() {
        ArrayList<String> codigosPais = new ArrayList<>();

        if (c.conectar()) {

            try {

                // PreparedStatement para ejecutar una sentencia con parámetros
                PreparedStatement ps = c.getConexion().prepareStatement("SELECT DISTINCT Code, Name FROM country ");

                ResultSet rs = ps.executeQuery();

                // En cada iteracion de ResultSet hay una fila
                while (rs.next()) {
                    codigosPais.add(rs.getString(1) + "(" + rs.getString(2) + ")");
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


        return codigosPais;

    }

    public ArrayList<String> getCodigosPais() {
        ArrayList<String> codigosPais = new ArrayList<>();

        if (c.conectar()) {

            try {

                // PreparedStatement para ejecutar una sentencia con parámetros
                PreparedStatement ps = c.getConexion().prepareStatement("SELECT DISTINCT Code FROM country ");

                ResultSet rs = ps.executeQuery();

                // En cada iteracion de ResultSet hay una fila
                while (rs.next()) {
                    codigosPais.add(rs.getString(1));
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


        return codigosPais;

    }

    // Crear Pais
    public boolean crearPais(Pais pais) {

        boolean correcto;

        //Conecto a la base de datos, ya definida en el atributo de la clase
        //Solo seguiré el proceso si consigo conectar
        if (c.conectar()) {

            try {
                PreparedStatement ps = c.getConexion().prepareStatement(
                        "INSERT INTO `country` ( Code, Name, Continent, Region, SurfaceArea, IndepYear, Population, " +
                                "LifeExpectancy, GNP, GNPOld, LocalName, GovernmentForm, HeadOfState, Capital, Code2) " +
                                "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                ps.setString(1, pais.getCodigo());
                ps.setString(2, pais.getNombre());
                ps.setString(3, pais.getContinente());
                ps.setString(4, pais.getRegion());
                ps.setDouble(5, pais.getSuperficie());
                ps.setInt(6, pais.getAnioIndependencia());
                ps.setLong(7, pais.getPoblacion());
                ps.setDouble(8, pais.getExpectativaVida());
                ps.setDouble(9, pais.getGnp());

                // Segun si es null
                if (pais.getGnpOld() == null) {
                    ps.setNull(10, Types.DECIMAL);
                } else {
                    ps.setDouble(10, pais.getGnpOld());
                }

                ps.setString(11, pais.getNombreLocal());
                ps.setString(12, pais.getFormaGobierno());
                ps.setString(13, pais.getJefeEstado());
                ps.setInt(14, pais.getCapital());
                ps.setString(15, pais.getCodigo2());
                ps.execute();

                correcto = true;
            } catch (SQLException se) {
                System.err.println("Error al insertar el registro " + pais.getNombre() + " en la tabla country -> " + se.getMessage());
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


}
