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
    public Rayon add(Rayon ray) {
        boolean present= rayons.contains(ray);
        if(!present){
            rayons.add(ray);
            return ray;
        }
        else return null;
    }

    @Override
    public boolean remove(Rayon ray) {
        return rayons.remove(ray);
    }

    @Override
    public Rayon read(Rayon rayon) {
        for (Rayon ray : rayons) {
            if (ray.getCodeRayon().equals(rayon.getCodeRayon())) return ray;
        }
        return null;
    }

    @Override
    public Rayon update(Rayon ray) {
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
    public List<Rayon> getAll() {
        return new ArrayList<>(rayons);
    }

    @Override
    public List<Rayon> getRayons() {
        return new ArrayList<>(rayons);
    }
}
