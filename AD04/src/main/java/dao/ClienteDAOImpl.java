package dao;

import entities.Cliente;
import entities.Producto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.Collections;
import java.util.List;


public class ClienteDAOImpl implements ClienteDAO {
    @Override
    public void create(Cliente cliente) {
        Session session = null;
        Transaction transaction = null;

        try {
            // Abrimos la session
            session = HibernateUtil.getSessionFactory().openSession();

            // Iniciamos la transacción
            transaction = session.beginTransaction();

            // Marcamos para almacenar en la BBDD
            session.persist(cliente);

            // Confirmamos la transacción
            transaction.commit();
        } catch (Exception e) {
            // Rollback en caso de error
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (Exception rollbackException) {
                    rollbackException.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            // Cerrar la sesión
            if (session != null) {
                try {
                    session.close();
                } catch (Exception closeException) {
                    closeException.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Cliente> readAll() {
        Session session = null;
        try {
            // Abrir la sesión
            session = HibernateUtil.getSessionFactory().openSession();

            // Crear una consulta HQL para obtener todos los productos
            String hql = "FROM Cliente";
            Query<Cliente> query = session.createQuery(hql, Cliente.class);

            // Ejecutar la consulta y obtener la lista de productos
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            // Cerrar la sesión
            if (session != null) {
                try {
                    session.close();
                } catch (Exception closeException) {
                    closeException.printStackTrace();
                }
            }
        }
    }

    @Override
    public void delete(Integer clienteId) {
        Session session = null;
        Transaction transaction = null;

        try {
            // Abrir la sesión
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            // Obtener el cliente por ID
            Cliente cliente = session.get(Cliente.class, clienteId);

            if (cliente == null) {
                System.out.println("No se encontró ningún cliente con ID " + clienteId);
                return;
            }

            // Eliminar el cliente
            session.delete(cliente);

            // Confirmar la transacción
            transaction.commit();
            System.out.println("Cliente eliminado correctamente.");
        } catch (Exception e) {
            // Rollback en caso de error
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (Exception rollbackException) {
                    rollbackException.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            // Cerrar la sesión
            if (session != null) {
                try {
                    session.close();
                } catch (Exception closeException) {
                    closeException.printStackTrace();
                }
            }
        }
    }

    @Override
    public Cliente read(Integer clienteId) {
        Session session = null;
        try {
            // Abrir la sesión
            session = HibernateUtil.getSessionFactory().openSession();

            // Obtener el producto por su ID
            Cliente cliente = session.get(Cliente.class, clienteId);
/*
            if (cliente != null) {
                System.out.println("Cliente encontrado: " + cliente.getNombre());
            } else {
                System.out.println("No se ha encontrado cliente con ID " + clienteId);
            }*/

            return cliente;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            // Cerrar la sesión
            if (session != null) {
                try {
                    session.close();
                } catch (Exception closeException) {
                    closeException.printStackTrace();
                }
            }
        }
    }
}


