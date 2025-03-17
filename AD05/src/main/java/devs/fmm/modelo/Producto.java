package devs.fmm.modelo;


public class Producto {
    private Integer id;

    // Si tenemos datos en el fichero de otra sesion tenemos que actualizar el ultimo id, esto lo hacemos en el
    // constructor del DAO
    public static int ultimoId=-1;

    private String nombre;

    private double precioCompra;

    private double precioVenta;

    private Integer stock;

    // Constructor vacío para crear nuevos Productos
    public Producto() {
        // Al crear un nuevo producto utilizamos este constructor, que se encarga de asignar un id
        // el resto de los atributos los asignamos mediante los métodos setters
        ultimoId++;
        this.id = ultimoId;
    }

    // Constructor para encontrar un producto en la bbdd db4o
    public Producto(Integer id) {
        this.id = id;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precioCompra=" + precioCompra +
                ", precioVenta=" + precioVenta +
                ", stock=" + stock +
                '}';
    }
}
