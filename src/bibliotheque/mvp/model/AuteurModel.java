package bibliotheque.mvp.model;


import bibliotheque.metier.Auteur;

import java.util.ArrayList;
import java.util.List;

public class AuteurModel implements DAOAuteur{
    private List<Auteur> auteurs = new ArrayList<>();

    @Override
    public Auteur addAuteur(Auteur aut) {
        boolean present= auteurs.contains(aut);
        if(!present){
            auteurs.add(aut);
            return aut;
        }
        else return null;
    }

    @Override
    public boolean removeAuteur(Auteur aut) {
        return auteurs.remove(aut);
    }

    @Override
    public Auteur updateAuteur(Auteur aut) {
        boolean present= auteurs.contains(aut);
        if(present){
            aut.setNom(aut.getNom());
            aut.setPrenom(aut.getPrenom());
            aut.setNationalite(aut.getNationalite());
            aut.setLouvrage(aut.getLouvrage());

            return aut;
        }
        else return null;
    }

    @Override
    public List<Auteur> getAuteurs() {
        return new ArrayList<>(auteurs);
    }
}
