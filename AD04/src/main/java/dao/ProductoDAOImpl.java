package dao;

import entities.Producto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.Collections;
import java.util.List;

public class ProductoDAOImpl implements ProductoDAO {

    // Crear un nuevo producto
    public void create(Producto producto) {
        Session session = null;
        Transaction transaction = null;

        try {
            // Abrir la sesión
            session = HibernateUtil.getSessionFactory().openSession();

            // Iniciar la transacción
            transaction = session.beginTransaction();

            // Guardar el producto
            session.save(producto);

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
    public void delete(Integer id) {
        Session session = null;
        Transaction transaction = null;

        try {
            // Abrir la sesión
            session = HibernateUtil.getSessionFactory().openSession();

            // Iniciar la transacción
            transaction = session.beginTransaction();

            // Obtenemos el producto
            Producto producto = session.get(Producto.class, id);

            // Si el producto existe lo eliminamos
            if (producto != null) {
                session.delete(producto);
                System.out.println("Producto con id %d eliminado correctamente".formatted(id));
            } else {
                System.out.println("No se ha encontrado producto con id " + id);
            }

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
    public Producto read(Integer id) {
        Session session = null;
        try {
            // Abrir la sesión
            session = HibernateUtil.getSessionFactory().openSession();

            // Obtener el producto por su ID
            Producto producto = session.get(Producto.class, id);

            if (producto != null) {
                System.out.println("Producto encontrado: " + producto.getNombre());
            } else {
                System.out.println("No se ha encontrado producto con ID " + id);
            }

            return producto;
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
    public void update(Producto producto) {
        Session session = null;
        Transaction transaction = null;

        try {
            // Abrir la sesión
            session = HibernateUtil.getSessionFactory().openSession();

            // Iniciar la transacción
            transaction = session.beginTransaction();


            // Actualizamos el producto.
            // Hibernate utiliza el id del producto que le pasamos por parámetro para encontrar el registro en la bbdd
            session.update(producto);

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
    public List<Producto> readAll() {

        Session session = null;
        try {
            // Abrir la sesión
            session = HibernateUtil.getSessionFactory().openSession();

            // Crear una consulta HQL para obtener todos los productos
            String hql = "FROM Producto";
            Query<Producto> query = session.createQuery(hql, Producto.class);

            // Ejecutar la consulta y obtener la lista de productos
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList(); // Devolver una lista vacía en caso de error
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
