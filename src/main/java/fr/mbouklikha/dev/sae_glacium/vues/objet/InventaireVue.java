package fr.mbouklikha.dev.sae_glacium.vues.objet;

import fr.mbouklikha.dev.sae_glacium.modeles.objets.Inventaire;
import fr.mbouklikha.dev.sae_glacium.modeles.objets.Item;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class InventaireVue {

    private HBox conteneur;

    public InventaireVue(HBox conteneur) {
        this.conteneur = conteneur;
    }

    public void afficherInventaire(Inventaire inventaire) {
        conteneur.getChildren().clear();

        for (Item item : inventaire.getItems()) {
            VBox caseObjet = new VBox();
            caseObjet.setSpacing(5);

            // Image
            ImageView imageView = new ImageView();
            imageView.setFitWidth(40);
            imageView.setFitHeight(40);

            try {
                String nomImage = item.getObjet().getNom().toLowerCase();
                Image image = new Image(getClass().getResourceAsStream("/fr/mbouklikha/dev/sae_glacium/images/arme/" + nomImage + ".png"));
                imageView.setImage(image);
            } catch (Exception e) {
                System.out.println("Image introuvable pour " + item.getObjet().getNom());
            }

            // Quantité
            Label quantiteLabel = new Label();
            quantiteLabel.textProperty().bind(Bindings.convert(item.getQuantite()));

            caseObjet.getChildren().addAll(imageView, quantiteLabel);
            conteneur.getChildren().add(caseObjet);
        }
    }

    public void setVisible(boolean visible) {
        conteneur.setVisible(visible);
    }

    public boolean isVisible() {
        return conteneur.isVisible();
    }

    public void toggle() {
        setVisible(!isVisible());
    }
}
