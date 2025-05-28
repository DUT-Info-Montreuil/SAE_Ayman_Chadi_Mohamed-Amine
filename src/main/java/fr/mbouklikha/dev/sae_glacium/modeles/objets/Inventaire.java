package fr.mbouklikha.dev.sae_glacium.modeles.objets;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventaire {

    private ObservableList<Item> items;

    public Inventaire() {
        this.items = FXCollections.observableArrayList();
    }

    public ObservableList<Item> getItems() {
        return items;
    }

    public void ajouterItem(Objets objetAAjouter) {
        for (Item item : items) {
            if (item.getNom().equals(objetAAjouter.getNom())) {
                item.ajouter(1);
                return;
            }
        }
        items.add(objetAAjouter.creerItem());
    }

    public void supprimerItem(Item item) {
        items.remove(item);
    }
}

