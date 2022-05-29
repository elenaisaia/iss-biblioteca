package Services;

import Model.*;
import Repository.*;

import java.time.LocalDate;
import java.util.List;

public class Service {
    private final BibliotecarRepositoryInterface repoBibliotecar;
    private final AbonatRepositoryInterface repoAbonat;
    private final CarteRepositoryInterface repoCarte;
    private final ImprumutRepositoryInterface repoImprumut;

    public Service(BibliotecarRepositoryInterface repoBibliotecar, AbonatRepositoryInterface repoAbonat,
                   CarteRepositoryInterface repoCarte, ImprumutRepositoryInterface repoImprumut) {
        this.repoBibliotecar = repoBibliotecar;
        this.repoAbonat = repoAbonat;
        this.repoCarte = repoCarte;
        this.repoImprumut = repoImprumut;
    }

    public synchronized boolean login(Bibliotecar persoana) throws BibliotecaException {
        Bibliotecar bibliotecarGasit = repoBibliotecar.findById(persoana.getID());
        Abonat abonatGasit = repoAbonat.findById(persoana.getID());
        if(bibliotecarGasit == null && abonatGasit == null) {
            throw new BibliotecaException("Cont inexistent! :<");
        }

        if(abonatGasit == null) {
            if(!bibliotecarGasit.getParola().equals(persoana.getParola())) {
                throw new BibliotecaException("Parola gresita! :<");
            }
            return false;
        }

        if(!abonatGasit.getParola().equals(persoana.getParola())) {
            throw new BibliotecaException("Parola gresita! :<");
        }
        return true;
    }

    public synchronized void register(Abonat abonat) throws BibliotecaException {
        Abonat abonatGasit = repoAbonat.findById(abonat.getID());
        if(abonatGasit != null) {
            throw new BibliotecaException("Abonat deja existent! :<");
        }
        addAbonat(abonat);
    }

    private synchronized void addAbonat(Abonat abonat) {
        repoAbonat.add(abonat);
    }

    public List<Carte> findAllCarti() {
        return repoCarte.findAll();
    }

    public synchronized List<Carte> findCartiByTitlu(String titlu) {
        return repoCarte.findByTitlu(titlu);
    }

    public synchronized List<Carte> findCartiByAutor(String autor) {
        return repoCarte.findByAutor(autor);
    }

    public synchronized List<Carte> findCartiByTitluAutor(String titlu, String autor) {
        return repoCarte.findByTitluAutor(titlu, autor);
    }

    public synchronized List<Carte> findCartiByStatus(boolean status) {
        return repoCarte.findByStatus(status);
    }

    public synchronized List<Carte> findCartiByTitluStatus(String titlu, boolean status) {
        return repoCarte.findByTitluStatus(titlu, status);
    }

    public synchronized List<Carte> findCartiByAutorStatus(String autor, boolean status) {
        return repoCarte.findByAutorStatus(autor, status);
    }

    public synchronized List<Carte> findCartiByTitluAutorStatus(String titlu, String autor, boolean status) {
        return repoCarte.findByTitluAutorStatus(titlu, autor, status);
    }

    public synchronized void addCarte(Carte carte) {
        repoCarte.add(carte);
    }

    public synchronized void deleteCarte(Carte carte) {
        repoCarte.delete(carte.getID());
    }

    public synchronized void modifyCarte(Carte carte) {
        repoCarte.update(carte, carte.getID());
    }

    public synchronized void updateCarte(Carte carte) {
        carte.setStatus(true);
        repoCarte.update(carte, carte.getID());
    }

    public synchronized void updateImprumut(Imprumut imprumut) {
        imprumut.setStatus(Status.restituit);
        imprumut.setDataRestituire(LocalDate.now());
        List<Integer> coduri = repoImprumut.findByImprumut(imprumut.getID());
        for(Integer cod : coduri) {
            Carte carte = repoCarte.findById(cod);
            carte.setStatus(false);
            repoCarte.update(carte, carte.getID());
        }
    }

    public synchronized void addImprumut(Imprumut imprumut) {
        repoImprumut.add(imprumut);
    }

    public synchronized void addImprumutCarte(Imprumut imprumut, Carte carte) {
        repoImprumut.addIC(imprumut, carte);
    }

    public synchronized List<Imprumut> findImprumuturi() {
        return repoImprumut.findAll();
    }
}
