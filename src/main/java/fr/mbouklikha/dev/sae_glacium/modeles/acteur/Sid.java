package fr.mbouklikha.dev.sae_glacium.modeles.acteur;

import fr.mbouklikha.dev.sae_glacium.modeles.monde.Environnement;
import fr.mbouklikha.dev.sae_glacium.modeles.objets.Item;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.input.KeyCode;
import java.util.Set;
import fr.mbouklikha.dev.sae_glacium.modeles.objets.Inventaire;

public class Sid extends Acteur {

    private final StringProperty directionProperty = new SimpleStringProperty("base");

    private String objetEnMain = "pioche";

    private final double GRAVITE = 0.4;
    private final double SAUT_FORCE = -8;
    private double vitesseY = 0;
    private Inventaire inventaire;

    public Sid(Environnement env) {
        super("Sid", 10, 100, 100, env); // position initiale (100,100)
    }

    public StringProperty getDirection() {
        return directionProperty;
    }

    public void setDirection(String direction) {
        directionProperty.set(direction);
    }

    public Inventaire getInventaire() {
        return inventaire;
    }


    // Méthodes deplacer abstract de Acteur
    @Override
    public void deplacer(Set<KeyCode> touches) {
        int caseX = getX() / 32;
        int caseY = getY() / 32;

        int[][] map = getEnvironnement().getTerrain().getMap();

        if (touches.contains(KeyCode.D)) {
            // Vérifie si la case à droite est vide
            int prochaineCaseX = (getX() + 5 + 30) / 32; // 30 = largeur image du perso
            if (prochaineCaseX < map[0].length && map[caseY][prochaineCaseX] == -1) {
                setX(getX() + 5);
                setDirection("droite");
            }
        }

        if (touches.contains(KeyCode.Q)) {
            // Vérifie si la case à gauche est vide
            int prochaineCaseX = (getX() - 5) / 32;
            if (prochaineCaseX >= 0 && map[caseY][prochaineCaseX] == -1) {
                setX(getX() - 5);
                setDirection("gauche");
            }
        }

        if (touches.contains(KeyCode.SPACE) && !enSaut) {
            vitesseY = -8;
            enSaut = true;
        }

    }



    @Override
    public void appliquerGravite(int[][] map, int tailleBloc) {
        vitesseY += GRAVITE;
        int newY = (int)(getY() + vitesseY); // nouvelle position du perso après mouvement vertical

        int caseX = getX() / tailleBloc; // savoir dans quel colonne le perso se trouve

        if (vitesseY > 0) { // le personnage descend
            int caseBas = (newY + 56) / tailleBloc; // 56 = hauteur du perso
            if (caseBas < map.length && map[caseBas][caseX] == 1) {
                vitesseY = 0;
                enSaut = false;
                setY(caseBas * tailleBloc - 56); // pose le perso avec le haut du bloc
            } else {
                setY(newY);
            }
        } else if (vitesseY < 0) { // le personnage monte (saute)
            int caseHaut = newY / tailleBloc;
            if (caseHaut >= 0 && map[caseHaut][caseX] == 1) {
                vitesseY = 0;
                setY((caseHaut + 1) * tailleBloc); // pose le perso avec le bas du bloc
            } else {
                setY(newY);
            }
        } else {
            setY(newY);
        }
    }


    public String getObjetEnMain() {
        return objetEnMain;
    }

    public void setObjetEnMain(String objet) {
        this.objetEnMain = objet;
    }

}
