package dao;

import entities.Producto;

import java.util.List;

public interface ProductoDAO {
    // Crear producto
    void create(Producto producto);

    // Eliminar producto por su id
    void delete(Integer id);

    // Obtener producto por su id
    Producto read(Integer id);

    // Actualizar producto
    void update(Producto productoActualizado);

    // Obtener listado con todos los productos
    List<Producto> readAll();
}
