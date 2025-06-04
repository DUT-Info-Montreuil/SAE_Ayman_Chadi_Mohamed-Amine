package fr.mbouklikha.dev.sae_glacium.controller;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Terrain;
import fr.mbouklikha.dev.sae_glacium.vues.monde.TerrainVue;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;


public class Souris {

    private final Sid sid;
    private final Terrain terrain;
    private final int TAILLE_BLOC = 32;
    private final TerrainVue terrainVue;
    private final TilePane tilePane;
    private final int DISTANCE_MAX = 96; // distance maximale autorisée (3 bloc)

    public Souris(Sid sid, Terrain terrain, TerrainVue terrainVue, TilePane tilePane) {
        this.sid = sid;
        this.terrain = terrain;
        this.terrainVue = terrainVue;
        this.tilePane = tilePane;
    }

    private void casserBlocXY(int x, int y) {
        int[][] map = terrain.getMap();
        if (map[y][x] == 1) {
            map[y][x] = -1;
            // Plus tard : ajouter à l'inventaire
            terrainVue.afficherMap(tilePane);
            System.out.println("Bloc cassé");
        }
    }

    private void poserBlocXY(int x, int y) {
        int[][] map = terrain.getMap();
        if (map[y][x] == -1) {
            map[y][x] = 1;
            // Plus tard : retirer du bon slot d'inventaire
            terrainVue.afficherMap(tilePane);
            System.out.println("Bloc posé");
        }
    }

    public void gererClic(MouseEvent event) {
        int sourisX = (int) event.getX();
        int sourisY = (int) event.getY();
        int blocX = sourisX / TAILLE_BLOC;
        int blocY = sourisY / TAILLE_BLOC;

        int persoX = sid.getX();
        int persoY = sid.getY() + 28;

        int dx = persoX - sourisX;
        int dy = persoY - sourisY;
        int distanceCarree = dx * dx + dy * dy;

        if (distanceCarree <= DISTANCE_MAX * DISTANCE_MAX) {
            if (event.getButton() == MouseButton.PRIMARY) {
                casserBlocXY(blocX, blocY);
            } else if (event.getButton() == MouseButton.SECONDARY) {
                poserBlocXY(blocX, blocY);
            }
        }
    }

}
