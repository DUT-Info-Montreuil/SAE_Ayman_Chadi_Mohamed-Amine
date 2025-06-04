package fr.mbouklikha.dev.sae_glacium.modeles.acteur;

import fr.mbouklikha.dev.sae_glacium.modeles.Hitbox;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Environnement;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.Set;

public class Sid extends Acteur {

    private final StringProperty directionProperty = new SimpleStringProperty("base");
    private final double GRAVITE = 0.4;
    private final double SAUT_FORCE = -8;
    private double vitesseY = 0;
    private Hitbox hitbox;
    private Environnement environnement;


    public Sid(Environnement env) {
        super("Sid", 10, 100, 100, env); // position initiale (100,100)
        this.environnement = env;
        hitbox = new Hitbox(getX(), getY(), 25, 54); // taille du perso

    }

    public Hitbox getHitbox() {
        return hitbox;
    }

    public StringProperty getDirection() {
        return directionProperty;
    }

    public void setDirection(String direction) {
        directionProperty.set(direction);
    }

    // Méthode deplacer abstract de Acteur
    @Override
    public void deplacer(Set<KeyCode> touches) {
        double nouvelleX = getX();

        if (touches.contains(KeyCode.D)) {
            nouvelleX += 5;
            setDirection("droite");
        }
        if (touches.contains(KeyCode.Q)) {
            nouvelleX -= 5;
            setDirection("gauche");
        }

        // Tester collision avant de bouger
        hitbox.setPosition((int) nouvelleX, getY());
        if (!collisionAvecBlocs(environnement.getTerrain().getHitboxBlocsSolides())) {
            setX(nouvelleX);
        }
            hitbox.setPosition(getX(), getY());  //System.out.println(("avant :" + ", " + this.getX() + this.getY() + this.getHitbox().getRectangle()));

        // Gestion du saut
        if (touches.contains(KeyCode.SPACE) && !enSaut) {
            vitesseY = SAUT_FORCE;
            enSaut = true;
        }

        // Met à jour la position de la hitbox
        hitbox.setPosition(getX(), getY());
    }


    @Override
    public void appliquerGravite(int[][] map, int tailleBloc) {
        vitesseY += GRAVITE;
        double nouvelleY = getY() + vitesseY;

        hitbox.setPosition(getX(), (int) nouvelleY);
        if (!collisionAvecBlocs(environnement.getTerrain().getHitboxBlocsSolides())) {
            setY((int) nouvelleY);
        } else {
            if (vitesseY > 0) {
                // Collision avec le sol, on bloque la chute
                enSaut = false;
            }
            vitesseY = 0;
        }
        hitbox.setPosition(getX(), getY());
    }


    public boolean collisionAvecBlocs(ArrayList<Hitbox> blocsSolides) {
        for (Hitbox bloc : blocsSolides) {
            if (hitbox.collisionAvec(bloc)) {
                return true; // collision détectée avec un bloc solide
            }
        }
        return false;
    }













}
