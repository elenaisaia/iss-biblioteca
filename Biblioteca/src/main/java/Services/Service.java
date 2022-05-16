package Services;

import Model.Abonat;
import Model.Bibliotecar;
import Model.Carte;
import Repository.*;

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
}
