package bibliotheque.mvp.presenter;

import bibliotheque.metier.*;
import bibliotheque.mvp.model.DAOAuteur;
import bibliotheque.mvp.model.DAORayon;
import bibliotheque.mvp.model.SpecialAuteur;
import bibliotheque.mvp.model.SpecialRayon;
import bibliotheque.mvp.view.AuteurViewInterface;
import bibliotheque.mvp.view.RayonViewInterface;

import java.util.List;

public class RayonPresenter {
    private DAORayon model;
    private RayonViewInterface view;

    public RayonPresenter(DAORayon model, RayonViewInterface view) {
        this.model = model;
        this.view = view;
        this.view.setPresenter(this);
    }

    public void start() {
        List<Rayon> rayons = model.getRayons();
        view.setListDatas(rayons);
    }

    public List<Rayon> getAll(){
        return model.getRayons();
    }

    public void addRayon(Rayon rayon) {
        Rayon ray = model.addRayon(rayon);
        if(rayon!=null) view.affMsg("Création de :"+ray);
        else view.affMsg("Erreur de création");
        List<Rayon> rayons = model.getRayons();
        view.setListDatas(rayons);
    }


    public void removeRayon(Rayon rayon) {
        boolean ok = model.removeRayon(rayon);
        if(ok) view.affMsg("Rayon effacé");
        else view.affMsg("Rayon non effacé");
        List<Rayon> rayons = model.getRayons();
        view.setListDatas(rayons);
    }

    //Méthode màj à développer
    public void updateRayon(Rayon ray){
        Rayon lRayon = model.updateRayon(ray);
        if(lRayon!=null) view.affMsg("Mise à jour de :"+lRayon);
        else view.affMsg("Erreur de màj");
        List<Rayon> rayons = model.getRayons();
        view.setListDatas(rayons);
    }

    public void search(String code){
        Rayon ray = model.readRayon(code);
        if(ray==null) view.affMsg("Recherche infructueuse ");
        else view.affMsg(toString());
    }

    public void listerExemplaires(){
        List<Exemplaire> lExemplaire = ((SpecialRayon)model).listerExemplaires();
        if(lExemplaire==null || lExemplaire.isEmpty()) view.affMsg("Aucun exemplaire trouvé ");
        else view.affList(lExemplaire);
    }
}
