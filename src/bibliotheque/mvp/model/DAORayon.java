package bibliotheque.mvp.model;

import bibliotheque.metier.Lecteur;
import bibliotheque.metier.Rayon;

import java.util.List;

public interface DAORayon {
    Rayon addRayon(Rayon ray);

    boolean removeRayon(Rayon ray);

    Rayon updateRayon(Rayon ray);

    List<Rayon> getRayons();
}
