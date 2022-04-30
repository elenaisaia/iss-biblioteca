package Model;

public class Carte implements Identifiable<Integer> {
    private Integer cod;
    private String titlu;
    private String autor;
    private Stare stare;

    public Carte(Integer cod, String titlu, String autor, Stare stare) {
        this.cod = cod;
        this.titlu = titlu;
        this.autor = autor;
        this.stare = stare;
    }

    @Override
    public Integer getID() {
        return cod;
    }

    @Override
    public void setID(Integer id) {
        this.cod = id;
    }

    public String getTitlu() {
        return titlu;
    }

    public String getAutor() {
        return autor;
    }

    public Stare getStare() {
        return stare;
    }

    public void setStare(Stare stare) {
        this.stare = stare;
    }

    @Override
    public String toString() {
        return "Carte{" +
                "cod=" + cod +
                ", titlu='" + titlu + '\'' +
                ", autor='" + autor + '\'' +
                ", stare=" + stare +
                '}';
    }
}
