package fr.mbouklikha.dev.sae_glacium.modeles.acteur;

import fr.mbouklikha.dev.sae_glacium.modeles.Hitbox;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Environnement;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.input.KeyCode;

import java.util.Set;

public class Sorcier extends Acteur {

    private Sid sid;
    private static final double GRAVITE = 0.4;
    private double vitesseY = 0;

    private final StringProperty direction = new SimpleStringProperty("occupe");     // "discute" ou "occupe"
    private final StringProperty orientation = new SimpleStringProperty("droite");    // "gauche" ou "droite"
    private boolean occupe = true;

    public Sorcier(Environnement env, Sid sid) {
        super("Sorcier", 10, 300, 250, env);
        this.sid = sid;
    }

    @Override
    public void deplacer(Set<KeyCode> touches) {
        int dx = sid.getX() - getX();

        // Orientation graphique
        if (dx > 0) {
            orientation.set("droite");
        } else {
            orientation.set("gauche");
        }

        // Logique "discute" vs "occupe"
        if (Math.abs(dx) > 50) {
            occupe = false;
            direction.set("discute");
        } else {
            occupe = true;
            direction.set("occupe");
        }
    }

    @Override
    public void appliquerGravite(int[][] map, int tailleBloc) {
        vitesseY += GRAVITE;
        int newY = (int) (getY() + vitesseY);

        int caseX = getX() / tailleBloc;
        int caseY = (newY + 64) / tailleBloc;

        if (caseY < map.length && caseX < map[0].length && map[caseY][caseX] == 1) {
            vitesseY = 0;
            enSaut = false;
            setY(caseY * tailleBloc - 64);
        } else {
            setY(newY);
        }
    }

    @Override
    public Hitbox getHitbox() {
        return null;
    }

    public StringProperty getDirection() {
        return direction;
    }

    public StringProperty getOrientation() {
        return orientation;
    }

    public boolean isOccupe() {
        return occupe;
    }
}
