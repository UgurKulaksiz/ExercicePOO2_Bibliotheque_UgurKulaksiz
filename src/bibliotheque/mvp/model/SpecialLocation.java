package bibliotheque.mvp.model;

import bibliotheque.metier.Location;

public interface SpecialLocation {
    public double calculerAmende(Location l);
    public void enregistrerRetour(Location l);
}
