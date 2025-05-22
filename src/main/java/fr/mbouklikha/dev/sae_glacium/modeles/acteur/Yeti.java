package fr.mbouklikha.dev.sae_glacium.modeles.acteur;

import fr.mbouklikha.dev.sae_glacium.modeles.monde.Environnement;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.input.KeyCode;

import java.util.Set;

public class Yeti extends Acteur {

    private final StringProperty direction = new SimpleStringProperty("immobile");

    private static final double GRAVITE = 0.4;
    private static final int HAUTEUR_YETI = 64;
    private static final int VITESSE_X = 4;

    private double vitesseY = 0;

    public Yeti(Environnement env) {
        super("Yeti", 10, 150, 100, env);
    }

    public StringProperty getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction.set(direction);
    }

    @Override
    public void deplacer(Set<KeyCode> touches) {
        boolean aBouge = false;

        if (touches.contains(KeyCode.RIGHT)) {
            setX(getX() + VITESSE_X);
            setDirection("droite");
            aBouge = true;
        }

        if (touches.contains(KeyCode.LEFT)) {
            setX(getX() - VITESSE_X);
            setDirection("gauche");
            aBouge = true;
        }

        if (!aBouge) {
            setDirection("immobile");
        }
    }

    @Override
    public void appliquerGravite(int[][] map, int tailleBloc) {
        vitesseY += GRAVITE;
        int newY = (int)(getY() + vitesseY);

        int caseX = getX() / tailleBloc;
        int caseY = (newY + HAUTEUR_YETI) / tailleBloc;

        // Sécurité : vérifier que les coordonnées sont valides
        if (caseY >= map.length || caseX >= map[0].length || caseX < 0) {
            setY(newY); // Laisser tomber si hors map
            return;
        }

        if (map[caseY][caseX] == 1) { // Bloc solide en dessous
            vitesseY = 0;
            setY(caseY * tailleBloc - HAUTEUR_YETI);
        } else {
            setY(newY);
        }
    }
}
