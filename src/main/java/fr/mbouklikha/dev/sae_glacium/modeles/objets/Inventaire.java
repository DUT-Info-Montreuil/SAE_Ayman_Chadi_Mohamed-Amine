package fr.mbouklikha.dev.sae_glacium.modeles.objets;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventaire {

    private ObservableList<Item> items;
    private StringProperty objetEnMain = new SimpleStringProperty("pioche");


    public Inventaire() {
        this.items = FXCollections.observableArrayList();
    }

    public ObservableList<Item> getItems() {
        return items;
    }

    public StringProperty getObjetEnMain() {
        return objetEnMain;
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

    public void retirerUnItem(Objets objetARetirer) {
        for (Item item : items) {
            if (item.getNom().equals(objetARetirer.getNom())) {
                item.getQuantite().set(item.getQuantite().get() - 1);
                if (item.getQuantite().get() <= 0) {
                    item.supprimer(items);
                }
                break;
            }
        }
    }

    public boolean contient(Objets objet) {
        for (Item item : items) {
            if (item.getObjet().equals(objet)) {
                return true;
            }
        }
        return false;
    }






}

