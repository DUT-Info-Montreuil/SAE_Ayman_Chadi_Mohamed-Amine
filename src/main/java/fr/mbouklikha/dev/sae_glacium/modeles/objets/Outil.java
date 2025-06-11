package fr.mbouklikha.dev.sae_glacium.modeles.objets;

import fr.mbouklikha.dev.sae_glacium.modeles.monde.Terrain;

public abstract class Outil extends Objets {

    public Outil(String nom, Terrain terrain) {
        super(nom, terrain);

    }

    @Override
    public Item creerItem() {
        return new Item(this, 1);
    }

    public String getType() {
        return "Outil";
    }


}

