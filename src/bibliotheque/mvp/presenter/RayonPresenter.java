package bibliotheque.mvp.presenter;

import bibliotheque.metier.*;
import bibliotheque.mvp.model.DAO;
import bibliotheque.mvp.model.DAORayon;
import bibliotheque.mvp.model.SpecialRayon;
import bibliotheque.mvp.view.ViewInterface;

import java.util.List;

public class RayonPresenter extends Presenter<Rayon> {
    public RayonPresenter(DAO<Rayon> model, ViewInterface<Rayon> view) {
        super(model, view);
    }

}
