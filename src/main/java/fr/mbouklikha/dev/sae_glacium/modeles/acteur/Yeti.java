package fr.mbouklikha.dev.sae_glacium.modeles.acteur;

import fr.mbouklikha.dev.sae_glacium.modeles.Hitbox;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Environnement;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.Set;

public class Yeti extends Acteur {

    private final StringProperty direction = new SimpleStringProperty("immobile");
    private static final double GRAVITE = 0.4;
    private static final int VITESSE_X = 2;
    private double vitesseY = 0;
    private boolean frappeEnCours = false;
    private int compteurDegats = 0;

    private Sid sid;
    private final Environnement environnement;
    private final Hitbox hitboxYeti;

    public Yeti(Environnement env, Sid sid) {
        super("Yeti", 100, 800, 350, env);
        this.sid = sid;
        this.environnement = env;
        this.hitboxYeti = new Hitbox(getX(), getY(), 60, 60);
    }

    @Override
    public Hitbox getHitbox() {
        return hitboxYeti;
    }

    public StringProperty getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction.set(direction);
    }

    @Override
    public void deplacer(Set<javafx.scene.input.KeyCode> touches) {
        // Le Yeti se déplace automatiquement
    }

    public boolean isFrappeEnCours() {
        return frappeEnCours;
    }

    public void suivreEtFrapperSid() {
        if (sid == null || !sid.estVivant()) {
            setDirection("immobile");
            frappeEnCours = false;
        } else {

        int dx = sid.getX() - getX();
        int dy = sid.getY() - getY();

        if (Math.abs(dy) > 50) {
            frappeEnCours = false;
            setDirection("immobile");
        } else if (Math.abs(dx) <= 20) {
            frappeEnCours = true;
            setDirection(dx > 0 ? "droite" : "gauche");
            sid.setEstRalenti(true);
        } else if (Math.abs(dx) <= 180) {
            frappeEnCours = false;
            int deplacementX = (dx > 0 ? VITESSE_X : -VITESSE_X);

            // Collision latérale comme pour Sid
            hitboxYeti.setPosition(getX() + deplacementX, getY());
            boolean collision = false;
            for (Hitbox bloc : environnement.getTerrain().getHitboxBlocsSolides()) {
                if (hitboxYeti.collisionAvec(bloc)) {
                    collision = true;
                    break;
                }
            }

            if (!collision) {
                setX(getX() + deplacementX);
            }

            setDirection(dx > 0 ? "droite" : "gauche");
            hitboxYeti.setPosition(getX(), getY());
        } else {
            frappeEnCours = false;
            setDirection("immobile");
        }
        }

        // Mise à jour finale de la hitbox
        hitboxYeti.setPosition(getX(), getY());
    }

    @Override
    public void appliquerGravite(int[][] map, int tailleBloc) {
        vitesseY += GRAVITE;
        int newY = (int) (getY() + vitesseY);

        int caseX = getX() / tailleBloc;
        int caseY = (newY + 60) / tailleBloc;

        if (caseY >= map.length || caseX >= map[0].length || caseX < 0) {
            setY(newY);
            hitboxYeti.setPosition(getX(), newY);
        } else {
            hitboxYeti.setPosition(getX(), newY);
            if (!collisionAvecBlocs(environnement.getTerrain().getHitboxBlocsSolides())) {
                setY(newY);
            } else {
                vitesseY = 0;
            }
        }
        hitboxYeti.setPosition(getX(), getY());
    }

    public boolean collisionAvecBlocs(ArrayList<Hitbox> blocsSolides) {
        for (Hitbox bloc : blocsSolides) {
            if (hitboxYeti.collisionAvec(bloc)) {
                return true;
            }
        }
        return false;
    }
}
