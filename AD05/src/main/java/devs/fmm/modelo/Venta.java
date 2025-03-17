package devs.fmm.modelo;

import java.util.Date;

public class Venta {
    private Integer id;

    // Si tenemos datos en el fichero de otra sesion tenemos que actualizar el ultimo id, esto lo hacemos en el
    // constructor del DAO
    public static Integer ultimoId = -1;

    private Date fecha;

    private double total;

    public Venta() {
        ultimoId++;
        id = ultimoId;
    }

    public Venta(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static Integer getUltimoId() {
        return ultimoId;
    }

    public static void setUltimoId(Integer ultimoId) {
        Venta.ultimoId = ultimoId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", total=" + total +
                '}';
    }
}
