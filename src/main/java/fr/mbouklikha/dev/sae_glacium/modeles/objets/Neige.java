package fr.mbouklikha.dev.sae_glacium.modeles.objets;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Terrain;

public class Neige extends Ressource {

    public Neige(Terrain terrain, Inventaire inventaire, Sid sid){
        super("neige", terrain, inventaire, sid);
    }

    public void fonction(int x, int y){
        int[][] map = getTerrain().getMap();
        if (map[y][x] == -1) {
            map[y][x] = 1;
            // Plus tard : retirer du bon slot d'inventaire
            getInventaire().retirerUnItem(getSid().getObjetEnMain());
            getTerrain().mettreAJourHitboxBlocsSolides();
            System.out.println("Bloc posé");
        }

    }
}
