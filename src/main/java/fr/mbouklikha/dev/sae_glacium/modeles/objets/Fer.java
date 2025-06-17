package fr.mbouklikha.dev.sae_glacium.modeles.objets;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Terrain;

public class Fer extends Ressource {

    public Fer(Terrain terrain, Inventaire inventaire, Sid sid){
        super("fer", terrain, inventaire, sid);
    }

    public void fonction(int x, int y){

    }
}
