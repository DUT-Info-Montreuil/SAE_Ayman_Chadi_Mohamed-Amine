package fr.mbouklikha.dev.sae_glacium.controller;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;
import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Yeti;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Environnement;

import fr.mbouklikha.dev.sae_glacium.modeles.objets.Inventaire;
import fr.mbouklikha.dev.sae_glacium.modeles.objets.Outil;
import fr.mbouklikha.dev.sae_glacium.modeles.objets.Ressource;
import fr.mbouklikha.dev.sae_glacium.vues.acteur.SidVue;
import fr.mbouklikha.dev.sae_glacium.vues.acteur.YetiVue;
import fr.mbouklikha.dev.sae_glacium.vues.monde.TerrainVue;

import fr.mbouklikha.dev.sae_glacium.vues.objet.InventaireVue;
import fr.mbouklikha.dev.sae_glacium.vues.objet.ObjetEnMainVue;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

import java.util.HashSet;
import java.util.Set;


public class Controller {

    @FXML
    private TilePane tilePane;

    @FXML
    private Pane zoneJeu;

    @FXML
    private HBox conteneurInventaire;

    private Inventaire inventaire;
    private InventaireVue inventaireVue;
    private ObjetEnMainVue objetEnMainVue;


    private Timeline gameLoop;
    private Set<KeyCode> touchesActives = new HashSet<>();
    private int temps = 0 ;

    private Environnement env;
    private Sid sid;
    private SidVue sidVue;

    private Yeti yeti;
    private YetiVue yetiVue;

    private final int TAILLE_BLOC = 32;



    @FXML
    public void initialize() {
        this.env = new Environnement(992, 576);
        new TerrainVue(env.getTerrain(), tilePane);

        sid = new Sid(env);
        sidVue = new SidVue(sid, zoneJeu);

        yeti = new Yeti(env, sid);
        yetiVue = new YetiVue(yeti, zoneJeu);


        // -------------------------------------------------------------------------------------------

        // Inventaire
        inventaire = new Inventaire();
        inventaire.ajouterItem(new Outil("dague"));
        inventaire.ajouterItem(new Outil("arc"));

        inventaireVue = new InventaireVue(conteneurInventaire, sid);
        inventaireVue.initialiserCases(inventaire);
        inventaireVue.mettreAJourInventaire(inventaire);
        conteneurInventaire.setVisible(true);


        objetEnMainVue = new ObjetEnMainVue(sid);

        // Positionner en haut à droite
        objetEnMainVue.getConteneur().setLayoutX(zoneJeu.getPrefWidth() - 70);
        objetEnMainVue.getConteneur().setLayoutY(10);

        zoneJeu.getChildren().add(objetEnMainVue.getConteneur());

        inventaireVue.setObjetEnMainVue(objetEnMainVue);

        // ---------------------------------------------------------------------------------

        // Focus sur les élements du fxml
        tilePane.setFocusTraversable(false);
        zoneJeu.setFocusTraversable(true);


        Platform.runLater(() -> {
            zoneJeu.setOnKeyPressed(event -> touchesActives.add(event.getCode()));
            zoneJeu.setOnKeyReleased(event -> touchesActives.remove(event.getCode()));
            zoneJeu.requestFocus();

            initAnimation();
            gameLoop.play();
        });
    }



    private void initAnimation() {
        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame keyFrame = new KeyFrame(
                Duration.seconds(0.017), // ≈ 60 FPS
                ev -> {
                    yeti.appliquerGravite(env.getTerrain().getMap(), TAILLE_BLOC);
                    yeti.suivreEtFrapperSid();

                    sid.deplacer(touchesActives);
                    sid.appliquerGravite(env.getTerrain().getMap(), TAILLE_BLOC);
                    sid.appliquerGravite(env.getTerrain().getMap(), TAILLE_BLOC);  // applique la gravité
                    temps++;
                }
        );

        gameLoop.getKeyFrames().add(keyFrame);
    }
}