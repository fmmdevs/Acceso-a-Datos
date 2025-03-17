package devs.fmm.dao;

import devs.fmm.modelo.Producto;

import java.util.List;

public interface ProductoDAO {
    void create(Producto producto);

    boolean delete(Integer id);

    Producto read(Integer id);

    boolean update(Producto productoActualizado);

    List<Producto> readAll();
}
