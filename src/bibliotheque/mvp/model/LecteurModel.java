package bibliotheque.mvp.model;

import bibliotheque.metier.Lecteur;

import java.util.ArrayList;
import java.util.List;

public class LecteurModel implements DAOLecteur{
    private int numLec =0;
    private List<Lecteur> lecteurs = new ArrayList<>();

    @Override
    public Lecteur add(Lecteur lec) {
        boolean present= lecteurs.contains(lec);
        if(!present){
            numLec++;
            lec.setNumlecteur(numLec);
            lecteurs.add(lec);
            return lec;
        }
        else return null;
    }

    @Override
    public boolean remove(Lecteur lec) {
        return lecteurs.remove(lec);
    }

    @Override
    public Lecteur read(Lecteur lec) {
        for (Lecteur l : lecteurs) {
            if (l.getNumlecteur() == lec.getNumlecteur()) return l;
        }
        return null;
    }


    @Override
    public Lecteur update(Lecteur lec) {
        boolean present= lecteurs.contains(lec);
        if(present){
            lec.setNom(lec.getNom());
            lec.setPrenom(lec.getPrenom());
            lec.setDn(lec.getDn());
            lec.setAdresse(lec.getAdresse());
            lec.setMail(lec.getMail());
            lec.setTel(lec.getTel());
            lecteurs.set(numLec, lec);
            return lec;
        }
        else return null;
    }

    @Override
    public List<Lecteur> getAll() {
        return new ArrayList<>(lecteurs);
    }

    @Override
    public List<Lecteur> getLecteurs() {
        return new ArrayList<>(lecteurs);
    }
}
