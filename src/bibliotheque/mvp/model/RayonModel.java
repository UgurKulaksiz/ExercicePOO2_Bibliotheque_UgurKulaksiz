package bibliotheque.mvp.model;

import bibliotheque.metier.Exemplaire;
import bibliotheque.metier.Rayon;

import java.util.List;

public class RayonModel extends AbstractModel<Rayon> implements SpecialRayon {
    @Override
    public List<Exemplaire> listerExemplaires(Rayon r) {
        /* Appel de la méthode listerExemplaires() qui se trouve dans la classe métier Rayon */
        return r.listerExemplaires();
    }
}
