package fr.mbouklikha.dev.sae_glacium.modeles.objets;

public abstract class Objets {
    private String nom;

    public Objets(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public abstract Item creerItem();

}
