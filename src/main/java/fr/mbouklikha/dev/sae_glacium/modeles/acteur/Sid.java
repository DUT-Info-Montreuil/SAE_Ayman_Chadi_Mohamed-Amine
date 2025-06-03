// --- Sid.java ---
package fr.mbouklikha.dev.sae_glacium.modeles.acteur;

import fr.mbouklikha.dev.sae_glacium.modeles.monde.Environnement;
import javafx.beans.property.*;
import javafx.scene.input.KeyCode;
import java.util.Set;

public class Sid extends Acteur {

    private final StringProperty directionProperty = new SimpleStringProperty("base");
    private final BooleanProperty estRalenti = new SimpleBooleanProperty(false);

    private final double GRAVITE = 0.4;
    private final double SAUT_FORCE = -8;
    private double vitesseY = 0;
    private int finRalenti;

    public Sid(Environnement env) {

        super("Sid", 10, 100, 100, env);
    }

    public StringProperty getDirection() {
        return directionProperty;
    }

    public void setDirection(String direction) {
        directionProperty.set(direction);
    }

    public BooleanProperty estRalentiProperty() {
        return estRalenti;
    }

    public boolean isEstRalenti() {
        return estRalenti.get();
    }

    public void setEstRalenti(boolean ralenti) {
        estRalenti.set(ralenti);
    }

    @Override
    public void deplacer(Set<KeyCode> touches) {
        if (finRalenti == 300) {
            finRalenti = 0;
            setEstRalenti(false);
        }
        if (touches.contains(KeyCode.D)) {
            setX(getX() + (isEstRalenti() ? 2 : 4));
            setDirection("droite");
            if (isEstRalenti()) finRalenti++;
        }
        if (touches.contains(KeyCode.Q)) {
            setX(getX() - (isEstRalenti() ? 2 : 4));
            setDirection("gauche");
            if (isEstRalenti()) finRalenti++;
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
        int caseY = (newY + 56) / tailleBloc;

        if (caseY < map.length && caseX < map[0].length && map[caseY][caseX] == 1) {
            vitesseY = 0;
            enSaut = false;
            setY(caseY * tailleBloc - 56);
        } else {
            setY(newY);
        }
    }
}