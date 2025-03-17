package devs.fmm.Entities;

import java.math.BigDecimal;

public class IdiomaPaisGen {
    private String CountryCode;

    private String Language;

    private Boolean IsOfficial;

    private BigDecimal Percentage;

    public IdiomaPaisGen() {
    }

    public IdiomaPaisGen(String codigoPais, String idioma, boolean esOficial, BigDecimal porcentaje) {
        this.CountryCode = codigoPais;
        this.Language = idioma;
        this.IsOfficial = esOficial;
        this.Percentage = porcentaje;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        this.CountryCode = countryCode;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        this.Language = language;
    }

    public boolean esOficial() {
        return IsOfficial;
    }

    public void setIsOfficial(boolean isOfficial) {
        this.IsOfficial = isOfficial;
    }

    public BigDecimal getPercentage() {
        return Percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.Percentage = percentage;
    }

    @Override
    public String toString() {
        return "%10s %25s %15s %15s%n".formatted(
                CountryCode != null ? CountryCode : "---",
                Language != null ? Language : "------",
                IsOfficial != null ? IsOfficial : "---",
                Percentage != null ? Percentage : "---");
    }


}
