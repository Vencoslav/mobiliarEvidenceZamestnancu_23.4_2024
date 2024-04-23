import java.time.LocalDate;

public class Zamestnanec {
    private String jmeno;
    private String prijmeni;
    private Boolean penzijniPojisteni;
    private LocalDate datum;

    public Zamestnanec(String jmeno, String prijmeni, Boolean penzijniPojisteni, LocalDate datum) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.penzijniPojisteni = penzijniPojisteni;
        this.datum = datum;
    }

    public String getJmeno() {
        return jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public Boolean getPenzijniPojisteni() {
        return penzijniPojisteni;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public void setPenzijniPojisteni(Boolean penzijniPojisteni) {
        this.penzijniPojisteni = penzijniPojisteni;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }
}
