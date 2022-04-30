package Model;

import java.io.Serializable;

public class Bibliotecar implements Identifiable<String>, Serializable {
    private String cnp;
    private String nume;
    private String prenume;
    private String parola;

    public Bibliotecar(String cnp, String nume, String prenume, String parola) {
        this.cnp = cnp;
        this.nume = nume;
        this.prenume = prenume;
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

    public String getParola() {
        return parola;
    }

    @Override
    public String toString() {
        return "Bibliotecar{" +
                "cnp='" + cnp + '\'' +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", parola='" + parola + '\'' +
                '}';
    }
}
