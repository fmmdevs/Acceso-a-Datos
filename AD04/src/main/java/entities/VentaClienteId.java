package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class VentaClienteId implements java.io.Serializable {
    private static final long serialVersionUID = -5540100256341875739L;
    @Column(name = "venta_id", nullable = false)
    private Integer ventaId;

    @Column(name = "cliente_id", nullable = false)
    private Integer clienteId;

    public Integer getVentaId() {
        return ventaId;
    }

    public void setVentaId(Integer ventaId) {
        this.ventaId = ventaId;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        VentaClienteId entity = (VentaClienteId) o;
        return Objects.equals(this.clienteId, entity.clienteId) &&
                Objects.equals(this.ventaId, entity.ventaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clienteId, ventaId);
    }

}