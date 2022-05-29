package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Imprumut implements Identifiable<Integer> {
    private Integer id;
    private String abonat;
    private List<Carte> carti;
    private LocalDate dataImprumut;
    private LocalDate dataRestituire;
    private Status status;

    public Imprumut(String abonat) {
        this.abonat = abonat;
        this.carti = new ArrayList<>();
        this.dataImprumut = LocalDate.now();
        this.dataRestituire = null;
        this.status = Status.nerestituit;
    }

    public Imprumut(Integer id, String abonat, List<Carte> carti, LocalDate dataImprumut, LocalDate dataRestituire, Status status) {
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

    public String getAbonat() {
        return abonat;
    }

    public List<Carte> getCarti() {
        return carti;
    }

    public LocalDate getDataImprumut() {
        return dataImprumut;
    }

    public LocalDate getDataRestituire() {
        return dataRestituire;
    }

    public Status getStatus() {
        return status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAbonat(String abonat) {
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
