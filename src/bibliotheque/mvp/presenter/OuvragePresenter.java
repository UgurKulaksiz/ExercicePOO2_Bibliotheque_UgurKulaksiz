package bibliotheque.mvp.presenter;

import bibliotheque.metier.Exemplaire;
import bibliotheque.metier.Ouvrage;
import bibliotheque.mvp.model.DAO;
import bibliotheque.mvp.model.SpecialOuvrage;
import bibliotheque.mvp.model.SpecialRayon;
import bibliotheque.mvp.view.ViewInterface;

import java.util.List;

public class OuvragePresenter extends Presenter<Ouvrage> {
    public OuvragePresenter(DAO<Ouvrage> model, ViewInterface<Ouvrage> view) {
        super(model,view);
    }

}
