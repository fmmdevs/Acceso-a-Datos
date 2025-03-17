package devs.fmm.dao;

import devs.fmm.modelo.Cliente;

import java.util.List;

public interface ClienteDAO {

    // Crea un nuevo cliente
    void create(Cliente cliente);

    // Obtiene la lista con todos los clientes
    List<Cliente> readAll();

    // Obtiene un cliente por su id, null en caso de que no exista
    Cliente read(Integer clienteId);

    // Elimina un cliente por su id
    boolean delete(Integer clienteId);


}
