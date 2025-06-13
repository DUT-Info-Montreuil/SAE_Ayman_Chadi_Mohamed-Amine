package fr.mbouklikha.dev.sae_glacium.controller;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Terrain;
import fr.mbouklikha.dev.sae_glacium.modeles.objets.Inventaire;
import fr.mbouklikha.dev.sae_glacium.vues.monde.TerrainVue;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;


public class Souris {

    private Sid sid;
    private Terrain terrain;
    private int TAILLE_BLOC = 32;
    private TerrainVue terrainVue;
    private TilePane tilePane;
    private Inventaire inventaire;
    private final int DISTANCE_MAX = 128; // distance maximale autorisée (4 bloc)
    public boolean peutCasser = false;

    public Souris(Sid sid, Terrain terrain, TerrainVue terrainVue, TilePane tilePane) {
        this.sid = sid;
        this.terrain = terrain;
        this.terrainVue = terrainVue;
        this.tilePane = tilePane;
    }

    private void clic_gauche(int x, int y) {
        if (sid.getObjetEnMain() != null) {
            sid.getObjetEnMain().fonction(x, y);
        }
        terrainVue.afficherMap(tilePane);
    }

    private void clic_droit(int x, int y) {
        if (sid.getObjetEnMain() != null) {
            sid.getObjetEnMain().fonction(x, y);
        }
        terrainVue.afficherMap(tilePane);
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
                clic_gauche(blocX, blocY);
            } else if (event.getButton() == MouseButton.SECONDARY) {
                clic_droit(blocX, blocY);
            }
        }
    }

}
