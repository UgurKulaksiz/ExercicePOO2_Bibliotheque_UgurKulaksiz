package bibliotheque.mvp.model;

import bibliotheque.metier.Exemplaire;
import bibliotheque.metier.Lecteur;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LecteurModelV1 implements DAOLecteur, SpecialLecteur{
    private void populate() throws Exception {
        Lecteur lec = new Lecteur(0,"Dupont","Jean", LocalDate.of(2000,1,4),"Mons","jean.dupont@mail.com","0458774411");
        addLecteur(lec);
        lec = new Lecteur(0,"Durant","Aline",LocalDate.of(1980,10,10),"Binche","aline.durant@mail.com","045874444");
        addLecteur(lec);
    }

    @Override
    public List<Exemplaire> exemplairesEnLocation(Lecteur l) {
        return l.listerExemplairesEnLocation();
    }

    @Override
    public List<Exemplaire> exemplairesLoues(Lecteur l) {
        return new ArrayList<>(l.listerExemplairesLoues());
    }
}
