package bibliotheque.metier;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Ouvrage {
    protected String titre;
    protected int ageMin;
    protected LocalDate dateParution;
    protected TypeOuvrage to;
    protected double prixLocation;
    protected String langue;
    protected String genre;

    protected List<Auteur> lauteurs=new ArrayList<>();
    protected List<Exemplaire> lex = new ArrayList<>();


    public Ouvrage(String titre, int ageMin, LocalDate dateParution, TypeOuvrage to, double prixLocation, String langue, String genre) {
        if(titre.isEmpty() || ageMin <= 0 || dateParution == null || to == null || prixLocation <= 0 || langue.isEmpty() || genre.isEmpty()) {
            throw new IllegalArgumentException("Les valeurs passées en paramètre ne sont pas valides.");
        }
        this.titre = titre;
        this.ageMin = ageMin;
        this.dateParution = dateParution;
        this.to = to;
        this.prixLocation = prixLocation;
        this.langue = langue;
        this.genre = genre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(int ageMin) {
        this.ageMin = ageMin;
    }

    public LocalDate getDateParution() {
        return dateParution;
    }

    public void setDateParution(LocalDate dateParution) {
        this.dateParution = dateParution;
    }

    public TypeOuvrage getTo() {
        return to;
    }

    public void setTo(TypeOuvrage to) {
        this.to = to;
    }

    public double getPrixLocation() {
        return prixLocation;
    }

    public void setPrixLocation(double prixLocation) {
        this.prixLocation = prixLocation;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Auteur> getLauteurs() {
        return lauteurs;
    }

    public void setLauteurs(List<Auteur> lauteurs) {
        this.lauteurs = lauteurs;
    }

    public List<Exemplaire> getLex() {
        return lex;
    }

    public void setLex(List<Exemplaire> lex) {
        this.lex = lex;
    }


    @Override
    public String toString() {
        return "Ouvrage{" +
                "titre='" + titre + '\'' +
                ", ageMin=" + ageMin +
                ", dateParution=" + dateParution +
                ", to=" + to +
                ", prixLocation=" + prixLocation +
                ", langue='" + langue + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }

    public abstract double amendeRetard(int njours);

    public abstract int njlocmax();


    public void addAuteur(Auteur aut){
        lauteurs.add(aut);
        aut.getLouvrage().add(this);
    }

    public void remove(Auteur aut){
        lauteurs.remove(aut);
        aut.getLouvrage().remove(this);
    }
    public void addExemplaire(Exemplaire exemp){
        lex.add(exemp);
        exemp.setOuvrage(this);
    }

    public void remove(Exemplaire exemp){
        lex.remove(exemp);
        exemp.setOuvrage(null);
    }
    public List<Exemplaire>listerExemplaires(){
        //OKTODO lister exemplaires ouvrage

        return lex;
    }

    public List<Exemplaire>listerExemplaires(boolean enLocation){
        //OKTODO lister exemplaires ouvrage en location

        List<Exemplaire> lex2 = new ArrayList<>();
        for (Exemplaire exemplaire : lex) {
            if(exemplaire.enLocation() == enLocation){
                lex2.add(exemplaire);
            }
        }

        return lex2;
    }
}
