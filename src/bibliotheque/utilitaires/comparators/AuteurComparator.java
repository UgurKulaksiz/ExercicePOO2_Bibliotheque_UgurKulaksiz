package bibliotheque.utilitaires.comparators;

import bibliotheque.metier.Auteur;

import java.util.Comparator;

public class AuteurComparator implements Comparator<Auteur> {
    /* Cette méthode n'est plus valable car la même méthode est dans AuteurViewConsole
    qui se trouve dans la méthode public void setListDatas(List<Auteur> ldatas) */

    //Trie : ordre alphabétique du nom et du prénom
    @Override
    public int compare(Auteur aut1, Auteur aut2) {
        if (aut1.getNom().compareTo(aut2.getNom()) != 0) return aut1.getNom().compareTo(aut2.getNom());
        return aut1.getPrenom().compareTo(aut2.getPrenom());
    }

}
