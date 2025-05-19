package fr.mbouklikha.dev.sae_glacium.vues.acteur;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Acteur;
import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Environnement;
import fr.mbouklikha.dev.sae_glacium.modeles.objets.Ressources;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class SidVue {

    private Acteur acteur;
    private ImageView imageView;

    private Image imageDroite;
    private Image imageGauche;
    private Image imageBase;

    public SidVue(Acteur acteur, Pane zoneJeu) {
        this.acteur = acteur;

        imageDroite = new Image(getClass().getResourceAsStream("/fr/mbouklikha/dev/sae_glacium/images/sid_droite.png"));
        imageGauche = new Image(getClass().getResourceAsStream("/fr/mbouklikha/dev/sae_glacium/images/sid_gauche.png"));
        imageBase = new Image(getClass().getResourceAsStream("/fr/mbouklikha/dev/sae_glacium/images/sid.png"));

        imageView = new ImageView(imageBase);
        imageView.setFitWidth(30);
        imageView.setFitHeight(56);
        imageView.setX(100);
        imageView.setY(100);

        zoneJeu.getChildren().add(imageView);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void changerImage(String direction) {
        switch (direction) {
            case "droite":
                imageView.setImage(imageDroite);
                break;
            case "gauche":
                imageView.setImage(imageGauche);
                break;
            case "base":
            default:
                imageView.setImage(imageBase);
                break;
        }
    }
}
