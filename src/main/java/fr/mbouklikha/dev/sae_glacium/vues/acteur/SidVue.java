package fr.mbouklikha.dev.sae_glacium.vues.acteur;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class SidVue {

    private Sid sid;
    private ImageView imageView;

    private Image imageDroite;
    private Image imageGauche;
    private Image imageDroiteRalenti;
    private Image imageGaucheRalenti;
    private Image imageBase;

    public SidVue(Sid sid, Pane zoneJeu) {
        this.sid = sid;

        imageDroite = new Image(getClass().getResourceAsStream("/fr/mbouklikha/dev/sae_glacium/images/sid_droite.png"));
        imageGauche = new Image(getClass().getResourceAsStream("/fr/mbouklikha/dev/sae_glacium/images/sid_gauche.png"));
        imageDroiteRalenti = new Image(getClass().getResourceAsStream("/fr/mbouklikha/dev/sae_glacium/images/sid_droite_glace.png"));
        imageGaucheRalenti = new Image(getClass().getResourceAsStream("/fr/mbouklikha/dev/sae_glacium/images/sid_gauche_glace.png"));
        imageBase = new Image(getClass().getResourceAsStream("/fr/mbouklikha/dev/sae_glacium/images/sid.png"));

        imageView = new ImageView(imageBase);
        imageView.setFitWidth(30);
        imageView.setFitHeight(56);

        imageView.xProperty().bind(sid.getXProperty().asObject());
        imageView.yProperty().bind(sid.getYProperty().asObject());

        sid.getDirection().addListener((obs, oldDir, newDir) -> changerImage(newDir));
        sid.estRalentiProperty().addListener((obs, oldVal, newVal) -> changerImage(sid.getDirection().get()));

        zoneJeu.getChildren().add(imageView);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void changerImage(String direction) {
        switch (direction) {
            case "droite":
                imageView.setImage(sid.isEstRalenti() ? imageDroiteRalenti : imageDroite);
                break;
            case "gauche":
                imageView.setImage(sid.isEstRalenti() ? imageGaucheRalenti : imageGauche);
                break;
            default:
                imageView.setImage(imageBase);
                break;
        }
    }
}