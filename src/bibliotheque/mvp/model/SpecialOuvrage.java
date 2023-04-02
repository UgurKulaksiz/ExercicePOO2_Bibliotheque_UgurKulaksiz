package bibliotheque.mvp.model;

import bibliotheque.metier.Exemplaire;

import java.util.List;

public interface SpecialOuvrage {
    public List<Exemplaire> listerExemplaires();
    public List<Exemplaire> listerExemplaires(Boolean enLocation);
    public abstract double amendeRetard(int njours);
}
