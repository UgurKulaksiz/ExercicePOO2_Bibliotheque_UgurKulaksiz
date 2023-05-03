package bibliotheque.utilitaires.comparators;

import bibliotheque.metier.Exemplaire;

import java.util.Comparator;

public class ExemplaireComparator implements Comparator<Exemplaire> {
    @Override
    public int compare(Exemplaire ex1, Exemplaire ex2) {
        return ex1.getRayon().getCodeRayon().compareTo(ex1.getRayon().getCodeRayon());

    }
}
