package bibliotheque.utilitaires;

import bibliotheque.metier.Livre;
import bibliotheque.metier.Ouvrage;
import bibliotheque.metier.TypeLivre;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LivreFactory extends OuvrageFactory{
    public Ouvrage addDetail(String titre, int ageMin, LocalDate dateParution, double prixLocation, String langue, String genre){
        System.out.println("isbn : ");
        String isbn = sc.next();
        System.out.println("Pages : ");
        int nbrePages = sc.nextInt();
        sc.skip("\n");
        TypeLivre[] ttl = TypeLivre.values();
        List<TypeLivre> ltl = new ArrayList<>(Arrays.asList(ttl));
        int choix = Utilitaire.choixListe(ltl);
        TypeLivre tl = ttl[choix-1];
        System.out.println("Résumé du livre : ");
        String resume = sc.nextLine();

        Livre lv = null;
        try {
            lv = new Livre(titre,ageMin,dateParution,prixLocation,langue,genre,isbn,nbrePages,tl,resume);
        } catch (Exception e) {
            System.out.println("erreur : "+e);
        }
        return lv;
    }
}
