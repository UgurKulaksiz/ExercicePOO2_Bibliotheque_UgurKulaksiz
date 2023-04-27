package bibliotheque.mvp.presenter;

import bibliotheque.metier.*;
import bibliotheque.mvp.model.DAO;
import bibliotheque.mvp.model.SpecialAuteur;
import bibliotheque.mvp.view.ViewInterface;

import java.util.List;

public class AuteurPresenter extends Presenter<Auteur> {
    public AuteurPresenter(DAO<Auteur> model, ViewInterface<Auteur> view) {
        super(model, view);
    }
}
