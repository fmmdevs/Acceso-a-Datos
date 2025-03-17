package devs.fmm;

import devs.fmm.dao.*;
import devs.fmm.modelo.Cliente;
import devs.fmm.modelo.Producto;
import devs.fmm.modelo.Venta;


import java.util.*;


public class Main {

    public static void main(String[] args) {

        // Interfaces DAO que instanciamos con sus implementaciones concretas, en este caso de db4o
        ClienteDAO clienteDAO = new ClienteDAOImpl();
        ProductoDAO productoDAO = new ProductoDAOImpl();
        VentaDAO ventaDAO = new VentaDAOImpl();

        // Mostramos el menú
        mostrarMenu(productoDAO, clienteDAO, ventaDAO);

    }

    private static void mostrarMenu(ProductoDAO productoDAO, ClienteDAO clienteDAO, VentaDAO ventaDAO) {
        // Scanner para la entrada de datos por consola
        Scanner sc = new Scanner(System.in);

        // Variable donde vamos a almacenar la opción del menú principal elegida por el usuario
        String opcion;

        // Usamos un bucle do-while para mostrar el menú hasta que se introduzca "12"
        do {
            // Mostramos el menú principal
            System.out.println("""
                    Introduce una opción (1-12)
                    1. Añadir producto
                    2. Eliminar producto.
                    3. Modificar producto
                    4. Listar productos
                    5. Añadir cliente
                    6. (extra - opcional) Eliminar cliente. Se podrán eliminar clientes con ventas, pero deberás eliminar los registros de la tabla Venta_Cliente de ese cliente
                    7. Listar clientes
                    8. Realizar una venta
                    9. (extra - opcional) Realizar una venta a un cliente.
                    10. (extra - extra) Listar ventas
                    11. (extra - extra) Listar relación clientes - ventas
                    12. Salir
                    """);

            // Obtenemos la opción elegida por el usuario
            opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    crearProducto(sc, productoDAO);
                    break;

                case "2":
                    eliminarProducto(productoDAO, sc);
                    break;

                case "3":
                    modificarProducto(sc, productoDAO);
                    break;

                case "4":
                    listarProductos(productoDAO);
                    break;

                case "5":
                    crearCliente(sc, clienteDAO);
                    break;

                case "6":
                    eliminarCliente(clienteDAO, sc);
                    break;

                case "7":
                    listarClientes(clienteDAO);
                    break;

                case "8":
                    crearVenta(sc, ventaDAO);
                    break;

                case "9":
                    crearVentaCliente(sc, ventaDAO);
                    break;

                case "10":
                    listarVentas(ventaDAO);
                    break;

                case "11":
                    mostrarClienteVentas(sc, clienteDAO, ventaDAO);
                    break;

                case "12":
                    // 12. Salir:
                    // Cerramos el Scanner.
                    sc.close();
                    break;

                default:
                    // Si el usuario introduce un valor que no sea un número del 1 al 12 mostramos mensaje
                    System.out.println("Opción no válida");

            }
        }
        while (!opcion.equals("12"));
    }

    // 1. Añadir producto:
    private static void crearProducto(Scanner sc, ProductoDAO productoDAO) {
        // Instanciamos un Producto con el constructor vacío.
        // El único atributo que inicializa es el id.
        Producto producto = new Producto();

        // Lo envuelvo en un try-catch por si introducimos un tipo de dato incorrecto
        try {
            // Vamos pidiendo los datos al usuario por consola y "seteandolos" a nuestro objeto tipo Producto
            System.out.println("Introduce nombre de producto");
            producto.setNombre(sc.nextLine());

            System.out.println("Introduce precio de compra");
            producto.setPrecioCompra(sc.nextDouble());
            // Cada vez que usamos cualquier método de Scanner que no sea nextLine(), debemos consumir la línea
            // utilizando nextLine().
            sc.nextLine();

            System.out.println("Introduce precio de venta");
            producto.setPrecioVenta(sc.nextDouble());
            // Consumir linea
            sc.nextLine();

            System.out.println("Introduce el stock");
            producto.setStock(sc.nextInt());
            // Consumir linea
            sc.nextLine();

            // Una vez asignados todos los atributos del objeto Producto llamamos al método create del DAO
            productoDAO.create(producto);
        } catch (InputMismatchException e) {
            // Si la excepción ocurre en el Scanner al introducir un tipo incorrecto,
            // se queda sin consumir la línea
            sc.nextLine();
            System.err.println("Error en la entrada de datos");
            // Como finalmente no hemos creado un nuevo Producto restamos uno a la variable static
            // que lleva la cuenta de cuál ha sido el último id (se incrementa al usar el constructor vacío)
            Producto.ultimoId--;

        } catch (Exception e) {
            // Resto de excepciones
            System.out.println(e.getMessage());
            Producto.ultimoId--;
        }
    }

    // 2. Eliminar producto:
    private static void eliminarProducto(ProductoDAO productoDAO, Scanner sc) {
        try {
            // Solicitamos el id de Producto
            System.out.println("Introduce el id del producto a eliminar");
            // Llamamos al método del DAO que elimina a un Producto por su id
            productoDAO.delete(sc.nextInt());
            sc.nextLine();
        } catch (InputMismatchException e) {
            sc.nextLine();
            System.err.println("El id debe ser un número entero");
            //System.err.println(e);
        }
    }

    // 3. Modificar producto:
    private static void modificarProducto(Scanner sc, ProductoDAO productoDAO) {
        try {
            // Solicitamos el id de Producto al usuario
            System.out.println("Introduce el id del producto a actualizar");
            int productoModificandoId = sc.nextInt();
            sc.nextLine();

            // Obtenemos el Producto con el DAO mediante su id
            Producto productoModificando = productoDAO.read(productoModificandoId);

            // Submenú de modificación
            String opcionActualizarProducto;

            // Si el Producto existe mostraremos el menú
            if (productoModificando != null) {
                do {
                    // Mostramos el estado actual del Producto que estamos modificando
                    System.out.println(productoModificando);

                    System.out.println("""
                            1. Modificar nombre de producto
                            2. Modificar precio de compra
                            3. Modificar precio de venta
                            4. Modificar stock
                            5. Guardar modificaciones y salir
                            6. Cancelar modificaciones y salir
                            """);
                    opcionActualizarProducto = sc.nextLine();

                    switch (opcionActualizarProducto) {
                        // 1. Modificar nombre de producto:
                        case "1":
                            // Vamos a mostrar el valor actual de lo que estamos modificando
                            System.out.println("Nombre de producto actual: " + productoModificando.getNombre());

                            // Solicitamos el nuevo valor
                            System.out.println("Introduzca el nuevo nombre");

                            // Lo almacenamos en nuestro Producto
                            productoModificando.setNombre(sc.nextLine());
                            break;
                        case "2":
                            // Idem
                            System.out.println("Precio de compra actual :" + productoModificando.getPrecioCompra());
                            System.out.println("Introduzca el nuevo precio de compra");
                            productoModificando.setPrecioCompra(sc.nextDouble());
                            sc.nextLine();
                            break;
                        case "3":
                            System.out.println("Precio de venta actual :" + productoModificando.getPrecioVenta());
                            System.out.println("Introduzca el nuevo precio de venta");
                            productoModificando.setPrecioVenta(sc.nextDouble());
                            sc.nextLine();
                            break;
                        case "4":
                            System.out.println("Stock actual : " + productoModificando.getStock());
                            System.out.println("Introduzca el nuevo stock");
                            productoModificando.setStock(sc.nextInt());
                            sc.nextLine();
                            break;
                        case "5":
                            // Solo cuando el usuario introduzca la opción "5" actualizaremos el producto en
                            // la bbdd, en este caso en el archivo Producto.db4o
                            productoDAO.update(productoModificando);
                            break;
                        case "6":
                            // Si el usuario quiere salir sin modificar el Producto, usará la opción "6"
                            System.out.println("Saliendo de submenú de modificación de producto sin guardar cambios");
                            break;
                        default:
                            System.out.println("Opción invalida");
                    }

                    // Si la opción introducida por el usuario ha sido 5 o 6 salimos del bucle del submenú de modificación
                } while (!opcionActualizarProducto.equals("5") && !opcionActualizarProducto.equals("6"));

            } else {
                // Si no existe el Producto mostramos un mensaje
                System.out.println("No existe el Producto con id " + productoModificandoId);
            }

        } catch (InputMismatchException e) {
            sc.nextLine();
            System.err.println("Error en la entrada de datos");

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    // 4. Listar productos:
    private static void listarProductos(ProductoDAO productoDAO) {
        // Obtenemos un List con todos los productos usando el método readAll del DAO
        List<Producto> listaProductos = productoDAO.readAll();
        for (Producto p : listaProductos) {
            // Recorremos el List y mostramos por consola cada Producto
            System.out.println(p);
        }
    }

    // 5. Añadir cliente:
    private static void crearCliente(Scanner sc, ClienteDAO clienteDAO) {
        // Instanciamos un objeto Cliente con el constructor vacío, que se encargará de asignar el id correspondiente
        Cliente cliente = new Cliente();
        try {
            // Solicitamos los datos al usuario y se los asignamos a nuestro objeto Cliente
            System.out.println("Introduce nombre de cliente");
            cliente.setNombre(sc.nextLine());

            System.out.println("Introduce el email");
            cliente.setEmail(sc.nextLine());

            System.out.println("Introduce el teléfono");
            cliente.setTelefono(sc.nextLine());

            System.out.println("Introduce la dirección");
            cliente.setDireccion(sc.nextLine());

            // Usamos el método create del DAO para insertar un nuevo cliente, en este caso en el fichero .db4o
            clienteDAO.create(cliente);
        } catch (Exception e) {
            // Si ocurre una excepción una vez usado el constructor vacío que actualiza la variable
            // static ultimoId, deshacemos esa actualización
            Cliente.ultimoId--;
            System.out.println(e.getMessage());
        }
    }

    // 6. Eliminar cliente (si tiene ventas, eliminamos todas las ventas asociadas en Venta_Cliente y Ventas):
    private static void eliminarCliente(ClienteDAO clienteDAO, Scanner sc) {
        // Solicitamos el id del cliente a eliminar
        System.out.println("Introduzca el id del cliente");
        try {
            // Si existe lo eliminamos junto a todas las ventas asociadas en Venta_Cliente.db4o
            clienteDAO.delete(sc.nextInt());
            sc.nextLine();
        } catch (InputMismatchException e) {
            sc.nextLine();
            System.err.println("El id debe ser un número entero");
        } catch (Exception e) {
            sc.nextLine();
            System.err.println(e.getMessage());
        }
    }

    // 7. Listar clientes:
    private static void listarClientes(ClienteDAO clienteDAO) {
        List<Cliente> listaClientes = clienteDAO.readAll();
        for (Cliente c : listaClientes) {
            System.out.println(c);
        }
    }

    // 8. Realizar una venta (se incrementa la variable static ultimoId):
    private static void crearVenta(Scanner sc, VentaDAO ventaDAO) {
        Venta venta = new Venta();
        try {
            // Asignamos la fecha y hora actuales al atributo fecha de tipo Date del objeto de tipo Venta
            venta.setFecha(new Date());
            // Solicitamos el importe total al usuario y lo asignamos al atributo total del objeto de tipo Venta
            System.out.println("Introduzca el importe total");
            venta.setTotal(sc.nextDouble());
            sc.nextLine();

            // Insertamos el nuevo registro en el fichero Venta.db4o mediante el metodo create del DAO
            ventaDAO.create(venta);

        } catch (InputMismatchException e) {
            sc.nextLine();
            System.err.println("El importe debe ser un número");
            Venta.ultimoId--;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Venta.ultimoId--;
        }
    }

    // 9. Realizar una venta a un cliente:
    private static void crearVentaCliente(Scanner sc, VentaDAO ventaDAO) {
        try {
            // Solicitamos y obtenemos el id del cliente
            System.out.println("Introduzca el id del cliente");
            Integer idCliente = sc.nextInt();
            sc.nextLine();

            // Solicitamos y obtenemos el importe total
            System.out.println("Introduzca el importe total");
            double total = sc.nextDouble();
            sc.nextLine();

            // Creamos un objeto de tipo Venta con el constructor vacío
            Venta ventaAsociada = new Venta();
            // Asignamos el objeto Date con la fecha y hora actual al atributo fecha con el metodo setFecha
            ventaAsociada.setFecha(new Date());
            // Asignamos el total al atributo correspondiente del objeto Venta
            ventaAsociada.setTotal(total);

            // Creamos los registros en el ficheor Venta.db4o y en Venta_Cliente.db4o
            ventaDAO.create(idCliente, ventaAsociada);

        } catch (NoSuchElementException | IllegalStateException e) {
            // Agrupo las excepciones que se pueden dar al usar Scanner, ya que en estas no hará falta
            // decrementar el valor de la variable ultimoId del objeto Venta
            System.err.println("Error en la entrada de datos");


        } catch (Exception e) {
            // Decremento ultimoId de la clase Venta
            Venta.ultimoId--;
            System.err.println(e.getMessage());
        }
    }


    // 10. Listar ventas:
    private static void listarVentas(VentaDAO ventaDAO) {
        // Obtenemos el List con el método del DAO readAll()
        List<Venta> listaVentas = ventaDAO.readAll();
        for (Venta v : listaVentas) {
            // Mostramos cada una de las Ventas
            System.out.println(v);
        }
    }

    // 11. Relación Cliente - Ventas
    private static void mostrarClienteVentas(Scanner sc, ClienteDAO clienteDAO, VentaDAO ventaDAO) {
        try {
            // Solicitamos y obtenemos el id del cliente
            System.out.println("Introduzca el ID del cliente");
            Integer idClient = sc.nextInt();
            sc.nextLine();

            // Comprobamos si existe el cliente
            Cliente clienteVenta = clienteDAO.read(idClient);
            if (clienteVenta != null) {
                // Si existe lo mostramos
                System.out.println("---------------------------------------------------------------------------------------------------");
                System.out.println(clienteVenta);

                // Obtenemos las ventas asociadas al cliente
                List<Venta> listadoVentas = ventaDAO.readAll(idClient);
                // Si hay al menos una la mostramos
                if (listadoVentas.size() > 0) {
                    for (Venta v : listadoVentas) {
                        System.out.println("\t" + v);
                    }
                } else {
                    // Si el cliente no tiene ninguna venta asociada mostramos un mensaje
                    System.out.println("\t El cliente no tiene ventas asociadas");
                }

                System.out.println("---------------------------------------------------------------------------------------------------");
            } else {
                System.out.println("No existe el Cliente con id " + idClient);
            }
        } catch (InputMismatchException e) {
            System.err.println("Error en la entrada de datos");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}