package bibliotheque.mvp.presenter;

import bibliotheque.metier.Auteur;
import bibliotheque.metier.Exemplaire;
import bibliotheque.metier.Ouvrage;
import bibliotheque.mvp.model.DAO;
import bibliotheque.mvp.model.SpecialOuvrage;
import bibliotheque.mvp.model.SpecialRayon;
import bibliotheque.mvp.view.ViewInterface;

import java.util.Comparator;
import java.util.List;

public class OuvragePresenter extends Presenter<Ouvrage> implements SpecialOuvragePresenter {
    private Presenter<Auteur> auteurPresenter;
    @Override
    public void setAuteurPresenter(Presenter<Auteur> auteurPresenter) {
        this.auteurPresenter = auteurPresenter;
    }

    @Override
    public Auteur choixAuteur(){
        return  auteurPresenter.selection();
    }

    public OuvragePresenter(DAO<Ouvrage> model, ViewInterface<Ouvrage> view, Comparator<Ouvrage> cmp) {
        super(model,view, cmp);
    }

    @Override
    public void  listerExemplaire(Ouvrage o){
        view.affList(((SpecialOuvrage)model).listerExemplaire(o));
    }
    @Override
    public void listerExemplaire(Ouvrage o, boolean enLocation){
        view.affList(((SpecialOuvrage)model).listerExemplaire(o,enLocation));
    }

}
