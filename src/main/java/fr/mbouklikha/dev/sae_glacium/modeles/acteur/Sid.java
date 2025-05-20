package fr.mbouklikha.dev.sae_glacium.modeles.acteur;

import fr.mbouklikha.dev.sae_glacium.modeles.monde.Environnement;
import javafx.scene.input.KeyCode;
import java.util.Set;

public class Sid extends Acteur {

    private String direction = "base";

    private final double GRAVITE = 0.4;
    private final double SAUT_FORCE = -8;
    private final double VITESSE_X = 5;
    private double vitesseY = 0;

    public Sid(Environnement env) {
        super("Sid", 10, 100, 100, env); // position initiale (100,100)
    }

    public String getDirection() {
        return direction;
    }

    // Méthodes deplacer abstract de Acteur
    @Override
    public void deplacer(Set<KeyCode> touches) {
        if (touches.contains(KeyCode.D)) {
            setX(getX() + (int)VITESSE_X);
            direction = "droite";
        }

        if (touches.contains(KeyCode.Q)) {
            setX(getX() - (int)VITESSE_X);
            direction = "gauche";
        }

        if (touches.contains(KeyCode.SPACE) && !enSaut) {
            vitesseY = SAUT_FORCE;
            enSaut = true;
        }
    }

    @Override
    public void appliquerGravite(int[][] map, int tailleBloc) {
        vitesseY += GRAVITE;
        int newY = (int)(getY() + vitesseY);

        int caseX = getX() / tailleBloc;
        int caseY = (newY + 56) / tailleBloc; // 56 = hauteur sprite

        if (caseY < map.length && caseX < map[0].length && map[caseY][caseX] == 1) {
            vitesseY = 0;
            enSaut = false;
            setY(caseY * tailleBloc - 56);
        } else {
            setY(newY);
        }
    }
}
