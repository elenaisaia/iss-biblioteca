package Repository;

import Model.Abonat;
import Model.Carte;
import Model.Imprumut;

import java.util.List;

public interface ImprumutRepositoryInterface extends Repository<Imprumut, Integer> {
    void update(Imprumut imprumut, Integer id);
    List<Imprumut> findAll();
    void addIC(Imprumut imprumut, Carte carte);
    List<Integer> findByImprumut(Integer id);
}
