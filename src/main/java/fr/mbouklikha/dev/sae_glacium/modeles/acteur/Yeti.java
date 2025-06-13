// --- Yeti.java ---
package fr.mbouklikha.dev.sae_glacium.modeles.acteur;

import fr.mbouklikha.dev.sae_glacium.modeles.Hitbox;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Environnement;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.util.Set;

public class Yeti extends Acteur {

    private final StringProperty direction = new SimpleStringProperty("immobile");
    private static final double GRAVITE = 0.4;
    private static final int HAUTEUR_YETI = 64;
    private static final int VITESSE_X = 2;
    private Hitbox hitboxYeti;
    private double vitesseY = 0;
    private boolean frappeEnCours = false;

    private Sid sid;

    public Yeti(Environnement env, Sid sid) {
        super("Yeti", 10, 800, 250, env);
        this.sid = sid;
        this.hitboxYeti = new Hitbox(getX(),getY(),64,64);
    }

    public StringProperty getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction.set(direction);
    }

    @Override
    public void deplacer(Set<javafx.scene.input.KeyCode> touches) {

    }

    public boolean isFrappeEnCours() {
        return frappeEnCours;
    }

    public void suivreEtFrapperSid() {
        int dx = sid.getX() - getX();
        int dy = sid.getY() - getY();

        if (sid == null || !sid.estVivant()) {
            setDirection("immobile");
            frappeEnCours = false;
        }
        else if (Math.abs(dy) > 50) {
            frappeEnCours = false;
            setDirection("immobile");
        }
        else if (Math.abs(dx) <= 20) {
            frappeEnCours = true;
            setDirection(dx > 0 ? "droite" : "gauche");
            sid.setEstRalenti(true);
        }
        else if (Math.abs(dx) <= 180) {
            frappeEnCours = false;
            setX(getX() + (dx > 0 ? VITESSE_X : -VITESSE_X));
            setDirection(dx > 0 ? "droite" : "gauche");
        } else {
            frappeEnCours = false;
            setDirection("immobile");
        }

    }

    @Override
    public void appliquerGravite(int[][] map, int tailleBloc) {
        vitesseY += GRAVITE;
        int newY = (int)(getY() + vitesseY);

        int caseX = getX() / tailleBloc;
        int caseY = (newY + HAUTEUR_YETI) / tailleBloc;

        if (caseY >= map.length || caseX >= map[0].length || caseX < 0) {
            setY(newY);
            return;
        }

        if (map[caseY][caseX] == 1) {
            vitesseY = 0;
            setY(caseY * tailleBloc - HAUTEUR_YETI);
        } else {
            setY(newY);
        }
    }
}
