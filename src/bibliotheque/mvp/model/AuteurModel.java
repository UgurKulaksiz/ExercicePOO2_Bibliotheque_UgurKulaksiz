package bibliotheque.mvp.model;


import bibliotheque.metier.Auteur;

import java.util.ArrayList;
import java.util.List;


public class AuteurModel implements DAOAuteur {
    private List<Auteur> auteurs = new ArrayList<>();

    @Override
    public Auteur add(Auteur auteur) {
        boolean present = auteurs.contains(auteur);
        if (!present) {
            auteurs.add(auteur);
            return auteur;
        } else {
            return null;
        }
    }

    @Override
    public boolean remove(Auteur auteur) {
        return auteurs.remove(auteur);
    }

    @Override
    public Auteur update(Auteur auteur) {
        boolean present = auteurs.contains(auteur);
        if (present) {
            auteurs.set(auteurs.indexOf(auteur), auteur);
            return auteur;
        } else {
            return null;
        }
    }

    @Override
    public Auteur read(Auteur elementToSearch) {
        for (Auteur auteur : auteurs) {
            if (auteur.equals(elementToSearch)) {
                return auteur;
            }
        }
        return null;
    }

    @Override
    public List<Auteur> getAll() {
        return new ArrayList<>(auteurs);
    }

    @Override
    public List<Auteur> getAuteurs() {
        return new ArrayList<>(auteurs);
    }
}
