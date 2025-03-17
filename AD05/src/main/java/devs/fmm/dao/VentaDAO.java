package devs.fmm.dao;

import devs.fmm.modelo.Venta;

import java.util.List;

public interface VentaDAO {
    // Nueva venta sin Cliente asociado
    void create(Venta venta);

    // Nueva venta con Cliente
    void create(Integer clienteId, Venta venta);

    List<Venta> readAll();

    List<Venta> readAll(Integer clienteId);
}
