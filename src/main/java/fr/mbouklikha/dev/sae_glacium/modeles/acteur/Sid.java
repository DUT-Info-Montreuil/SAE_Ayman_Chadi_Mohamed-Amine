package fr.mbouklikha.dev.sae_glacium.modeles.acteur;

import fr.mbouklikha.dev.sae_glacium.modeles.monde.Environnement;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.input.KeyCode;
import java.util.Set;

public class Sid extends Acteur {

    private final StringProperty directionProperty = new SimpleStringProperty("base");

    private final double GRAVITE = 0.4;
    private final double SAUT_FORCE = -8;
    private double vitesseY = 0;
    public boolean estRalenti ;

    public Sid(Environnement env) {
        super("Sid", 10, 100, 100, env); // position initiale (100,100)
    }

    public StringProperty getDirection() {
        return directionProperty;
    }

    public void setDirection(String direction) {
        directionProperty.set(direction);
    }
    private int finRalenti;

    // Méthodes deplacer abstract de Acteur
    @Override
    public void deplacer(Set<KeyCode> touches) {
        if (finRalenti == 300) {
            finRalenti = 0;
            estRalenti = false;
        }
        if (touches.contains(KeyCode.D)) {
            if (estRalenti ){
                setX(getX() + 3);
                finRalenti ++;
            }else {
                setX(getX() + 5);
            }

            setDirection("droite");
        }

        if (touches.contains(KeyCode.Q)) {
            if (estRalenti){
                setX(getX() - 3);
                finRalenti ++;
            }else {
                setX(getX() - 5);
            }
            setDirection("gauche");
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
