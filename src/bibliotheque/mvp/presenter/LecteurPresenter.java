package bibliotheque.mvp.presenter;

import bibliotheque.metier.Exemplaire;
import bibliotheque.metier.Lecteur;
import bibliotheque.mvp.model.DAOLecteur;
import bibliotheque.mvp.model.SpecialLecteur;
import bibliotheque.mvp.view.LecteurViewInterface;

import java.util.List;

public class LecteurPresenter {
    private DAOLecteur model;
    private LecteurViewInterface view;

    public LecteurPresenter(DAOLecteur model, LecteurViewInterface view) {
        this.model = model;
        this.view = view;
        this.view.setPresenter(this);
    }

    public void start() throws Exception {
        view.setListDatas(getAll());
    }

    public List<Lecteur> getAll() {
        return model.getLecteurs();
    }

    public void addLecteur(Lecteur lecteur) throws Exception {
        Lecteur lec = model.add(lecteur);
        if (lec != null) view.affMsg("création de :" + lec);
        else view.affMsg("erreur de création");
        List<Lecteur> lecteurs = model.getLecteurs();
        view.setListDatas(lecteurs);
        // List<Lecteur> lecteurs = model.getLecteurs();
        // view.setListDatas(lecteurs); //désactivé pour éviter appels imbriqués
    }


    public void removeLecteur(Lecteur lecteur) throws Exception {
        boolean ok = model.remove(lecteur);
        if (ok) view.affMsg("lecteur effacé");
        else view.affMsg("lecteur non effacé");
        List<Lecteur> lecteurs = model.getLecteurs();
        view.setListDatas(lecteurs);
        //List<Lecteur> lecteurs = model.getLecteurs();
        //view.setListDatas(lecteurs); //désactivé pour éviter appels imbriqués
    }

    //Méthode màj à développer
    public void updateLecteur(Lecteur lec) throws Exception {
        Lecteur lLecteur = model.update(lec);
        if (lLecteur != null) view.affMsg("Mise à jour de :" + lLecteur);
        else view.affMsg("Erreur de màj");
        List<Lecteur> lecteurs = model.getLecteurs();
        view.setListDatas(lecteurs);
        //view.setListDatas(model.getClients());//désactivé pour éviter appels imbriqués
    }

    public void search(Lecteur idLecteur) {
        Lecteur lec = model.read(idLecteur);
        if (lec == null) view.affMsg("Recherche infructueuse ");
        else view.affMsg(toString());
    }

    public void exemplairesEnLocation(Lecteur lec) {
        List<Exemplaire> lex = ((SpecialLecteur) model).exemplairesEnLocation(lec);
        if (lex == null || lex.isEmpty()) view.affMsg("Aucun exemplaire trouvé ");
        else view.affList(lex);
    }

    public void exemplairesLoues(Lecteur lec) {
        List<Exemplaire> lex = ((SpecialLecteur) model).exemplairesLoues(lec);
        if (lex == null || lex.isEmpty()) view.affMsg("Aucun exemplaire trouvé ");
        else view.affList(lex);
    }
}
