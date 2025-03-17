package devs.fmm.Entities;

public class CiudadGen {

    private Integer ID;

    private String Name;

    private String CountryCode;

    private String District;

    private Integer Population;

    public CiudadGen() {
    }


    public CiudadGen(int id, String Name, String CountryCode, String District, int Population) {
        this.ID = id;
        this.Name = Name;
        this.CountryCode = CountryCode;
        this.District = District;
        this.Population = Population;
    }

    public Integer getId() {
        return ID;
    }

    public void setId(int id) {
        this.ID = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        this.CountryCode = countryCode;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        this.District = district;
    }

    public Integer getPopulation() {
        return Population;
    }

    public void setPopulation(int population) {
        this.Population = population;
    }

    // Mejorar?
    @Override
    public String toString() {
        return "%-10s %34s %10s %20s %20s%n".formatted(
                getId() != null ? getId() : "----",
                getName() != null ? getName() : "-------",
                getCountryCode() != null ? getCountryCode() : "---",
                getDistrict() != null ? getDistrict() : "--------",
                getPopulation() != null ? getPopulation() : "------");
    }
}
