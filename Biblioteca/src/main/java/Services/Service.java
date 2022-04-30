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

    public boolean login(Bibliotecar persoana) throws BibliotecaException {
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

    public void register(Abonat abonat) throws BibliotecaException {
        Abonat abonatGasit = repoAbonat.findById(abonat.getID());
        if(abonatGasit != null) {
            throw new BibliotecaException("Abonat deja existent! :<");
        }
        addAbonat(abonat);
    }

    private void addAbonat(Abonat abonat) {
        repoAbonat.add(abonat);
    }

    public List<Carte> findAllCarti() {
        return repoCarte.findAll();
    }

    public List<Carte> findCartiByTitlu(String titlu) {
        return repoCarte.findByTitlu(titlu);
    }

    public List<Carte> findCartiByAutor(String autor) {
        return repoCarte.findByAutor(autor);
    }

    public List<Carte> findCartiByTitluAutor(String titlu, String autor) {
        return repoCarte.findByTitluAutor(titlu, autor);
    }
}
