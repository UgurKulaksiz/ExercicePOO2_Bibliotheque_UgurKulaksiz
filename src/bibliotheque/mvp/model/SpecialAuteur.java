package bibliotheque.mvp.model;

import bibliotheque.metier.Ouvrage;
import bibliotheque.metier.TypeLivre;
import bibliotheque.metier.TypeOuvrage;

import java.util.List;

public interface SpecialAuteur {
    public List<Ouvrage> listerOuvrages();
    public List<Ouvrage> listerOuvrages(TypeOuvrage to, TypeLivre tl);
    public List<Ouvrage> listerOuvrage(String genre);
}
