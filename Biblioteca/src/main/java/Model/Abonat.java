package Model;

import java.io.Serializable;

public class Abonat implements Identifiable<String>, Serializable {
    private String cnp;
    private String nume;
    private String prenume;
    private String adresa;
    private String telefon;
    private Integer cod;
    private String parola;

    public Abonat(String cnp, String nume, String prenume, String adresa, String telefon, Integer cod, String parola) {
        this.cnp = cnp;
        this.nume = nume;
        this.prenume = prenume;
        this.adresa = adresa;
        this.telefon = telefon;
        this.cod = cod;
        this.parola = parola;
    }

    @Override
    public String getID() {
        return this.cnp;
    }

    @Override
    public void setID(String id) {
        this.cnp = id;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getTelefon() {
        return telefon;
    }

    public Integer getCod() {
        return cod;
    }

    public String getParola() {
        return parola;
    }

    @Override
    public String toString() {
        return "Abonat{" +
                "cnp='" + cnp + '\'' +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", adresa=" + adresa + '\'' +
                ", telefon='" + telefon + '\'' +
                ", cod='" + cod + '\'' +
                ", parola='" + parola + '\'' +
                '}';
    }
}
