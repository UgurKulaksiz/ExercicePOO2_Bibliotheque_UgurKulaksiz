package bibliotheque.mvp.presenter;

import bibliotheque.metier.Exemplaire;
import bibliotheque.metier.Ouvrage;
import bibliotheque.mvp.model.DAOOuvrage;
import bibliotheque.mvp.model.SpecialOuvrage;
import bibliotheque.mvp.model.SpecialRayon;
import bibliotheque.mvp.view.OuvrageViewInterface;

import java.util.List;

public class OuvragePresenter {
    private DAOOuvrage model;
    private OuvrageViewInterface view;

    public OuvragePresenter(DAOOuvrage model, OuvrageViewInterface view) {
        this.model = model;
        this.view = view;
        this.view.setPresenter(this);
    }

    public void start() {
        List<Ouvrage> ouvrages = model.getOuvrages();
        view.setListDatas(ouvrages);
    }

    public List<Ouvrage> getAll(){
        return model.getOuvrages();
    }


    public void addOuvrage(Ouvrage ouvrage) {
        Ouvrage ouvr = model.addOuvrage(ouvrage);
        if(ouvr!=null) view.affMsg("Création de :"+ouvr);
        else view.affMsg("Erreur de création");
        List<Ouvrage> ouvrages = model.getOuvrages();
        view.setListDatas(ouvrages);
    }


    public void removeOuvrage(Ouvrage ouvrage) {
        boolean ok = model.removeOuvrage(ouvrage);
        if(ok) view.affMsg("Ouvrage effacé");
        else view.affMsg("Ouvrage non effacé");
        List<Ouvrage> ouvrages = model.getOuvrages();
        view.setListDatas(ouvrages);
    }

    //Méthode màj à développer
    public void updateOuvrage(Ouvrage ouvrage){
        Ouvrage lOuvrage = model.updateOuvrage(ouvrage);
        if(lOuvrage!=null) view.affMsg("Mise à jour de :"+lOuvrage);
        else view.affMsg("Erreur de màj");
        List<Ouvrage> ouvrages = model.getOuvrages();
        view.setListDatas(ouvrages);
    }

    public void search(String titre){
        Ouvrage ouv = model.readOuvrage(titre);
        if(ouv==null) view.affMsg("Recherche infructueuse ");
        else view.affMsg(toString());
    }

    public void listerExemplaires(){
        List<Exemplaire> lExemplaire = ((SpecialRayon)model).listerExemplaires();
        if(lExemplaire==null || lExemplaire.isEmpty()) view.affMsg("Aucun exemplaire trouvé ");
        else view.affList(lExemplaire);
    }

    public void listerExemplaires(Boolean enLocation){
        List<Exemplaire> lex = ((SpecialOuvrage)model).listerExemplaires(enLocation);
        if(lex==null || lex.isEmpty()) view.affMsg("Aucun exemplaire trouvé ");
        else view.affList(lex);
    }

    public void  amendeRetard(int njours){
        Double j = ((SpecialOuvrage)model).amendeRetard(njours);
        if(j==null) view.affMsg("Aucun exemplaire trouvé ");
        else view.affList(j);
    }
}
