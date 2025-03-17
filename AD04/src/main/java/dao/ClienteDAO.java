package dao;

import entities.Cliente;

import java.util.List;

public interface ClienteDAO {
    // Crear nuevo cliente
    void create(Cliente cliente);

    // Listar todos los clientes
    List<Cliente> readAll();

    // Eliminar cliente por su id
    void delete(Integer clienteId);

    Cliente read(Integer clienteId);
}
