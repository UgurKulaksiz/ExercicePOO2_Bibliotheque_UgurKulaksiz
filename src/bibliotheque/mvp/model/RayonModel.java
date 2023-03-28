package bibliotheque.mvp.model;

import bibliotheque.metier.Exemplaire;
import bibliotheque.metier.Lecteur;
import bibliotheque.metier.Rayon;

import java.util.ArrayList;
import java.util.List;

public class RayonModel implements DAORayon{
    private int numRayon =0;
    private List<Rayon> rayons = new ArrayList<>();

    @Override
    public Rayon addRayon (Rayon ray) {
        boolean present= rayons.contains(ray);
        if(!present){
            rayons.add(ray);
            return ray;
        }
        else return null;
    }

    @Override
    public boolean removeRayon(Rayon ray) {
        return rayons.remove(ray);
    }

    @Override
    public Rayon readRayon(String code) {
        for (Rayon ray : rayons) {
            if (ray.getCodeRayon().equals(code)) return ray;
        }
        return null;
    }

    @Override
    public Rayon updateRayon(Rayon ray) {
        boolean present= rayons.contains(ray);
        if(present){
            ray.setCodeRayon(ray.getCodeRayon());
            ray.setGenre(ray.getGenre());

            rayons.set(numRayon, ray);
            return ray;
        }
        else return null;
    }

    @Override
    public List<Rayon> getRayons() {
        return new ArrayList<>(rayons);
    }
}
