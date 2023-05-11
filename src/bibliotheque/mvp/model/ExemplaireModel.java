package bibliotheque.mvp.model;

import bibliotheque.metier.Exemplaire;
import bibliotheque.metier.Lecteur;
import bibliotheque.metier.Mail;

import java.util.List;

public class ExemplaireModel extends AbstractModel<Exemplaire> implements SpecialExemplaire {
    @Override
    public void modifierEtat(Exemplaire ex, String etat) {
        ex.setDescriptionEtat(etat);
    }

    @Override
    public Lecteur lecteurActuel(Exemplaire ex) {
        return ex.lecteurActuel();
    }

    @Override
    public List<Lecteur> lecteurs(Exemplaire ex) {
        return ex.lecteurs();
    }

    /* Enoncé V8
    2.Lors de l'envoi de mail à tous les loueurs d'un exemplaire donné, créez et envoyez
    une série de mails dont l'objet est information et dont le contenu est :
    "vous avez loué les exemplaires suivants :

    - matricule de l'exemplaire - titre de l'ouvrage

    -

    La bibliothèque "
     */
    @Override
    public void envoiMailLecteurActuel(Exemplaire ex) {
        Mail m = new Mail("demo","message de test");
        ex.envoiMailLecteurActuel(m);
    }

    @Override
    public void envoiMailLecteurs(Exemplaire ex) {
        String msg = "Vous avez loué l'exemplaire suivant : \n"+ex+"\n"+"La Bibliothèque";
        Mail m = new Mail("information",msg);
        ex.envoiMailLecteurs(m);
    }

    @Override
    public boolean enRetard(Exemplaire ex) {
        return ex.enRetard();
    }

    @Override
    public int joursRetard(Exemplaire ex) {
        return ex.joursRetard();
    }

    @Override
    public boolean enLocation(Exemplaire ex) {
        return ex.enLocation();
    }
}
