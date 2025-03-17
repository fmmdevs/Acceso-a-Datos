package devs.fmm.Entities;

public class IdiomaPais {
    private String codigoPais;

    private String idioma;

    private Boolean esOficial;

    private Double porcentaje;

    public IdiomaPais() {
    }

    public IdiomaPais(String codigoPais, String idioma, boolean esOficial, double porcentaje) {
        this.codigoPais = codigoPais;
        this.idioma = idioma;
        this.esOficial = esOficial;
        this.porcentaje = porcentaje;
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public boolean esOficial() {
        return esOficial;
    }

    public void setEsOficial(boolean esOficial) {
        this.esOficial = esOficial;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    @Override
    public String toString() {
        return "%10s %25s %15s %15s%n".formatted(codigoPais, idioma, esOficial, porcentaje);
    }


}
