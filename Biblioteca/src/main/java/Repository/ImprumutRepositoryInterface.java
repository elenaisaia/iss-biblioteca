package Repository;

import Model.Abonat;
import Model.Imprumut;

import java.util.List;

public interface ImprumutRepositoryInterface extends Repository<Imprumut, Integer> {
    void update(Imprumut imprumut, Integer id);
    List<Imprumut> findByAbonat(Abonat abonat);
}
