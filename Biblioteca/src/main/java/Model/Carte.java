package Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Carti", schema = "public")
public class Carte implements Identifiable<Integer>, Serializable {
    private Integer cod;
    private String titlu;
    private String autor;
    private boolean status;
    private Stare stare;

    public Carte() {
    }

    public Carte(Integer cod, String titlu, String autor, Stare stare) {
        this.cod = cod;
        this.titlu = titlu;
        this.autor = autor;
        this.stare = stare;
        this.status = false;
    }

    public Carte(Integer cod, String titlu, String autor, boolean status, Stare stare) {
        this.cod = cod;
        this.titlu = titlu;
        this.autor = autor;
        this.status = status;
        this.stare = stare;
    }

    @Id
    @Column(name = "cod")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Override
    public Integer getID() {
        return cod;
    }

    @Override
    public void setID(Integer id) {
        this.cod = id;
    }

    @Column(name = "titlu")
    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    @Column(name = "autor")
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Column(name = "stare")
    public Stare getStare() {
        return stare;
    }

    public void setStare(Stare stare) {
        this.stare = stare;
    }

    @Column(name = "status")
    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Carte{" +
                "cod=" + cod +
                ", titlu='" + titlu + '\'' +
                ", autor='" + autor + '\'' +
                ", status='" + status + '\'' +
                ", stare=" + stare +
                '}';
    }
}

//public class Carte implements Identifiable<Integer> {
//    private Integer cod;
//    private String titlu;
//    private String autor;
//    private Stare stare;
//
//    public Carte(Integer cod, String titlu, String autor, Stare stare) {
//        this.cod = cod;
//        this.titlu = titlu;
//        this.autor = autor;
//        this.stare = stare;
//    }
//
//    @Override
//    public Integer getID() {
//        return cod;
//    }
//
//    @Override
//    public void setID(Integer id) {
//        this.cod = id;
//    }
//
//    public String getTitlu() {
//        return titlu;
//    }
//
//    public String getAutor() {
//        return autor;
//    }
//
//    public Stare getStare() {
//        return stare;
//    }
//
//    public void setStare(Stare stare) {
//        this.stare = stare;
//    }
//
//    @Override
//    public String toString() {
//        return "Carte{" +
//                "cod=" + cod +
//                ", titlu='" + titlu + '\'' +
//                ", autor='" + autor + '\'' +
//                ", stare=" + stare +
//                '}';
//    }
//}
