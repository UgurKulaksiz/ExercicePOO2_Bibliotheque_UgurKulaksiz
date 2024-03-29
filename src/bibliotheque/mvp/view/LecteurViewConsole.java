package bibliotheque.mvp.view;

import bibliotheque.metier.Auteur;
import bibliotheque.metier.Exemplaire;
import bibliotheque.metier.Lecteur;
import bibliotheque.mvp.presenter.LecteurPresenter;
import bibliotheque.mvp.presenter.SpecialLecteurPresenter;

import java.time.LocalDate;
import java.util.*;

import static bibliotheque.utilitaires.Utilitaire.*;
import static bibliotheque.utilitaires.Utilitaire.lireInt;

public class LecteurViewConsole extends AbstractViewConsole<Lecteur> implements SpecialLecteurViewConsole {
    /*
    @Override
    public void setListDatas(List<Lecteur> ldatas) {
        /*
        Comparator<Lecteur> cmp = new LecteurComparator();
        ldatas.sort(cmp);
        super.setListDatas(ldatas);

        ldatas.sort((o1, o2) -> {
            if(o1.getNom().compareTo(o2.getNom())!=0) return (o1.getNom().compareTo(o2.getNom()));
            return o1.getPrenom().compareTo(o2.getPrenom())  ;
        });

        //Lambda expressions
        /* Enoncé V7 : Remplacez les instructions de tri utilisant une classe anonyme
        par une lambda expression.

        Comparator<Lecteur> cmp = (lec1,lec2)-> lec1.getNom().compareTo(lec2.getNom());
        cmp = cmp.thenComparing((lec1,lec2)-> lec1.getPrenom().compareTo(lec2.getPrenom()));
        ldatas.sort(cmp);
    }
    */

    protected void rechercher() {
        System.out.println("numLecteur : ");
        int idLecteur = sc.nextInt();
        Lecteur rech = null;
        try {
            rech = new Lecteur(idLecteur, "zzz", "zzz", null, null, null, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        presenter.search(rech);
    }

    protected void modifier() {
        int choix = choixElt(ldatas);
        Lecteur l = ldatas.get(choix - 1);
        String nom = modifyIfNotBlank("nom", l.getNom());
        String prenom = modifyIfNotBlank("prénom", l.getPrenom());
        String date = modifyIfNotBlank("date de naissance", getDateFrench(l.getDn()));
        String[] jma = date.split(" ");
        int j = Integer.parseInt(jma[0]);
        int m = Integer.parseInt(jma[1]);
        int a = Integer.parseInt(jma[2]);
        LocalDate dn = LocalDate.of(a, m, j);
        String adr = modifyIfNotBlank("adresse", l.getAdresse());
        String mail = modifyIfNotBlank("mail", l.getMail());
        String tel = modifyIfNotBlank("tel", l.getTel());
        Lecteur lec = null;
        try {
            lec = new Lecteur(l.getNumlecteur(), nom, prenom, dn, adr, mail, tel);
        } catch (Exception e) {
            System.out.println("erreur :" + e);
        }
        presenter.update(lec);
        ldatas = presenter.getAll();//rafraichissement
        affListe(ldatas);
    }

    protected void retirer() {
        int choix = choixElt(ldatas);
        Lecteur lecteur = ldatas.get(choix - 1);
        presenter.remove(lecteur);
        ldatas = presenter.getAll();//rafraichissement
        affListe(ldatas);
    }


    protected void ajouter() {
        System.out.println("nom ");
        String nom = sc.nextLine();
        System.out.println("prénom ");
        String prenom = sc.nextLine();
        System.out.println("date de naissance");
        String[] jma = sc.nextLine().split(" ");
        int j = Integer.parseInt(jma[0]);
        int m = Integer.parseInt(jma[1]);
        int a = Integer.parseInt(jma[2]);
        LocalDate dn = LocalDate.of(a, m, j);
        System.out.println("adresse");
        String adr = sc.nextLine();
        System.out.println("mail");
        String mail = sc.nextLine();
        System.out.println("tel ");
        String tel = sc.nextLine();
        Lecteur lec = null;
        try {
            lec = new Lecteur(0, nom, prenom, dn, adr, mail, tel);
            presenter.add(lec);
            ldatas = presenter.getAll();//rafraichissement
            affListe(ldatas);
        } catch (Exception e) {
            System.out.println("erreur : " + e);
        }
    }

    protected void special() {
        int choix = choixElt(ldatas);
        Lecteur lec = ldatas.get(choix - 1);
        do {
            System.out.println("1.Exemplaire en location\n2.Exemplaires loués\n3. Recherche par mail\n4. Chargement par fichier\n5.menu principal");
            System.out.println("choix : ");
            int ch = lireInt();
            sc.skip("\n");
            switch (ch) {
                case 1:
                    ((LecteurPresenter) presenter).exemplairesEnLocation(lec);
                    break;
                case 2:
                    ((LecteurPresenter) presenter).exemplairesLoues(lec);
                    break;
                case 3:
                    lecParMail();
                    break;
                case 4:
                    chargmementLecteurParFichier();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("choix invalide recommencez ");
            }
        } while (true);

    }

    @Override
    public void exemplairesLoues(Lecteur lec) {
        ((SpecialLecteurPresenter)presenter).exemplairesLoues(lec);
    }

    @Override
    public void exemplairesLocation(Lecteur lec) {
        ((SpecialLecteurPresenter)presenter).exemplairesEnLocation(lec);
    }

    @Override
    public void lecParMail() {
        //ajout pour forcer push
        System.out.print("mail recherché : ");
        String mail= sc.next();
        ((SpecialLecteurPresenter)presenter).lecParMail(mail);
    }

    @Override
    public void chargmementLecteurParFichier() {
        ((SpecialLecteurPresenter)presenter).chargementLecteurParFichier();
    }
}
