package bibliotheque.metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Exemplaire {

    private String matricule;
    private String descriptionEtat;

    private Ouvrage ouvrage;
    private Rayon rayon;

    private List<Location> lloc= new ArrayList<>();


    public Exemplaire(String matricule, String descriptionEtat,Ouvrage ouvrage) {
        this.matricule = matricule;
        this.descriptionEtat=descriptionEtat;
        this.ouvrage = ouvrage;
        this.ouvrage.getLex().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exemplaire that = (Exemplaire) o;
        return Objects.equals(matricule, that.matricule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricule);
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getDescriptionEtat() {
        return descriptionEtat;
    }

    public void setDescriptionEtat(String descriptionEtat) {
        this.descriptionEtat = descriptionEtat;
    }

     public Ouvrage getOuvrage() {
        return ouvrage;
    }

    public void setOuvrage(Ouvrage ouvrage) {
        if(this.ouvrage!=null) this.ouvrage.getLex().remove(this);
        this.ouvrage = ouvrage;
        this.ouvrage.getLex().add(this);
    }

    public Rayon getRayon() {
        return rayon;
    }

    public void setRayon(Rayon rayon) {
        //S'il y a déjà un ouvrage dans un exemplaire, on le supprime et on ajoute le nouveau ouvrage
        if(this.rayon!=null) this.rayon.getLex().remove(this);
        this.rayon=rayon;
        this.rayon.getLex().add(this);
    }

    public List<Location> getLloc() {
        return lloc;
    }

    public void setLloc(List<Location> lloc) {
        this.lloc = lloc;
    }

    @Override
    public String toString() {
        return "Exemplaire{" +
                "matricule='" + matricule + '\'' +
                ", descriptionEtat='" + descriptionEtat + '\'' +
                ", ouvrage=" + ouvrage +
                ", rayon=" + rayon +
                '}';
    }

    public void modifierEtat(String etat){
        //TODO modifier etat exemplaire

    }

    public Lecteur lecteurActuel(){
        //TODO lecteur actuel exemplaire
        List<Lecteur> lLecteur = new ArrayList<>();
        for (Location location : lloc) {
            //if(location.getDateRestitution().isBef)
            lLecteur.add(location.getLoueur());
        }

        return lLecteur;
    }
    public List<Lecteur> lecteurs(){
        //TODO lecteurs exemplaire
        List<Lecteur> lLecteur = new ArrayList<>();
        for (Location location : lloc) {
            lLecteur.add(location.getLoueur());
        }

        return lLecteur;
    }

    public void envoiMailLecteurActuel(Mail mail){
        //TODO envoi mail lecteur exemplaire

    }
    public void envoiMailLecteurs(Mail mail){
        //TODO envoi mail lecteurs exemplaire
        //Faire un println du contenu du mail

    }

    public boolean enRetard(){
        //TODO enretard exemplaire

        return false;
    }

    public int joursRetard(){
        //TODO jours retard exemplaire

        return 0;
    }


    public boolean enLocation(){
        //TODO en location exemplaires

        return false;
    }



}
