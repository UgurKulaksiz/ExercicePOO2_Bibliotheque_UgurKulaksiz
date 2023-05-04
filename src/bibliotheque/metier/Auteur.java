package bibliotheque.metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Auteur {
    private String nom, prenom;
    private String nationalite;
    private List<Ouvrage> louvrage = new ArrayList<>();

    public Auteur(String nom, String prenom, String nationalite) throws Exception {
        if(nom==null || nom.trim().equals("")) throw new Exception ("nom vide");

        this.nom = nom;
        this.prenom = prenom;
        this.nationalite = nationalite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public List<Ouvrage> getLouvrage() {
        return louvrage;
    }

    public void setLouvrage(List<Ouvrage> louvrage) {
        this.louvrage = louvrage;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auteur auteur = (Auteur) o;
        return Objects.equals(nom, auteur.nom) && Objects.equals(prenom, auteur.prenom) && Objects.equals(nationalite, auteur.nationalite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, prenom, nationalite);
    }

    @Override
    public String toString() {
        return "Auteur{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", nationalite='" + nationalite + '\'' +
                '}';
    }

    public void addOuvrage(Ouvrage ouv) {
        louvrage.add(ouv);
        ouv.getLauteurs().add(this);
    }

    public void remove(Ouvrage ouv) {
        louvrage.remove(ouv);
        ouv.getLauteurs().remove(this);
    }

    public List<Ouvrage> listerOuvrages() {
        //OKTODO lister ouvrages

        return louvrage;
    }

    public List<Ouvrage> listerOuvrages(TypeOuvrage to) {
        //OKTODO lister ouvrages d'un type

        List<Ouvrage> louv = new ArrayList<>();
        for (Ouvrage ouvrage : louvrage) {
            if (ouvrage.getTo().equals(to)) {
                louv.add(ouvrage);
            }
        }

        return louv;
    }

    public List<Livre> listerLivres(TypeLivre tl) {
        //OKTODO lister livres d'un type

        List<Livre> lLivre = new ArrayList<>();
        for (Ouvrage ouvrage : louvrage) {
            if (ouvrage.getTo().equals(TypeOuvrage.LIVRE)) {
                Livre l = (Livre) ouvrage;
                if(l.getTl().equals(tl)){
                    lLivre.add(l);
                }
            }
        }

        return lLivre;
    }

    public List<Ouvrage> listerOuvrages(String genre) {
        //OKTODO lister ouvrages d'un genre

        List<Ouvrage> louv = new ArrayList<>();
        for (Ouvrage ouvrage : louvrage) {
            if (ouvrage.getGenre().equals(genre)) {
                louv.add(ouvrage);
            }
        }

        return louv;
    }
}
