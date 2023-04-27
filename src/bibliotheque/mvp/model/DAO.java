package bibliotheque.mvp.model;

import java.util.List;

public interface DAO<T> {
    T add(T newObj);

    T read(T objRech);
    T update(T objRech);

    boolean remove(T ObjRech);


    List<T> getAll();
}
