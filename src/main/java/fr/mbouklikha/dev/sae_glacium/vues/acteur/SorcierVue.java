package fr.mbouklikha.dev.sae_glacium.vues.acteur;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sorcier;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class SorcierVue {

    private final Sorcier sorcier;
    private final ImageView imageView;

    private final Image imageOccupeDroite;
    private final Image imageOccupeGauche;
    private final Image imageDiscuteDroite;
    private final Image imageDiscuteGauche;

    public SorcierVue(Sorcier sorcier, Pane zoneJeu) {
        this.sorcier = sorcier;

        imageOccupeDroite = new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                "/fr/mbouklikha/dev/sae_glacium/images/Sorcier/D/O.png")));
        imageOccupeGauche = new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                "/fr/mbouklikha/dev/sae_glacium/images/Sorcier/G/O.png")));
        imageDiscuteDroite = new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                "/fr/mbouklikha/dev/sae_glacium/images/Sorcier/D/D.png")));
        imageDiscuteGauche = new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                "/fr/mbouklikha/dev/sae_glacium/images/Sorcier/G/D.png")));

        imageView = new ImageView(imageOccupeDroite);
        imageView.setFitWidth(54);
        imageView.setFitHeight(64);

        imageView.xProperty().bind(sorcier.getXProperty().asObject());
        imageView.yProperty().bind(sorcier.getYProperty().asObject());

        sorcier.getDirection().addListener((obs, oldVal, newVal) -> updateImage());
        sorcier.getOrientation().addListener((obs, oldVal, newVal) -> updateImage());

        updateImage();
        zoneJeu.getChildren().add(imageView);
    }

    private void updateImage() {
        String etat = sorcier.getDirection().get();         // "discute" ou "occupe"
        String orientation = sorcier.getOrientation().get(); // "droite" ou "gauche"

        if (etat.equals("discute")) {
            imageView.setImage(orientation.equals("droite") ? imageDiscuteDroite : imageDiscuteGauche);
        } else {
            imageView.setImage(orientation.equals("droite") ? imageOccupeDroite : imageOccupeGauche);
        }
    }

    public ImageView getImageView() {
        return imageView;
    }
}