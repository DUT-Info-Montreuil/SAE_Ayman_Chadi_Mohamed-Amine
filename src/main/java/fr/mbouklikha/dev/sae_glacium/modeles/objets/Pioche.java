package fr.mbouklikha.dev.sae_glacium.modeles.objets;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Terrain;

public class Pioche extends Outil {

    public Pioche(Terrain terrain, Inventaire inventaire,Sid sid){
        super("pioche", terrain, inventaire,sid);
    }

    public void fonction(int x, int y){
            int[][] map = getTerrain().getMap();
            if (map[y][x] == 1) {
                map[y][x] = -1;
                // Plus tard : ajouter à l'inventaire
                getInventaire().ajouterItem(new Neige(getTerrain(), getInventaire(), getSid()));

                getTerrain().mettreAJourHitboxBlocsSolides();
                System.out.println("Bloc cassé");
            } else if (map[y][x] == 2){
                map[y][x] = -1;
                // Plus tard : ajouter à l'inventaire
                getInventaire().ajouterItem(new Glace(getTerrain(), getInventaire(), getSid()));

                getTerrain().mettreAJourHitboxBlocsSolides();
                System.out.println("Bloc cassé");
            }
    }
}
