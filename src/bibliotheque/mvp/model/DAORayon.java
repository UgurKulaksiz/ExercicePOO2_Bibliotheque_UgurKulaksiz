package bibliotheque.mvp.model;

import bibliotheque.metier.Rayon;

import java.util.List;

public interface DAORayon {
    Rayon addRayon(Rayon r);
    Rayon readRayon(int idRayon);
    Rayon updateRayon(Rayon r);
    boolean removeRayon(Rayon r);

    List<Rayon> getRayons();
}
