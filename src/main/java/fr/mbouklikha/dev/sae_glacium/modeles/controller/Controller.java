package fr.mbouklikha.dev.sae_glacium.modeles.controller;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Acteur;
import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Environnement;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Terrain;

import fr.mbouklikha.dev.sae_glacium.vues.acteur.ActeurVue;
import fr.mbouklikha.dev.sae_glacium.vues.acteur.SidVue;
import fr.mbouklikha.dev.sae_glacium.vues.monde.TerrainVue;
import javafx.animation.AnimationTimer;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Rectangle;


public class Controller {

    @FXML
    private TilePane tilePane;

    @FXML
    private Pane zoneJeu;

    @FXML
    private ImageView joueurImageView;

    private Sid sid;

    private SidVue sidVue;

    private AnimationTimer gameLoop;

    private final int TAILLE_BLOC = 32;

    private int[][] map;

    @FXML
    public void initialize() {
        Terrain terrain = new Terrain();
        map = terrain.getMap();
        TerrainVue terrainVue = new TerrainVue(terrain);
        terrainVue.afficherMap(tilePane);

        Environnement env = new Environnement(800, 600);

        sidVue = new SidVue(null, zoneJeu);
        joueurImageView = sidVue.getImageView();
        sid = new Sid(joueurImageView, sidVue, env);


        Platform.runLater(() -> {
            joueurImageView.getScene().setOnKeyPressed(event -> sid.deplacer(event.getCode()));
            lancerBoucleDeJeu();
        });
    }

    private void lancerBoucleDeJeu() {
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                sid.appliquerGravite(map, TAILLE_BLOC);
            }
        };
        gameLoop.start();
    }





}
