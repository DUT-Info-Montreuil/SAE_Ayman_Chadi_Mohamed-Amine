package fr.mbouklikha.dev.sae_glacium.vues.objet;

import fr.mbouklikha.dev.sae_glacium.modeles.objets.Inventaire;
import fr.mbouklikha.dev.sae_glacium.modeles.objets.Item;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class InventaireVue {

    private HBox conteneur;
    private VBox[] cases = new VBox[8];
    private ImageView[] images = new ImageView[8];
    private Label[] quantites = new Label[8];

    public InventaireVue(HBox conteneur) {
        this.conteneur = conteneur;
    }

    public void initialiserCases(Inventaire inventaire) {
        conteneur.getChildren().clear();
        for (int i = 0; i < 8; i++) {
            VBox caseObjet = new VBox();
            caseObjet.setSpacing(5);
            caseObjet.setAlignment(Pos.CENTER);
            caseObjet.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-padding: 5;");

            ImageView imageView = new ImageView();
            imageView.setFitWidth(30);
            imageView.setFitHeight(30);

            Label quantiteLabel = new Label();

            final int index = i; // pour accéder i dans le lambda

            caseObjet.setOnMouseClicked(event -> {
                if (index < inventaire.getItems().size()) {
                    Item item = inventaire.getItems().get(index);
                    int qte = item.getQuantite().get();
                    if (qte > 0) {
                        item.getQuantite().set(qte - 1);
                        System.out.println(item.getNom() + " a été retiré");
                        if (item.getQuantite().get() == 0) {
                            inventaire.getItems().remove(index);
                        }
                        mettreAJourInventaire(inventaire);
                    }
                }
            });

            caseObjet.setOnMouseEntered(e -> caseObjet.setStyle(
                    "-fx-border-color: blue; -fx-border-width: 2; -fx-padding: 5;"
            ));
            caseObjet.setOnMouseExited(e -> caseObjet.setStyle(
                    "-fx-border-color: black; -fx-border-width: 2; -fx-padding: 5;"
            ));


            caseObjet.getChildren().addAll(imageView, quantiteLabel);
            conteneur.getChildren().add(caseObjet);

            cases[i] = caseObjet;
            images[i] = imageView;
            quantites[i] = quantiteLabel;
        }
    }


    public void mettreAJourInventaire(Inventaire inventaire) {
        for (int i = 0; i < 8; i++) {
            if (i < inventaire.getItems().size()) {
                Item item = inventaire.getItems().get(i);

                String nomImage = item.getObjet().getNom().toLowerCase();
                Image image = new Image(getClass().getResourceAsStream("/fr/mbouklikha/dev/sae_glacium/images/item/" + nomImage + ".png"));
                images[i].setImage(image);
                quantites[i].textProperty().bind(Bindings.convert(item.getQuantite()));
            } else {
                images[i].setImage(null);
                quantites[i].textProperty().unbind();
                quantites[i].setText("");
            }
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
