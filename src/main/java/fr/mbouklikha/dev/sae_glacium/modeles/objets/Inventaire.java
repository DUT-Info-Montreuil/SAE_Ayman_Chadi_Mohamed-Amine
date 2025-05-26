package fr.mbouklikha.dev.sae_glacium.modeles.objets;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Inventaire {

    private ObservableList<Item> ressources;

    public Inventaire() {
        this.ressources = FXCollections.observableArrayList();
    }

    public void ajouterItem(String nom) {
        for (Item item : ressources) {
            if (item.getNom().equals(nom)) {
                item.ajouter(1);
                // Pas besoin de signaler un changement, la vue peut être liée à la quantité
                return;
            }
        }
        ressources.add(new Item(nom,96,100));
    }

    public boolean retirerItem(String nom, int nb) {
        for (Item item : ressources) {
            if (item.getNom().equals(nom)) {
                if (item.getQuantite() >= nb) {
                    item.ajouter(-nb);
                    if (item.getQuantite() <= 0) {
                        ressources.remove(item);
                    }
                    return true;
                }
            }
        }
        return false;
    }


    public ObservableList<Item> getItems() {
        return ressources;
    }
}
