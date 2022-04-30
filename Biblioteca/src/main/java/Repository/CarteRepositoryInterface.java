package Repository;

import Model.Carte;

import java.util.List;

public interface CarteRepositoryInterface extends Repository<Carte, Integer> {
    void delete(Integer id);
    void update(Carte carte, Integer id);
    List<Carte> findByTitlu(String titlu);
    List<Carte> findByAutor(String autor);
    List<Carte> findByTitluAutor(String titlu, String autor);
    List<Carte> findAll();
}
