package fr.mbouklikha.dev.sae_glacium.modeles.objets;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Acteur;
import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Terrain;

import java.util.ArrayList;

public abstract class Objets {
    private String nom;
    private Terrain terrain;
    private Inventaire inventaire;
    private Sid sid;

    public Objets(String nom, Terrain terrain, Inventaire inventaire, Sid sid) {
        this.nom = nom;
        this.terrain = terrain;
        this.inventaire = inventaire;
        this.sid = sid;
    }

    public String getNom() {
        return nom;
    }

    public abstract Item creerItem();

    public Terrain getTerrain(){
        return this.terrain;
    }

    public Inventaire getInventaire(){
        return this.inventaire;
    }

    public Sid getSid(){
        return sid;
    }



    public abstract void fonction(int x, int y);
}
