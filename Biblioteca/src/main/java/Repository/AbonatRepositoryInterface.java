package Repository;

import Model.Abonat;

public interface AbonatRepositoryInterface extends Repository<Abonat, String> {
    Abonat findByCod(Integer cod);
}
