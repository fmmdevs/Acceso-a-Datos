package dao;

import entities.Venta;

import java.math.BigDecimal;
import java.util.List;

public interface VentaDAO {

    // Crear venta
    void create(Venta venta);

    // Crear venta asociada a un cliente
    void create(Integer clienteId, Venta venta);

    // Listado de todas las ventas
    List<Venta> readAll();

    // Listado con todas las ventas asociadas a un cliente
    List<Venta> readAll(Integer clienteId);


}
