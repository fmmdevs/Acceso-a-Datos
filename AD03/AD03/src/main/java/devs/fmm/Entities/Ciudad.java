package devs.fmm.Entities;

public class Ciudad {

    private Integer id;

    private String nombre;

    private String codigoPais;

    private String distrito;

    private Integer poblacion;

    public Ciudad() {
    }


    public Ciudad(int id, String nombre, String codigoPais, String distrito, int poblacion) {
        this.id = id;
        this.nombre = nombre;
        this.codigoPais = codigoPais;
        this.distrito = distrito;
        this.poblacion = poblacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public int getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(int poblacion) {
        this.poblacion = poblacion;
    }

    // Mejorar?
    @Override
    public String toString() {
        return "%-10d %34s %10s %20s %20d%n".formatted(getId(), getNombre(), getCodigoPais(), getDistrito(), getPoblacion());
    }
}
