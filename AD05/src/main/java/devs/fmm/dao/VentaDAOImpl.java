package devs.fmm.dao;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import devs.fmm.modelo.Venta;
import devs.fmm.modelo.VentaCliente;
import devs.fmm.util.Db4oUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VentaDAOImpl implements VentaDAO {

    // Constructor vacío de la implementación del DAO, dónde nos encargamos de asegurar que los ficheros existan, y si no,
    // crearlos.
    public VentaDAOImpl() {

        // Objeto File construido con la ruta del directorio. Representa la carpeta donde irán los archivos .db4o
        File directorio = new File(Db4oUtil.PATH);

        // Si no existe el directorio, lo creamos
        if (!directorio.exists()) directorio.mkdirs();

        // Archivo que representa al fichero con la bbdd Venta.db4o
        File file = new File(Db4oUtil.VENTA_PATH);

        // Si el archivo no existe, lo creamos
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {

            // Si el fichero existe actualizo la variable de clase ultimoId de Venta
            List<Venta> ventas = readAll();

            // Podría pasar que el archivo esté creado pero no haya registros
            if (!ventas.isEmpty())
                Venta.ultimoId = ventas.getLast().getId();
        }

    }

    // Creamos un nueva Venta
    @Override
    public void create(Venta venta) {
        ObjectContainer db = null;
        try {
            db = Db4oEmbedded.openFile(Db4oUtil.VENTA_PATH);
            db.store(venta);
            db.commit();

        } catch (Exception e) {
            // Si ocurre una excepción hacemos rollback
            if (db != null) {
                db.rollback();
            }
            System.err.println("Error al crear venta: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    // Creamos una venta asociada a un cliente
    @Override
    public void create(Integer clienteId, Venta venta) {

        // Creamos nuevo registro en la tabla ventas, aprovechando el método anterior
        create(venta);

        // Creamos registro en la tabla Venta_Cliente, con el id del cliente y de la venta.
        ObjectContainer db = null;
        try {

            // Abrimos la bbdd Venta_Cliente.db4o
            db = Db4oEmbedded.openFile(Db4oUtil.VENTA_CLIENTE_PATH);

            // Creamos un objeto VentaCliente con el id de la venta y el id del cliente
            VentaCliente ventaCliente = new VentaCliente(venta.getId(), clienteId);

            // Lo marcamos para almacenar
            db.store(ventaCliente);

            // Ejecutamos la transacción
            db.commit();
        } catch (Exception e) {
            // Si ocurre una excepción hacemos rollback
            if (db != null) {
                db.rollback();
            }
            System.err.println("Error al crear venta asociada a cliente: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    // Listamos las ventas
    @Override
    public List<Venta> readAll() {
        ObjectContainer db = null;
        try {
            db = Db4oEmbedded.openFile(Db4oUtil.VENTA_PATH);
            ObjectSet<Venta> result = db.query(Venta.class);
            return new ArrayList<>(result);
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    // Listado de todas las ventas de un cliente
    // 1. Obtener ventas asociadas a un cliente
    // 1.1 obtener todos los id_ventas de un id_cliente en la tabla VentaCliente
    // 2. Encontrarlas y añadirlas a una lista

    @Override
    public List<Venta> readAll(Integer clienteId) {
        ObjectContainer dbVentaCliente = null;
        ObjectContainer dbVenta = null;
        try {
            // Nos conectamos a las bbdd
            dbVentaCliente = Db4oEmbedded.openFile(Db4oUtil.VENTA_CLIENTE_PATH);
            dbVenta = Db4oEmbedded.openFile(Db4oUtil.VENTA_PATH);

            // Obtenemos los registros de la bbdd VentaCliente cuyo id_cliente sea el que buscamos
            ObjectSet<VentaCliente> result = dbVentaCliente.queryByExample(new VentaCliente(clienteId));

            // Creamos un List para almacenar las ventas asociadas a un cliente
            List<Venta> listadoVentas = new ArrayList<>();

            // Recorremos objetos que representan la relacion VentaCliente
            while (result.hasNext()) {

                VentaCliente ventaCliente = result.next();
                // Por cada uno de los objetos VentaCliente(que son de un mismo cliente con id clienteId)
                // Obtenemos las distintas ventas mediante el atributo ventaId de los objetos VentaCliente
                // (En realidad solo habrá una Venta por cada registro de VentaCliente)
                ObjectSet<Venta> resultVentas = dbVenta.queryByExample(new Venta(ventaCliente.getVentaId()));

                if (resultVentas.hasNext()) {
                    // Añadimos cada una de las ventas asociadas a nuestro cliente al listado
                    listadoVentas.add(resultVentas.next());
                }
            }

            // Devolvemos el listado
            return listadoVentas;
        } finally {
            // Importante cerrar ambos ficheros
            if (dbVentaCliente != null) {
                dbVentaCliente.close();
            }
            if (dbVenta != null) {
                dbVenta.close();
            }
        }
    }
}
