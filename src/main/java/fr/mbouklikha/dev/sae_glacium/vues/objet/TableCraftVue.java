package fr.mbouklikha.dev.sae_glacium.vues.objet;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class TableCraftVue {
    private VBox conteneur; // Le conteneur principal
    private GridPane grilleCraft;
    private ImageView[][] slots; // 3x3
    private ImageView slotResultat;
    private Button boutonCrafter;

    public TableCraftVue() {
        conteneur = new VBox(10);
        conteneur.setAlignment(Pos.CENTER);
        conteneur.setStyle("-fx-background-color: rgba(0,0,0,0.7); -fx-padding: 10; -fx-border-color: white; -fx-border-width: 2;");
        conteneur.setVisible(false);

        grilleCraft = new GridPane();
        grilleCraft.setHgap(5);
        grilleCraft.setVgap(5);
        grilleCraft.setAlignment(Pos.CENTER);

        slots = new ImageView[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                ImageView slot = new ImageView();
                slot.setFitWidth(40);
                slot.setFitHeight(40);
                slot.setPreserveRatio(true);

                StackPane slotPane = new StackPane(slot);
                slotPane.setStyle("-fx-border-color: gray; -fx-background-color: white;");
                slotPane.setPrefSize(40, 40);

                slots[row][col] = slot;
                grilleCraft.add(slotPane, col, row);
            }
        }

        // Résultat
        slotResultat = new ImageView();
        slotResultat.setFitWidth(40);
        slotResultat.setFitHeight(40);
        slotResultat.setStyle("-fx-border-color: gold;");

        boutonCrafter = new Button("Crafter");

        conteneur.getChildren().addAll(new Label("Table de craft"), grilleCraft, boutonCrafter, slotResultat);
    }

    public VBox getConteneur() {
        return conteneur;
    }

    public void setVisible(boolean visible) {
        conteneur.setVisible(visible);
    }

    public boolean isVisible() {
        return conteneur.isVisible();
    }
}
