package devs.fmm.DAO;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenericoDAO<T> {


    // Atributos que configuran la conexión a la BBDD
    private final String BBDD = "world";
    private final String USUARIO = "super";
    private final String PASSWORD = "alumno";

    // Creamos un atributo Connection para usarlo en cualquier método del servicio
    private Conexion c;

    // Tablas permitidas
    private final List<String> TABLAS_PERMITIDAS = List.of("country", "city", "countrylanguage");

    // Class<T> representa los metadatos de una clase de tipo T
    private final Class<T> tipo;

    // Atributo que representa el nombre de la tabla de la que vamos a obtener los datos
    private final String TABLA;

    // Array de String con el nombre de los Campos
    private final ArrayList<String> nombreCampos;

    // Constructor con la entidad (la obtendremos con Entidad.class) y el nombre de la tabla
    public GenericoDAO(Class<T> tipo, String nombreTabla) {

        if (!TABLAS_PERMITIDAS.contains(nombreTabla)) {
            System.err.println("Error crítico: Tabla '%s' no permitida".formatted(nombreTabla));
            nombreCampos = null;
            TABLA = "";
            this.tipo = tipo;
        } else {
            c = new Conexion(BBDD, USUARIO, PASSWORD);
            this.tipo = tipo;
            this.TABLA = nombreTabla;
            nombreCampos = getNombreCampos();
        }

    }


    // get nombre campos (consulta)
    private ArrayList<String> getNombreCampos() {
        ArrayList<String> resultado = new ArrayList<>();
        try {
            if (c.conectar()) {

                PreparedStatement ps = c.getConexion().prepareStatement("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = ?");

                ps.setString(1, TABLA);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    //System.out.println(rs.getString(1));
                    resultado.add(rs.getString(1));
                }
            } else {
                System.err.println("Error en la conexión con la BBDD");
            }

            return resultado;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // get nombre campos (Evitar que el usuario introduzca un campo inexistente)
    public ArrayList<String> getCampos() {
        return nombreCampos;
    }

    // get tablas permitidas (comprobaciones para evitar inyecciones de SQL)

    public List<String> getTablasPermitidas() {
        return TABLAS_PERMITIDAS;
    }


    // get para cualquier tabla y para cualquier campo
    public ArrayList<T> getAnything(ArrayList<String> camposDeseados) {
        ArrayList<T> resultado = new ArrayList<>();
        if (c.conectar()) {
            try {

                StringBuilder query = new StringBuilder("SELECT");
                for (int i = 0; i < camposDeseados.size(); i++) {
                    // no se permite el uso de ? en los campos ni en el FROM, solo en los valores
                    query.append(" %s,".formatted(camposDeseados.get(i)));
                }
                //System.out.println(query);
                query.delete(query.length() - 1, query.length());
                //System.out.println(query);
                query.append(" FROM ").append(TABLA);
                System.out.println(query);

                PreparedStatement ps = c.getConexion().prepareStatement(query.toString());


                ResultSet rs = ps.executeQuery();


                while (rs.next()) {

                    // Creamos instancia generica
                    T instancia = tipo.getDeclaredConstructor().newInstance();

                    // Recorremos cada uno de los campos
                    for (String atributo : camposDeseados) {
                        // Obtenemos el valor del campo desde el ResultSet por nombre
                        Object valor = rs.getObject(atributo);

                        // En este punto he tenido que cambiar los atributos de las entidades para que tuvieran el mismo
                        // nombre que los campos de la tabla.
                        // Field permite obtener y modificar campos de un objeto en tiempo de ejecucion.
                        // Class.getDeclaredField("nombreDelAtributo") permite acceder a los atributos de Class
                        // tanto a los publicos como a los privados
                        Field field = tipo.getDeclaredField(atributo);

                        // Por si el campo fuese privado lo hacemos accesible
                        field.setAccessible(true);

                        // Asignamos el valor al atributo que hemos indicado antes de instancia (objeto generico)
                        field.set(instancia, valor);

                       /*
                       ejemplo menos abstracto
                        Pais pais = new Pais("España", 1);
                        Field field = Pais.class.getDeclaredField("nombre");
                        field.setAccessible(true);
                        field.set(pais, "México");*/
                    }
                    //System.out.println(instance);
                    resultado.add(instancia);
                }


            } catch (SQLException e) {
                System.err.println("Error en la consulta a la tabla " + TABLA + " : " + e.getMessage());
            } catch (InvocationTargetException e) {
                System.err.println(e);
            } catch (InstantiationException e) {
                System.err.println(e);
            } catch (IllegalAccessException e) {
                System.err.println(e);
            } catch (NoSuchMethodException e) {
                System.err.println(e);
            } catch (NoSuchFieldException e) {
                System.err.println(e);
            } finally {
                c.desconectar();
            }


        } else {
            System.err.println("Error en la conexión con la BBDD");
        }

        return resultado;

    }


}
