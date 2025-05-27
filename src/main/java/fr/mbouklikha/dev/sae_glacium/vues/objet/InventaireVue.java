package fr.mbouklikha.dev.sae_glacium.vues.objet;

import fr.mbouklikha.dev.sae_glacium.modeles.objets.Inventaire;
import fr.mbouklikha.dev.sae_glacium.modeles.objets.Item;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
            caseObjet.setAlignment(Pos.CENTER);

            // Image
            ImageView imageView = new ImageView();
            imageView.setFitWidth(32);
            imageView.setFitHeight(32);


            String nomImage = item.getObjet().getNom().toLowerCase();
            Image image = new Image(getClass().getResourceAsStream("/fr/mbouklikha/dev/sae_glacium/images/item/" + nomImage + ".png"));
            imageView.setImage(image);



            // Création du bouton
            Button bouton = new Button();
            bouton.setPrefSize(48, 48);
            bouton.setStyle("-fx-background-color: transparent;");
            bouton.setGraphic(imageView);
            bouton.setFocusTraversable(false);

            bouton.setOnAction(e -> {
                System.out.println("Bouton cliqué : " + item.getObjet().getNom());
            });

            // Quantité
            Label quantiteLabel = new Label();
            quantiteLabel.textProperty().bind(Bindings.convert(item.getQuantite()));

            // Ajout dans la case
            caseObjet.getChildren().addAll(bouton, quantiteLabel);
            caseObjet.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-padding: 5;");
            conteneur.getChildren().add(caseObjet);
        }
    }

    public void setVisible(boolean visible) {
        conteneur.setVisible(visible);
    }

    public boolean isVisible() {
        return conteneur.isVisible();
    }

    public void basculerVisibilite() {
        setVisible(!isVisible());
    }
}
