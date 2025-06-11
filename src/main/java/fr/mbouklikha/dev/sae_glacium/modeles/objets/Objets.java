package fr.mbouklikha.dev.sae_glacium.modeles.objets;

import fr.mbouklikha.dev.sae_glacium.modeles.monde.Terrain;

public abstract class Objets {
    private String nom;
    private Terrain terrain;

    public Objets(String nom, Terrain terrain) {
        this.nom = nom;
        this.terrain = terrain;
    }

    public String getNom() {
        return nom;
    }

    public abstract Item creerItem();

    public Terrain getTerrain(){
        return this.terrain;
    }


    public abstract void fonction(int x, int y);
}
