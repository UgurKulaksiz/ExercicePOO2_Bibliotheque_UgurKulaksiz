package bibliotheque.mvp.model;

import bibliotheque.metier.Exemplaire;
import bibliotheque.metier.Lecteur;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import static bibliotheque.utilitaires.Utilitaire.getDate;

public class LecteurModel extends AbstractModel<Lecteur> implements SpecialLecteur{
    private int numLecteur = 0;
    private List<Lecteur> lecteurs = new ArrayList<>();

    private Map<String,Lecteur> hml= new HashMap<>();

    public Lecteur add(Lecteur nl){
        Lecteur l = super.add(nl);
        if(l!=null) {
            l.setNumlecteur(++numLecteur);
            hml.put(l.getMail(),l);
        }
        return  l;
    }

    @Override
    public List<Exemplaire> exemplairesEnLocation(Lecteur l) {
        return l.listerExemplairesEnLocation();
    }

    @Override
    public List<Exemplaire> exemplairesLoues(Lecteur l) {
        return new ArrayList<>(l.listerExemplairesLoues());
    }

    @Override
    public Lecteur lecParMail(String mail) {
        return hml.get(mail);
    }

    /* Enoncé V8 :
    3.Créez manuellement(avec notepad) un fichier "nouveaux_lecteurs.txt" contenant
    les informations d'une série de nouveaux lecteurs.
     */
    @Override
    public void chargementParFichier(){
        File f = new File("d:/nouveauxLecteurs.txt");
        try(FileReader fr = new FileReader(f)){
            Scanner sc = new Scanner(fr);
            while(sc.hasNext()){ //sc.hasNext() --> Tant qu'il lit le fichier
                String ligne =sc.nextLine();
                String[] infos = ligne.split(":");
                String nom=infos[0];
                String prenom=infos[1];
                String dns=infos[2];
                //Transformer le String (dns) en LocalDate (dn) car dans le constructeur du Lecteur c'est de type LocalDate
                LocalDate dn = getDate(dns);
                String adresse=infos[3];
                String mail=infos[4];
                String tel=infos[5];
                add(new Lecteur(0,nom,prenom,dn,adresse,mail,tel));
            }
        } catch (FileNotFoundException e) {
            //TODO gérer exception
        } catch (IOException e) {
            //TODO gérer exception
        }
        catch(Exception e){
            //TODO gérer exception
        }
    }
}
