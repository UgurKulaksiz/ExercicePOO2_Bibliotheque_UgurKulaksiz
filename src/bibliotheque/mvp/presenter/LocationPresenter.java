package bibliotheque.mvp.presenter;


import bibliotheque.metier.Exemplaire;
import bibliotheque.metier.Lecteur;
import bibliotheque.metier.Location;
import bibliotheque.mvp.model.DAO;
import bibliotheque.mvp.model.SpecialLocation;
import bibliotheque.mvp.view.ViewInterface;

import java.util.Comparator;


public class LocationPresenter extends Presenter<Location> implements SpecialLocationPresenter {
    private Presenter<Exemplaire> exemplairePresenter;
    private Presenter<Lecteur> lecteurPresenter;


    @Override
    public void setExemplairePresenter(Presenter<Exemplaire> exemplairePresenter) {
        this.exemplairePresenter = exemplairePresenter;
    }

    @Override
    public void setLecteurPresenter(Presenter<Lecteur> lecteurPresenter) {
        this.lecteurPresenter = lecteurPresenter;
    }


    @Override
    public Exemplaire choixExemplaire(){
        return exemplairePresenter.selection();
    }

    @Override
    public Lecteur choixLecteur(){
        return lecteurPresenter.selection();
    }
    public LocationPresenter(DAO<Location> model, ViewInterface<Location> view, Comparator<Location> cmp) {
        super(model,view, cmp);
    }

    @Override
    public void  calculerAmende(Location l){
        view.affMsg("amende = "+((SpecialLocation)model).calculerAmende(l));
    }
    @Override
    public void enregistrerRetour(Location l){
        ((SpecialLocation)model).enregistrerRetour(l);
        view.affMsg("retour enregistré");
    }

}
