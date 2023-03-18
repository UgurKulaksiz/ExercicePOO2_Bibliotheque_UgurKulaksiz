package bibliotheque.mvp.presenter;

import bibliotheque.metier.Auteur;
import bibliotheque.mvp.model.DAOAuteur;
import bibliotheque.mvp.view.AuteurViewInterface;

import java.util.List;

public class AuteurPresenter {
    private DAOAuteur model;
    private AuteurViewInterface view;

    public AuteurPresenter(DAOAuteur model, AuteurViewInterface view) {
        this.model = model;
        this.view = view;
        this.view.setPresenter(this);
    }

    public void start() {
        List<Auteur> auteurs = model.getAuteurs();
        view.setListDatas(auteurs);
    }

    public void addAuteur(Auteur auteur) {
        Auteur aut = model.addAuteur(auteur);
        if(auteur!=null) view.affMsg("Création de :"+aut);
        else view.affMsg("Erreur de création");
        List<Auteur> auteurs = model.getAuteurs();
        view.setListDatas(auteurs);
    }


    public void removeAuteur(Auteur auteur) {
        boolean ok = model.removeAuteur(auteur);
        if(ok) view.affMsg("Auteur effacé");
        else view.affMsg("Auteur non effacé");
        List<Auteur> auteurs = model.getAuteurs();
        view.setListDatas(auteurs);
    }

    //Méthode màj à développer
    public void updateAuteur(Auteur aut){
        Auteur lAuteur = model.updateAuteur(aut);
        if(lAuteur!=null) view.affMsg("Mise à jour de :"+lAuteur);
        else view.affMsg("Erreur de màj");
        List<Auteur> auteurs = model.getAuteurs();
        view.setListDatas(auteurs);
    }
}
