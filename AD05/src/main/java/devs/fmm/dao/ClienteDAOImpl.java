package devs.fmm.dao;

import devs.fmm.modelo.Cliente;
import devs.fmm.modelo.VentaCliente;
import devs.fmm.util.Db4oUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.Db4oEmbedded;

public class ClienteDAOImpl implements ClienteDAO {


    // Constructor vacío de la implementación del DAO, dónde nos encargamos de asegurar que los ficheros existan, y si no,
    // crearlos.
    public ClienteDAOImpl() {

        // Objeto File construido con la ruta del directorio. Representa la carpeta donde irán los archivos .db4o
        File directorio = new File(Db4oUtil.PATH);

        // Si no existe el directorio, lo creamos
        if (!directorio.exists()) directorio.mkdirs();

        // File construido con la ruta del archivo Cliente.db4o.
        File fileCliente = new File(Db4oUtil.CLIENTE_PATH);

        // Si no existe lo creamos
        if (!fileCliente.exists()) {
            try {
                fileCliente.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            // Si existe tenemos que actualizar la variable static ultimoId del modelo
            // Para ello obtengo el List con todos los registros
            List<Cliente> clientes = readAll();

            // Podría pasar que el archivo esté creado pero no haya registros. Por lo que compruebo que la lista no
            // esté vacia y asigno el id del último Cliente de la List a la variable de clase (static) ultimoId;
            if (!clientes.isEmpty()) Cliente.ultimoId = clientes.getLast().getId();
        }

        // Archivo que representa a la tabla venta_cliente, donde se relacionan las id de ventas y clientes
        File ventaClienteFile = new File(Db4oUtil.VENTA_CLIENTE_PATH);
        if (!ventaClienteFile.exists()) {
            try {
                ventaClienteFile.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // Creamos un nuevo cliente
    @Override
    public void create(Cliente cliente) {
        ObjectContainer db = null;
        try {
            // Nos conectamos a la bbdd Cliente.db4o
            db = Db4oEmbedded.openFile(Db4oUtil.CLIENTE_PATH);

            // Marcamos para guardar el objeto Cliente en la bbdd
            db.store(cliente);

            // Simular un error intencionado
          /*  if (true) {
                throw new RuntimeException("Error simulado para probar rollback");
            }*/

            // Confirmamos la transacción, se almacena el objeto Cliente en la bbdd.
            db.commit();
        } catch (Exception e) {
            // Si ocurre una excepción hacemos rollback
            if (db != null) {
                db.rollback();
            }
            System.err.println("Error al crear cliente: " + e.getMessage());
        } finally {
            // Cerramos la conexión con la bbdd
            if (db != null) {
                db.close();
            }
        }
    }

    // Obtenemos una lista con todos los clientes
    @Override
    public List<Cliente> readAll() {
        ObjectContainer db = null;
        try {
            // Nos conectamos a la bbdd contenida en el fichero Cliente.db4o
            db = Db4oEmbedded.openFile(Db4oUtil.CLIENTE_PATH);

            // Obtenemos todos los objetos de tipo Cliente de la bbdd
            ObjectSet<Cliente> result = db.query(Cliente.class);

            // Devolvemos la Lista de objetos tipo Cliente con el resultado
            return new ArrayList<>(result);
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    // Obtenemos un Cliente por su id
    @Override
    public Cliente read(Integer clienteId) {
        ObjectContainer db = null;
        try {
            // Nos conectamos a la bbdd contenida en el fichero Cliente.db4o
            db = Db4oEmbedded.openFile(Db4oUtil.CLIENTE_PATH);

            // Obtenemos el ObjectSet con los clientes con la id clienteId (solo habrá uno)
            ObjectSet<Cliente> result = db.queryByExample(new Cliente(clienteId));

            // Si result tiene al menos un elemento
            if (result.hasNext()) {
                // Devolvemos ese Cliente
                return result.next();
            }

        } finally {
            if (db != null) {
                db.close();
            }
        }
        return null;
    }

    // Si el cliente tiene ventas eliminar registros en la tabla Venta_Cliente
    @Override
    public boolean delete(Integer clienteId) {

        ObjectContainer dbCliente = null;
        ObjectContainer dbVentaCliente = null;
        try {
            // Nos conectamos a las bbdd Cliente.db4o y Venta_Cliente.db4o
            dbCliente = Db4oEmbedded.openFile(Db4oUtil.CLIENTE_PATH);
            dbVentaCliente = Db4oEmbedded.openFile(Db4oUtil.VENTA_CLIENTE_PATH);

            // Obtenemos el ObjectSet con el cliente con el id clienteId
            ObjectSet<Cliente> resultCliente = dbCliente.queryByExample(new Cliente(clienteId));

            // Obtenemos los registros de la tabla Venta_Cliente cuyo atributo clienteId sea el parámetro clienteId
            ObjectSet<VentaCliente> resultVentaCliente = dbVentaCliente.queryByExample(new VentaCliente(clienteId));

            if (resultCliente.hasNext()) {
                // Si tenemos un cliente con esa id lo marcamos para eliminar
                Cliente clienteEliminar = resultCliente.next();
                dbCliente.delete(clienteEliminar);
                while (resultVentaCliente.hasNext()) {
                    // Marcamos para eliminar todos los registros del archivo que representa a VentaClientes cuyo
                    // clienteId sea el del cliente eliminado.
                    dbVentaCliente.delete(resultVentaCliente.next());
                }
                // Ejecutamos las transacciones
                dbCliente.commit();
                dbVentaCliente.commit();
                return true;
            }
        } catch (Exception e) {
            // Si ocurre una excepción no completamos la transacción en ninguna de las dos tablas.
            dbCliente.rollback();
            dbVentaCliente.rollback();
        } finally {
            if (dbCliente != null) {
                dbCliente.close();
            }
            if (dbVentaCliente != null) {
                dbVentaCliente.close();
            }
        }
        return false;
    }
}
