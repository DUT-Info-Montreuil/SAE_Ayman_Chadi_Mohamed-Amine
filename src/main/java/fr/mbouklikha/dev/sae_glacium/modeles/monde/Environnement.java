package fr.mbouklikha.dev.sae_glacium.modeles.monde;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Acteur;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class Environnement {

    private int width, height;
    private ArrayList<Acteur> acteurs;
    private IntegerProperty nbToursProperty;

    public Environnement(int width, int height) {
        this.width = width;
        this.height = height;
        this.nbToursProperty = new SimpleIntegerProperty(0);
        this.acteurs = new ArrayList<>();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ArrayList<Acteur> getActeurs() {
        return acteurs;
    }

    public void ajouterActeur(Acteur a) {
        acteurs.add(a);
    }

    public boolean estDansLimite(int x, int y) {
        return (0 <= x && x < this.width && 0 <= y && y < this.height);
    }

    public void effectuerUnTour() {
        for (Acteur a : acteurs) {
            if (a.estVivant()) {
                // Optionnel : ajouter une méthode agit() si tu veux du comportement automatique
                // a.agit();
            }
        }
        setNbTours(getNbTours() + 1);
    }

    public int getNbTours() {
        return nbToursProperty.get();
    }

    public void setNbTours(int n) {
        nbToursProperty.set(n);
    }

    public IntegerProperty nbToursProperty() {
        return nbToursProperty;
    }
}
