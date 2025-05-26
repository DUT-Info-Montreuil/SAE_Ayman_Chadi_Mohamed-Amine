package fr.mbouklikha.dev.sae_glacium.modeles.objets;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Item {
    private String nom;
    private IntegerProperty quantite;
    private int x;
    private int y;

    public Item(String nom, int x, int y) {
        this.nom = nom;
        this.quantite = new SimpleIntegerProperty(1);
        this.x = x;
        this.y = y;
    }

    public String getNom() {
        return nom;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void ajouter(int nb) {
        this.quantite.set(this.quantite.get() + nb);
    }

    public int getQuantite() {
        return quantite.get();
    }

    public IntegerProperty quantiteProperty() {
        return quantite;
    }
}
