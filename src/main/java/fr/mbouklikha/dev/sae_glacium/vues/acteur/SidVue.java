package fr.mbouklikha.dev.sae_glacium.vues.acteur;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Acteur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


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

        // 🔁 Lier x/y du modèle à ceux de l’image
        imageView.xProperty().bind(acteur.getXProperty().asObject());
        imageView.yProperty().bind(acteur.getYProperty().asObject());

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
            default:
                imageView.setImage(imageBase);
                break;
        }
    }
}

