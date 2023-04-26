package bibliotheque.mvp.model;

import bibliotheque.metier.Rayon;

import java.util.List;

public interface DAORayon extends DAO<Rayon>{
    List<Rayon> getRayons();
}
