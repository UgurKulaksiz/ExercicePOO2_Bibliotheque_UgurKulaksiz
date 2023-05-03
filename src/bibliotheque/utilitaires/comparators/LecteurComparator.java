package bibliotheque.utilitaires.comparators;

import bibliotheque.metier.Lecteur;

import java.util.Comparator;

public class LecteurComparator implements Comparator<Lecteur> {
    @Override
    public int compare(Lecteur lec1, Lecteur lec2) {
        if (lec1.getNom().compareTo(lec2.getNom()) != 0) return lec1.getNom().compareTo(lec2.getNom());
        return lec1.getPrenom().compareTo(lec2.getPrenom());
    }
}
