package devs.fmm.Entities;

public class Pais {
    private String codigo;
    private String nombre;

    private String continente;

    private String region;

    private Double superficie;

    private Integer anioIndependencia;

    private Long poblacion;

    private Double expectativaVida;

    private Double gnp;

    // Como puede ser null usamos el objeto Double
    private Double gnpOld;

    private String nombreLocal;

    private String formaGobierno;

    private String jefeEstado;

    private Integer capital;

    private String codigo2;


    public Pais() {

    }

    public Pais(String codigo, String nombre, String continente, String region, double superficie, Integer anioIndependencia, long poblacion, Double expectativaVida, double gnp, Double gnpOld, String nombreLocal, String formaGobierno, String jefeEstado, int capital, String codigo2) {
        this.codigo = codigo;
        this.codigo2 = codigo2;
        this.nombre = nombre;
        this.continente = continente;
        this.region = region;
        this.superficie = superficie;
        this.anioIndependencia = anioIndependencia;
        this.poblacion = poblacion;
        this.expectativaVida = expectativaVida;
        this.gnp = gnp;
        this.gnpOld = gnpOld;
        this.nombreLocal = nombreLocal;
        this.formaGobierno = formaGobierno;
        this.jefeEstado = jefeEstado;
        this.capital = capital;
    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo2() {
        return codigo2;
    }

    public void setCodigo2(String codigo2) {
        this.codigo2 = codigo2;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContinente() {
        return continente;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }

    public Integer getAnioIndependencia() {
        return anioIndependencia;
    }

    public void setAnioIndependecia(Integer anioIndependecia) {
        this.anioIndependencia = anioIndependecia;
    }

    public long getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(long poblacion) {
        this.poblacion = poblacion;
    }

    public Double getExpectativaVida() {
        return expectativaVida;
    }

    public void setExpectativaVida(Double expectativaVida) {
        this.expectativaVida = expectativaVida;
    }

    public double getGnp() {
        return gnp;
    }

    public void setGnp(double gnp) {
        this.gnp = gnp;
    }

    public Double getGnpOld() {
        return gnpOld;
    }

    public void setGnpOld(Double gnpOld) {
        this.gnpOld = gnpOld;
    }

    public String getNombreLocal() {
        return nombreLocal;
    }

    public void setNombreLocal(String nombreLocal) {
        this.nombreLocal = nombreLocal;
    }

    public String getFormaGobierno() {
        return formaGobierno;
    }

    public void setFormaGobierno(String formaGobierno) {
        this.formaGobierno = formaGobierno;
    }

    public String getJefeEstado() {
        return jefeEstado;
    }

    public void setJefeEstado(String jefeEstado) {
        this.jefeEstado = jefeEstado;
    }

    public int getCapital() {
        return capital;
    }

    public void setCapital(int capital) {
        this.capital = capital;
    }

    @Override
    public String toString() {
        return "%6s %30s %10s %22s %10s %9s %10s %10s %10s %10s %30s %40s %30s %10s %10s%n".formatted(codigo, nombre, continente, region, superficie, anioIndependencia, poblacion, expectativaVida, gnp, gnpOld, nombreLocal, formaGobierno, jefeEstado, capital, codigo2);
    }
}
