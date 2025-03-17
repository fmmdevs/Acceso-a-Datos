package devs.fmm.dao;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import devs.fmm.modelo.Cliente;
import devs.fmm.modelo.Producto;
import devs.fmm.util.Db4oUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOImpl implements ProductoDAO {


    // Constructor vacío de la implementación del DAO, dónde nos encargamos de asegurar que los ficheros existan, y si no,
    // crearlos.
    public ProductoDAOImpl() {

        // Objeto File construido con la ruta del directorio. Representa la carpeta donde irán los archivos .db4o
        File directorio = new File(Db4oUtil.PATH);

        // Si no existe el directorio, lo creamos
        if (!directorio.exists()) directorio.mkdirs();

        // File construido con la ruta del archivo Producto.db4o.
        File fileProducto = new File(Db4oUtil.PRODUCTO_PATH);

        // Si el fichero no existe lo creamos
        if (!fileProducto.exists()) {
            try {
                fileProducto.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            // Si existe tenemos que actualizar la variable static ultimoId del modelo
            // Para ello obtengo el List con todos los registros
            List<Producto> productos = readAll();

            // Podría pasar que el archivo esté creado pero no haya registros. Por lo que compruebo que la lista no
            // esté vacia y asigno el id del último Producto de la List a la variable de clase (static) ultimoId;
            if (!productos.isEmpty())
                Producto.ultimoId = productos.getLast().getId();
        }

    }

    // Creamos un nuevo producto
    @Override
    public void create(Producto producto) {
        ObjectContainer db = null;
        try {
            // Nos conectamos a la bbdd Producto.db4o
            db = Db4oEmbedded.openFile(Db4oUtil.PRODUCTO_PATH);

            // Marcamos para guardar el objeto Producto en la bbdd
            db.store(producto);

            // Confirmamos la transacción
            db.commit();
        } catch (Exception e) {
            // Si ocurre una excepción hacemos rollback
            if (db != null) {
                db.rollback();
            }
            System.err.println("Error al crear producto: " + e.getMessage());
        } finally {
            // Cerramos la conexión con la bbdd
            if (db != null) {
                db.close();
            }
        }
    }

    // Eliminamos un producto de la bbdd por su id
    @Override
    public boolean delete(Integer id) {
        ObjectContainer db = null;
        try {
            // Nos conectamos a la bbdd
            db = Db4oEmbedded.openFile(Db4oUtil.PRODUCTO_PATH);
            // Obtenemos el ObjectSet con todos los productos cuya id sea el parámetro id (solo habrá uno)
            ObjectSet<Cliente> result = db.queryByExample(new Producto(id));

            // Si result.hasNext() es true, quiere decir que existe ese Producto
            if (result.hasNext()) {
                // Lo marcamos para eliminar
                db.delete(result.next());

                // Ejecutamos la transacción
                db.commit();

                // Devolvemos true
                return true;
            }


        } catch (Exception e) {

            System.err.println("Error eliminando producto " + e.getMessage());
            db.rollback();
        } finally {
            // (aunque hagamos return dentro del try, siempre se va a ejecutar el bloque finally)
            // Cerramos la conexión
            if (db != null) {
                db.close();
            }
        }
        return false;
    }

    // Obtenemos un producto por su id
    @Override
    public Producto read(Integer id) {
        ObjectContainer db = null;
        try {
            db = Db4oEmbedded.openFile(Db4oUtil.PRODUCTO_PATH);
            ObjectSet<Producto> result = db.queryByExample(new Producto(id));

            // Si el metodo hasNext es true devolvemos el Producto con next() si no devolvemos null
            return result.hasNext() ? result.next() : null;
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    @Override
    public boolean update(Producto productoActualizado) {
        ObjectContainer db = null;
        try {
            // Nos conectamos a la bbdd
            db = Db4oEmbedded.openFile(Db4oUtil.PRODUCTO_PATH);

            // Obtenemos el producto de la bbdd con la id del producto ya actualizado
            ObjectSet<Producto> result = db.queryByExample(new Producto(productoActualizado.getId()));

            // Si el producto existe en nuestra bbdd (siempre va a existir)
            if (result.hasNext()) {
                // Obtenemos el Producto
                Producto productoActual = result.next();

                // Actualizamos sus atributos con los del productoActualizado
                productoActual.setNombre(productoActualizado.getNombre());
                productoActual.setPrecioCompra(productoActualizado.getPrecioCompra());
                productoActual.setPrecioVenta(productoActualizado.getPrecioVenta());
                productoActual.setStock(productoActualizado.getStock());

                // Lo marcamos para almacenar
                db.store(productoActual);

                // Ejecutamos la transacción
                db.commit();
                return true;
            }
        } catch (Exception e) {
            System.err.println("Error modificando producto: " + e.getMessage());
            // Si ocurre alguna excepción no ejecutamos la transacción (no se modifica la bbdd)
            db.rollback();
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return false;
    }

    // Obtenemos la lista con todos los productos
    @Override
    public List<Producto> readAll() {
        ObjectContainer db = null;
        try {
            // Nos conectamos a la bbdd
            db = Db4oEmbedded.openFile(Db4oUtil.PRODUCTO_PATH);

            // Obtenemos todos los objetos de tipo Producto
            ObjectSet<Producto> result = db.query(Producto.class);

            // Devolvemos un ArrayList con los objetos de tipo Producto
            return new ArrayList<>(result);
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }
}
