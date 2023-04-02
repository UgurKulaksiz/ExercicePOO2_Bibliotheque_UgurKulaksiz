package bibliotheque.mvp.presenter;

import bibliotheque.metier.*;
import bibliotheque.mvp.model.AuteurModel;
import bibliotheque.mvp.model.DAOAuteur;
import bibliotheque.mvp.model.SpecialAuteur;
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

    public void start() throws Exception {
        List<Auteur> auteurs = model.getAuteurs();
        view.setListDatas(auteurs);
    }

    public List<Auteur> getAll(){
        return model.getAuteurs();
    }

    public void add(Auteur auteur) throws Exception {
        Auteur aut = model.add(auteur);
        if(auteur!=null) view.affMsg("Création de :"+aut);
        else view.affMsg("Erreur de création");
        List<Auteur> auteurs = model.getAuteurs();
        view.setListDatas(auteurs);
    }


    public void removeAuteur(Auteur auteur) throws Exception {
        boolean ok = model.remove(auteur);
        if(ok) view.affMsg("Auteur effacé");
        else view.affMsg("Auteur non effacé");
        List<Auteur> auteurs = model.getAuteurs();
        view.setListDatas(auteurs);
    }

    //Méthode màj à développer
    public void updateAuteur(Auteur aut) throws Exception {
        Auteur lAuteur = model.update(aut);
        if(lAuteur!=null) view.affMsg("Mise à jour de :"+lAuteur);
        else view.affMsg("Erreur de màj");
        List<Auteur> auteurs = model.getAuteurs();
        view.setListDatas(auteurs);
    }

    public void search(String nom, String prenom, String nationalite) throws Exception {
        Auteur aut = new Auteur(nom, prenom, nationalite);
        Auteur autTrouve = model.read(aut);
        if(aut==null) view.affMsg("Recherche infructueuse ");
        else view.affMsg(toString());
    }

    public void listerOuvrages(){
        List<Ouvrage> lOuvrage = ((SpecialAuteur)model).listerOuvrages();
        if(lOuvrage==null || lOuvrage.isEmpty()) view.affMsg("Aucun ouvrage trouvé ");
        else view.affList(lOuvrage);
    }
    public void listerOuvrages(TypeOuvrage to, TypeLivre tl){
        List<Ouvrage> lOuvrage = ((SpecialAuteur)model).listerOuvrages(to, tl);
        if(lOuvrage==null || lOuvrage.isEmpty()) view.affMsg("Aucun ouvrage trouvé ");
        else view.affList(lOuvrage);
    }

    public void listerOuvrage(String genre){
        List<Ouvrage> lOuvrage = ((SpecialAuteur)model).listerOuvrage(genre);
        if(lOuvrage==null || lOuvrage.isEmpty()) view.affMsg("Aucun ouvrage trouvé ");
        else view.affList(lOuvrage);
    }
}
