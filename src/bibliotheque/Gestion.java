package bibliotheque;

import java.time.LocalDate;

public class Gestion {
    public static void main(String[] args) {
        //1° Créer un auteur
        Auteur a = new Auteur("Verne","Jules","France");

        //2° Créer un un livre de cet auteur
        Livre l = new Livre("Vingt mille lieues sous les mers",10, LocalDate.of(2016,4,30),
                TypeOuvrage.CD, 10.00, "français","aventure","a125",350,TypeLivre.ROMAN,"histoire de sous-marin");
        //a.getLouvrage().add(l);
        //l.getLauteurs().add(a);
        a.addAuteurOuvrage(l);

        //3° Créer un rayon
        Rayon r = new Rayon("r12","aventure");

        //4° Créer un exemplaire de ce livre dans ce rayon
        Exemplaire e = new Exemplaire("m12","état neuf",l);
        e.setRayon(r);
        r.getLex().add(e);

        //5° Créer un lecteur
        Lecteur lec = new Lecteur(1,"Dupont","Jean",LocalDate.of(2000,1,4),"Mons","jean.dupont@mail.com","0458774411");

        //6° Louer cet exemplaire à ce lecteur
        Location loc = new Location(LocalDate.of(2023,2,1),LocalDate.of(2023,3,1),lec,e);
        lec.getLloc().add(loc);
        e.getLloc().add(loc);
        System.out.println(a);
        System.out.println(l);
        System.out.println(r);
        System.out.println(e);
        System.out.println(lec);
        System.out.println(loc);

    }
}
