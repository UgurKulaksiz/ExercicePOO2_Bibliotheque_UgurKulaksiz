package bibliotheque.mvp.model;

import bibliotheque.metier.Ouvrage;
import bibliotheque.metier.Rayon;

import java.util.ArrayList;
import java.util.List;

public class OuvrageModel implements DAOOuvrage{

    private int numOuvr =0;
    private List<Ouvrage> ouvrages = new ArrayList<>();

    @Override
    public Ouvrage add(Ouvrage ouvrage) {
        boolean present = ouvrages.contains(ouvrage);
        if(!present){
            ouvrage.setTitre(ouvrage.getTitre());
            ouvrages.add(ouvrage);
            return ouvrage;
        }
        else return null;
    }

    @Override
    public boolean remove(Ouvrage ouvrage) {
        return ouvrages.remove(ouvrage);
    }

    @Override
    public Ouvrage read(Ouvrage ouvrage) {
        for (Ouvrage ouv : ouvrages) {
            if (ouv.getTitre().equals(ouvrage.getTitre())) return ouv;
        }
        return null;
    }

    @Override
    public Ouvrage update(Ouvrage ouvrage) {
        boolean present= ouvrages.contains(ouvrage);
        if(present){
            ouvrage.setTitre(ouvrage.getTitre());
            ouvrage.setAgeMin(ouvrage.getAgeMin());
            ouvrage.setDateParution(ouvrage.getDateParution());
            ouvrage.setTo(ouvrage.getTo());
            ouvrage.setPrixLocation(ouvrage.getPrixLocation());
            ouvrage.setLangue(ouvrage.getLangue());
            ouvrage.setGenre(ouvrage.getGenre());

            ouvrages.set(numOuvr, ouvrage);
            return ouvrage;
        }
        else return null;
    }

    @Override
    public List<Ouvrage> getAll() {
        return new ArrayList<>(ouvrages);
    }

    @Override
    public List<Ouvrage> getOuvrages() {
        return new ArrayList<>(ouvrages);
    }
}
