package fr.mbouklikha.dev.sae_glacium.modeles.acteur;

import fr.mbouklikha.dev.sae_glacium.modeles.monde.Environnement;
import fr.mbouklikha.dev.sae_glacium.vues.acteur.SidVue;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.image.ImageView;

public class Sid extends Acteur {

    private final double GRAVITE = 0.3;
    private final double SAUT_FORCE = -10;
    private final double VITESSE_X = 8;

    private ImageView imageView;  // Vue du personnage
    private SidVue sidVue;

    public Sid(ImageView imageView, SidVue sidVue, Environnement environnement) {
        super("Sid", 10, environnement);
        this.imageView = imageView;
        this.sidVue = sidVue;
    }


    public void deplacer(KeyCode code) {
        if (code == KeyCode.D) {
            double newX = imageView.getX() + VITESSE_X;
            imageView.setX(newX);
            sidVue.changerImage("droite");
        } else if (code == KeyCode.Q) {
            double newX = imageView.getX() - VITESSE_X;
            imageView.setX(newX);
            sidVue.changerImage("gauche");
        } else if (code == KeyCode.SPACE && !enSaut) {
            vitesseY = SAUT_FORCE;
            enSaut = true;
        }
    }

    @Override
    public void appliquerGravite(int[][] map, int tailleBloc) {
        vitesseY += GRAVITE;
        double newY = imageView.getY() + vitesseY;

        int caseX = (int)(imageView.getX() / tailleBloc);
        int caseY = (int)((newY + imageView.getFitHeight()) / tailleBloc);

        if (caseY < map.length && caseX < map[0].length && map[caseY][caseX] == 1) {
            vitesseY = 0;
            enSaut = false;
            imageView.setY(caseY * tailleBloc - imageView.getFitHeight());
        } else {
            imageView.setY(newY);
        }
    }

}