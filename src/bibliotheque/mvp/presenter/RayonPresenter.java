package bibliotheque.mvp.presenter;

import bibliotheque.metier.*;
import bibliotheque.mvp.model.DAO;
import bibliotheque.mvp.model.DAORayon;
import bibliotheque.mvp.model.SpecialRayon;
import bibliotheque.mvp.view.ViewInterface;

import java.util.Comparator;
import java.util.List;

public class RayonPresenter extends Presenter<Rayon> implements SpecialRayonPresenter {
    public RayonPresenter(DAO<Rayon> model, ViewInterface<Rayon> view, Comparator<Rayon> cmp) {
        super(model, view, cmp);
    }

    @Override
    public void listerExemplaires(Rayon r){
        view.affList(((SpecialRayon)model).listerExemplaires(r));
    }

}
