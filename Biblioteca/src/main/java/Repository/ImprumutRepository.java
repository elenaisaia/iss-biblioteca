package Repository;

import Model.Abonat;
import Model.Imprumut;

import java.util.List;
import java.util.Properties;

public class ImprumutRepository implements ImprumutRepositoryInterface {
    private JdbcUtils dbUtils;

    public ImprumutRepository(Properties properties) {
        dbUtils = new JdbcUtils(properties);
    }

    @Override
    public void update(Imprumut imprumut, Integer id) {

    }

    @Override
    public List<Imprumut> findByAbonat(Abonat abonat) {
        return null;
    }

    @Override
    public void add(Imprumut elem) {

    }

    @Override
    public Imprumut findById(Integer id) {
        return null;
    }
}
