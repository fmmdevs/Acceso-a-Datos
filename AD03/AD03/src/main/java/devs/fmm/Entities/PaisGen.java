package devs.fmm.Entities;

import java.math.BigDecimal;

public class PaisGen {
    private String Code;
    private String Name;

    private String Continent;

    private String Region;

    private Double SurfaceArea;

    private Integer IndepYear;

    private Long Population;

    private Double LifeExpectancy;

    private BigDecimal GNP;

    // Como puede ser null usamos el objeto Double
    private Double GNPOld;

    private String LocalName;

    private String GovernmentForm;

    private String HeadOfState;

    private Integer Capital;

    private String Code2;


    public PaisGen() {

    }

    public PaisGen(String codigo, String nombre, String continente, String region, double superficie, Integer anioIndependencia, long poblacion, Double expectativaVida, BigDecimal gnp, Double gnpOld, String nombreLocal, String formaGobierno, String jefeEstado, int capital, String codigo2) {
        this.Code = codigo;
        this.Code2 = codigo2;
        this.Name = nombre;
        this.Continent = continente;
        this.Region = region;
        this.SurfaceArea = superficie;
        this.IndepYear = anioIndependencia;
        this.Population = poblacion;
        this.LifeExpectancy = expectativaVida;
        this.GNP = gnp;
        this.GNPOld = gnpOld;
        this.LocalName = nombreLocal;
        this.GovernmentForm = formaGobierno;
        this.HeadOfState = jefeEstado;
        this.Capital = capital;
    }


    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        this.Code = code;
    }

    public String getCode2() {
        return Code2;
    }

    public void setCode2(String code2) {
        this.Code2 = code2;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getContinent() {
        return Continent;
    }

    public void setContinent(String continent) {
        this.Continent = continent;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        this.Region = region;
    }

    public double getSurfaceArea() {
        return SurfaceArea;
    }

    public void setSurfaceArea(double surfaceArea) {
        this.SurfaceArea = surfaceArea;
    }

    public Integer getIndepYear() {
        return IndepYear;
    }

    public void setAnioIndependecia(Integer anioIndependecia) {
        this.IndepYear = anioIndependecia;
    }

    public long getPopulation() {
        return Population;
    }

    public void setPopulation(long population) {
        this.Population = population;
    }

    public Double getLifeExpectancy() {
        return LifeExpectancy;
    }

    public void setLifeExpectancy(Double lifeExpectancy) {
        this.LifeExpectancy = lifeExpectancy;
    }

    public BigDecimal getGNP() {
        return GNP;
    }

    public void setGNP(BigDecimal GNP) {
        this.GNP = GNP;
    }

    public Double getGNPOld() {
        return GNPOld;
    }

    public void setGNPOld(Double GNPOld) {
        this.GNPOld = GNPOld;
    }

    public String getLocalName() {
        return LocalName;
    }

    public void setLocalName(String localName) {
        this.LocalName = localName;
    }

    public String getGovernmentForm() {
        return GovernmentForm;
    }

    public void setGovernmentForm(String governmentForm) {
        this.GovernmentForm = governmentForm;
    }

    public String getHeadOfState() {
        return HeadOfState;
    }

    public void setHeadOfState(String headOfState) {
        this.HeadOfState = headOfState;
    }

    public int getCapital() {
        return Capital;
    }

    public void setCapital(int capital) {
        this.Capital = capital;
    }

    @Override
    public String toString() {
        return "%6s %30s %10s %22s %10s %9s %10s %10s %10s %10s %30s %40s %30s %10s %10s%n".formatted(
                Code != null ? Code : "---",
                Name != null ? Name : "-----",
                Continent != null ? Continent : "-----",
                Region != null ? Region : "------",
                SurfaceArea != null ? SurfaceArea : "-----",
                IndepYear != null ? IndepYear : "----",
                Population != null ? Population : "------",
                LifeExpectancy != null ? LifeExpectancy : "---",
                GNP != null ? GNP : "-----",
                GNPOld != null ? GNPOld : "----",
                LocalName != null ? LocalName : "----",
                GovernmentForm != null ? GovernmentForm : "-----",
                HeadOfState != null ? HeadOfState : "------",
                Capital != null ? Capital : "-------",
                Code2 != null ? Code2 : "--");
    }
}
