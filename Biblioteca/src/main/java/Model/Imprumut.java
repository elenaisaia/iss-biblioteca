package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Imprumut implements Identifiable<Integer> {
    private Integer id;
    private Abonat abonat;
    private List<Carte> carti;
    private LocalDate dataImprumut;
    private LocalDate dataRestituire;
    private Status status;

    public Imprumut(Abonat abonat) {
        this.abonat = abonat;
        this.carti = new ArrayList<>();
        this.dataImprumut = LocalDate.now();
        this.dataRestituire = null;
        this.status = Status.nerestituit;
    }

    public Imprumut(Integer id, Abonat abonat, List<Carte> carti, LocalDate dataImprumut, LocalDate dataRestituire, Status status) {
        this.id = id;
        this.abonat = abonat;
        this.carti = carti;
        this.dataImprumut = dataImprumut;
        this.dataRestituire = dataRestituire;
        this.status = status;
    }

    @Override
    public Integer getID() {
        return id;
    }

    @Override
    public void setID(Integer id) {
        this.id = id;
    }

    public Abonat getAbonat() {
        return abonat;
    }

    public List<Carte> getCarti() {
        return carti;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAbonat(Abonat abonat) {
        this.abonat = abonat;
    }

    public void setCarti(List<Carte> carti) {
        this.carti = carti;
    }

    public void setDataImprumut(LocalDate dataImprumut) {
        this.dataImprumut = dataImprumut;
    }

    public void setDataRestituire(LocalDate dataRestituire) {
        this.dataRestituire = dataRestituire;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Imprumut{" +
                "id=" + id +
                ", abonat=" + abonat +
                ", carti=" + carti +
                '}';
    }
}
