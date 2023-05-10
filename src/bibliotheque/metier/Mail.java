package bibliotheque.metier;

import java.io.FileWriter;
import java.io.IOException;

public class Mail {
    private String objet;
    private String message;
    private String dateEnvoi;

    public Mail(String objet, String message, String dateEnvoi) {
        this.objet = objet;
        this.message = message;
        this.dateEnvoi = dateEnvoi;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(String dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "objet='" + objet + '\'' +
                ", message='" + message + '\'' +
                ", dateEnvoi='" + dateEnvoi + '\'' +
                '}';
    }

    /* Enoncé V8 :
    1. Modifiez la classe Mail afin qu'elle comporte une méthode envoi permettant
    de générer un fichier texte dont le nom est l'adresse mail du destinataire
    et dont le contenu correspond à l'objet et au contenu du mail.
     */
    public void envoi(String destinataire) {
        String nomFichier = destinataire + ".txt";
        try {
            FileWriter fichier = new FileWriter(nomFichier);
            fichier.write("Objet : " + objet + "\n");
            fichier.write("Message : " + message + "\n");
            fichier.write("Date Envoi : " + dateEnvoi + "\n");
            fichier.close(); //Fermeture du fichier
            System.out.println("Mail envoyé et le fichier" + nomFichier + " a été généré. ");
        } catch (IOException e) {
            System.out.println("Une erreur s'est produite lors de l'envoi du mail : " + e.getMessage());
        }
    }

}
