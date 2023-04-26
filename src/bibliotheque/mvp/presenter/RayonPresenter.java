package bibliotheque.mvp.presenter;

import bibliotheque.metier.*;
import bibliotheque.mvp.model.DAORayon;
import bibliotheque.mvp.model.SpecialRayon;
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

    public void start() throws Exception {
        List<Rayon> rayons = model.getRayons();
        view.setListDatas(rayons);
    }

    public List<Rayon> getAll(){
        return model.getRayons();
    }

    public void addRayon(Rayon rayon) throws Exception {
        Rayon ray = model.add(rayon);
        if(rayon!=null) view.affMsg("Création de :"+ray);
        else view.affMsg("Erreur de création");
        List<Rayon> rayons = model.getRayons();
        view.setListDatas(rayons);
    }


    public void removeRayon(Rayon rayon) throws Exception {
        boolean ok = model.remove(rayon);
        if(ok) view.affMsg("Rayon effacé");
        else view.affMsg("Rayon non effacé");
        List<Rayon> rayons = model.getRayons();
        view.setListDatas(rayons);
    }

    //Méthode màj à développer
    public void updateRayon(Rayon ray) throws Exception {
        Rayon lRayon = model.update(ray);
        if(lRayon!=null) view.affMsg("Mise à jour de :"+lRayon);
        else view.affMsg("Erreur de màj");
        List<Rayon> rayons = model.getRayons();
        view.setListDatas(rayons);
    }

    public void search(Rayon rayon){
        Rayon ray = model.read(rayon.getCodeRayon());
        if(ray==null) view.affMsg("Recherche infructueuse ");
        else view.affMsg(toString());
    }

    public void listerExemplaires(){
        List<Exemplaire> lExemplaire = ((SpecialRayon)model).listerExemplaires();
        if(lExemplaire==null || lExemplaire.isEmpty()) view.affMsg("Aucun exemplaire trouvé ");
        else view.affList(lExemplaire);
    }
}
