package devs.fmm.modelo;

public class VentaCliente {
    private Integer ventaId;
    private Integer clienteId;

    public VentaCliente() {
    }

    // Constructor para la query que encuentra las ventas asociadas a un cliente
    public VentaCliente(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public VentaCliente(Integer ventaId, Integer clienteId) {
        this.ventaId = ventaId;
        this.clienteId = clienteId;
    }

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
    public String toString() {
        return "VentaCliente{" +
                "ventaId=" + ventaId +
                ", clienteId=" + clienteId +
                '}';
    }
}
