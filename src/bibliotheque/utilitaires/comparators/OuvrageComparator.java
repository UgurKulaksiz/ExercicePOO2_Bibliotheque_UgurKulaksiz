package bibliotheque.utilitaires.comparators;

import bibliotheque.metier.Ouvrage;

import java.util.Comparator;

public class OuvrageComparator implements Comparator<Ouvrage> {

    @Override
    public int compare(Ouvrage ouv1, Ouvrage ouv2) {
        return ouv1.getTitre().compareTo(ouv2.getTitre());
    }
}
