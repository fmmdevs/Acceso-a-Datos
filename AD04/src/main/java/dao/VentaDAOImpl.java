package dao;

import entities.Cliente;
import entities.Venta;
import entities.VentaCliente;
import entities.VentaClienteId;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class VentaDAOImpl implements VentaDAO {
    @Override
    public void create(Venta venta) {
        Session session = null;
        Transaction transaction = null;

        try {
            // Abrir la sesión
            session = HibernateUtil.getSessionFactory().openSession();

            // Iniciar la transacción
            transaction = session.beginTransaction();

            // Guardar el producto
            session.save(venta);

            // Confirmar la transacción
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
    public void create(Integer clienteId, Venta venta) {
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
                // Terminamos la ejecución
                return;
            }


            // Asociar la venta con el cliente
            VentaCliente ventaCliente = new VentaCliente();
            ventaCliente.setVenta(venta);
            ventaCliente.setCliente(cliente);


            // Clave compuesta
            VentaClienteId ventaClienteId = new VentaClienteId();
            ventaClienteId.setClienteId(clienteId);
            ventaClienteId.setVentaId(venta.getId());

            ventaCliente.setId(ventaClienteId);


            // Guardar la relación en la tabla venta_cliente
            session.save(ventaCliente);

            // Confirmar la transacción
            transaction.commit();

            // Añadimos la venta a la tabla de ventas
            create(venta);

            System.out.println("Venta realizada correctamente.");
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
    public List<Venta> readAll() {
        Session session = null;
        try {
            // Abrir la sesión
            session = HibernateUtil.getSessionFactory().openSession();

            // Crear una consulta HQL para obtener todas las ventas
            String hql = "FROM Venta";
            Query<Venta> query = session.createQuery(hql, Venta.class);

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
    public List<Venta> readAll(Integer clienteId) {
        Session session = null;
        try {
            // Abrir la sesión
            session = HibernateUtil.getSessionFactory().openSession();

            // Consulta que obtiene todos las ventas asociadas a un cliente
            String hql = "SELECT vc.venta FROM VentaCliente vc WHERE vc.cliente.id = :clienteId";

/*
            En SQL sería
            SELECT v.*
            FROM venta v
            JOIN venta_cliente vc ON v.id = vc.venta_id
            WHERE vc.cliente_id = :clienteId;*/

            // Creamos la query
            Query<Venta> query = session.createQuery(hql, Venta.class);
            // Asignamos el valor de la variable pasada por parametro clienteId al clienteId de la query
            query.setParameter("clienteId", clienteId);

            // Ejecutar la query y obtenemos la lista de ventas de este cliente
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
}

