package bibliotheque.mvp.model;

import java.util.List;

public interface DAO<T> {
    T add(T newObj);

    T update(T objRech);

    boolean remove(T ObjRech);

    T read(T objRech);

    List<T> getAll();
}
