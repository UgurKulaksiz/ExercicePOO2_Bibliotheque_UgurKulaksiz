package bibliotheque.mvp.view;

import bibliotheque.metier.*;


import static bibliotheque.utilitaires.Utilitaire.*;

public class RayonViewConsole extends AbstractViewConsole {
    @Override
    protected void rechercher() {
        System.out.println("code du rayon : ");
        String code = sc.nextLine();
        Rayon rech = new Rayon(code);
        presenter.search(rech);
    }

    @Override
    protected void modifier() {

    }

    @Override
    protected void ajouter() {
        Rayon r =null;
        do {
            try {
                System.out.println("code rayon ");
                String code = sc.nextLine();
                System.out.println("genre ");
                String genre = sc.nextLine();
                r = new Rayon(code, genre);
                presenter.add(r);
                ldatas=presenter.getAll();//rafraichissement
                affListe(ldatas);
                break;
            }
            catch (Exception e){
                System.out.println("une erreur est survenue : "+e);
            }
        }while(true);

    }

    @Override
    protected void special() {

    }
}
