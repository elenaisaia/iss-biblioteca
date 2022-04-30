package Repository;

public interface Repository<T, Tid> {
    void add(T elem);
    T findById(Tid id);
}

