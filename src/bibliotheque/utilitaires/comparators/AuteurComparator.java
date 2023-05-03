package bibliotheque.utilitaires.comparators;

import bibliotheque.metier.Auteur;

import java.util.Comparator;

public class AuteurComparator implements Comparator<Auteur> {
    @Override
    public int compare(Auteur aut1, Auteur aut2) {
        if (aut1.getNom().compareTo(aut2.getNom()) != 0) return aut1.getNom().compareTo(aut2.getNom());
        return aut1.getPrenom().compareTo(aut2.getPrenom());
    }

    //Trie : ordre alphabétique du nom et du prénom
    public static Comparator<Auteur> trieAuteur() {
        return new Comparator<Auteur>() {
            @Override
            public int compare(Auteur aut1, Auteur aut2) {
                int resultat = aut1.getNom().compareTo(aut2.getNom());
                if (resultat != 0) {
                    return resultat;
                } else {
                    return aut1.getPrenom().compareTo(aut2.getPrenom());
                }
            }
        };
    }
}
