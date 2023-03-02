package bibliotheque.metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Auteur {
    private String nom, prenom;
    private String nationalite;
    private List<Ouvrage> louvrage = new ArrayList<>();

    public Auteur(String nom, String prenom, String nationalite) {
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
        //TODO lister ouvrages

        List<Ouvrage> louv = new ArrayList<>();
        for (Ouvrage ouvrage : louv) {
            louv.add(ouvrage);
        }

        return louv;
    }

    public List<Ouvrage> listerOuvrages(TypeOuvrage to) {
        //TODO lister ouvrages d'un type

        List<Ouvrage> louv = new ArrayList<>();
        for (Ouvrage ouvrage : louv) {
            if (ouvrage.getTo().toString().equals(to.toString())) {
                louv.add(ouvrage);
            }
        }

        return louv;
    }

    public List<Livre> listerLivres(TypeLivre tl) {
        //TODO lister livres d'un type

        List<Livre> lLivre = new ArrayList<>();
        for (Livre livre : lLivre) {
            if (livre.getTl().equals(tl.toString())) {
                System.out.println(livre.toString());
            }
        }

        return lLivre;
    }

    public List<Ouvrage> listerOuvrages(String genre) {
        //TODO lister ouvrages d'un genre

        List<Ouvrage> louv = new ArrayList<>();
        for (Ouvrage ouvrage : louv) {
            if (ouvrage.getGenre().equals(genre)) {
                louv.add(ouvrage);
            }
        }

        return louv;
    }
}
