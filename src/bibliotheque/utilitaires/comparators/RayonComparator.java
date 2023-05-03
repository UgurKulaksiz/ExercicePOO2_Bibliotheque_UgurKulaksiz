package bibliotheque.utilitaires.comparators;

import bibliotheque.metier.Auteur;
import bibliotheque.metier.Rayon;

import java.util.Comparator;

public class RayonComparator implements Comparator<Rayon> {
    @Override
    public int compare(Rayon ray1, Rayon ray2) {
        return ray1.getGenre().compareTo(ray2.getGenre());
    }
}
