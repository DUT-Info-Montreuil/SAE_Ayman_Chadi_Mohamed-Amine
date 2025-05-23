package fr.mbouklikha.dev.sae_glacium.vues.monde;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import fr.mbouklikha.dev.sae_glacium.modeles.monde.Terrain;
import javafx.scene.layout.TilePane;

public class TerrainVue {
    private Terrain terrain;

    public TerrainVue(Terrain terrain, TilePane tilePane) {
        this.terrain = terrain;
        afficherMap(tilePane);
    }


    public void afficherMap(TilePane tilePane) {
        int[][] map = terrain.getMap();
        tilePane.getChildren().clear();

        for (int[] ligne : map) {
            for (int val : ligne) {

                ImageView bloc = new ImageView();
                bloc.setFitWidth(32);
                bloc.setFitHeight(32);

                switch (val) {
                    case 0:
                        bloc.setImage(new Image(getClass().getResourceAsStream("/fr/mbouklikha/dev/sae_glacium/images/map/ciel.png")));
                        break;
                    case 1:
                        bloc.setImage(new Image(getClass().getResourceAsStream("/fr/mbouklikha/dev/sae_glacium/images/map/neige.png")));
                        break;
                    case 2:
                        bloc.setImage(new Image(getClass().getResourceAsStream("/fr/mbouklikha/dev/sae_glacium/images/map/glace.png")));
                        break;
                }

                tilePane.getChildren().add(bloc);
            }
        }
    }
}

