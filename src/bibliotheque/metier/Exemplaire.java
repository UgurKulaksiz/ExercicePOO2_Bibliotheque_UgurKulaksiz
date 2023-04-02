package bibliotheque.metier;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Exemplaire {

    private String matricule;
    private String descriptionEtat;

    private Ouvrage ouvrage;
    private Rayon rayon;

    private List<Location> lloc = new ArrayList<>();


    public Exemplaire(String matricule, String descriptionEtat, Ouvrage ouvrage) {
        if(matricule == null || matricule.isEmpty() || descriptionEtat == null || descriptionEtat.isEmpty() || ouvrage == null) {
            throw new IllegalArgumentException("Les valeurs passées en paramètre ne doivent pas être null ou vides.");
        }
        this.matricule = matricule;
        this.descriptionEtat = descriptionEtat;
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
        if (this.ouvrage != null) this.ouvrage.getLex().remove(this);
        this.ouvrage = ouvrage;
        this.ouvrage.getLex().add(this);
    }

    public Rayon getRayon() {
        return rayon;
    }

    public void setRayon(Rayon rayon) {
        //S'il y a déjà un ouvrage dans un exemplaire, on le supprime et on ajoute le nouveau ouvrage
        if (this.rayon != null) this.rayon.getLex().remove(this);
        this.rayon = rayon;
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

    public void modifierEtat(String etat) {
        //OKTODO modifier etat exemplaire

        setDescriptionEtat(etat);
    }

    public Lecteur lecteurActuel() {
        //OKTODO lecteur actuel exemplaire

        if(enLocation()){
            return lloc.get(lloc.size()-1).getLoueur();
        }else{
            return null;
        }
    }

    public List<Lecteur> lecteurs() {
        //OKTODO lecteurs exemplaire

        List<Lecteur> lLecteur = new ArrayList<>();
        for (Location location : lloc) {
            if(lLecteur.contains(location)){
                continue; //Par la suite utiliser 'set'
            }
            lLecteur.add(location.getLoueur());
        }

        return null;
    }

    public void envoiMailLecteurActuel(Mail mail) {
        //OKTODO envoi mail lecteur exemplaire

        if(lecteurActuel() != null)
            System.out.println("Envoi de "+mail+" à "+lecteurActuel().getMail());
        else
            System.out.println("Aucune location en cours ");

    }

    public void envoiMailLecteurs(Mail mail) {
        //OKTODO envoi mail lecteurs exemplaire
        //Faire un println du contenu du mail

        List<Lecteur> lLecteur = lecteurs();
        if(lLecteur.isEmpty()){
            System.out.println("Aucun lecteur enregistré ");
        }else{
            for (Lecteur lecteur : lLecteur){
                System.out.println("Envoi de "+mail+" à "+lecteur.getMail());
            }
        }

    }

    public boolean enRetard() { //par retard on entend pas encore restitué et en retard
        //OKTODO enretard exemplaire

        if(lloc.isEmpty())
            return false;
        Location location = lloc.get(lloc.size()-1); //la location en cours est la dernière  de la liste, sauf si elle est terminée

        if(location.getDateRestitution()==null && location.getDateLocation().plusDays(ouvrage.njlocmax()).isAfter(LocalDate.now()))
                return true;

        return false;
    }

    public int joursRetard() {
        //OKTODO jours retard exemplaire

        if(!enRetard())
            return 0;
        Location location = lloc.get(lloc.size()-1); //la location en cours est la dernière de la liste
        LocalDate dateLimite = location.getDateLocation().plusDays(ouvrage.njlocmax());
        int njretard = (int) ChronoUnit.DAYS.between(dateLimite, LocalDate.now());

        return njretard;
    }

    public boolean enLocation() {
        //OKTODO en location exemplaires

        if(lloc.isEmpty())
            return false;
        Location location = lloc.get(lloc.size()-1); //la location en cours est la dernière de la liste
        if(location.getDateRestitution() == null)
            return true;

        return false;
    }


}
