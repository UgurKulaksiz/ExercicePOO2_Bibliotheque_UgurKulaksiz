package bibliotheque.mvp.model;

import bibliotheque.metier.Exemplaire;
import bibliotheque.metier.Rayon;

import java.util.List;

public interface SpecialRayon {
    List<Exemplaire> listerExemplaires();
}
