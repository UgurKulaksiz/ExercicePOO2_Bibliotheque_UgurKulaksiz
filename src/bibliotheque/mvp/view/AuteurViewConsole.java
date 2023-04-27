package bibliotheque.mvp.view;

import bibliotheque.metier.*;
import bibliotheque.mvp.presenter.AuteurPresenter;
import bibliotheque.utilitaires.Utilitaire;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static bibliotheque.utilitaires.Utilitaire.*;

public class AuteurViewConsole extends AbstractViewConsole<Auteur> {
    @Override
    protected void rechercher() {

    }

    @Override
    protected void modifier() {

    }

    @Override
    protected void ajouter() {
        Auteur a=null;
        do {
            try {
                System.out.println("nom ");
                String nom = sc.nextLine();
                System.out.println("prénom ");
                String prenom = sc.nextLine();
                System.out.println("nationalité");
                String nat = sc.nextLine();
                a = new Auteur(nom, prenom, nat);
                presenter.add(a);
                break;
            } catch (Exception e) {
                System.out.println("une erreur est survenue : "+e.getMessage());
            }
        }while(true);


        //TODO attribuer ouvrages , les ouvrages sont triés par ordre de titre
    }

    @Override
    protected void special() {

    }

}
