package fr.mbouklikha.dev.sae_glacium.controller;

import fr.mbouklikha.dev.sae_glacium.modeles.objets.Item;
import fr.mbouklikha.dev.sae_glacium.vues.objet.InventaireVue;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

import java.util.HashMap;

public class MonObservateurRessource implements ListChangeListener<Item> {

    private Pane panneauJeu;
    private HashMap<Item, InventaireVue> vueMap;

    public MonObservateurRessource(Pane panneauJeu) {
        this.panneauJeu = panneauJeu;
        this.vueMap = new HashMap<>();
    }

    @Override
    public void onChanged(Change<? extends Item> c) {
        while (c.next()) {
            for (Item nouveau : c.getAddedSubList()) {
                //
            }

            for (Item supprime : c.getRemoved()) {
                InventaireVue vue = vueMap.remove(supprime);
                if (vue != null) {
                    //
                }
            }
        }
    }
}