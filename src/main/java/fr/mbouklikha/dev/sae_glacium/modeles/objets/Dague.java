package fr.mbouklikha.dev.sae_glacium.modeles.objets;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Terrain;

public class Dague extends Outil {

    public Dague(Terrain terrain, Inventaire inventaire, Sid sid){
        super("dague", terrain, inventaire, sid);
    }

    public void fonction(int x, int y){

    }
}
