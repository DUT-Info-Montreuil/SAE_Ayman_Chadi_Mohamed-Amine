package fr.mbouklikha.dev.sae_glacium.modeles.objets;

public abstract class Outil extends Objets {

    public Outil(String nom) {
        super(nom);

    }

    @Override
    public Item creerItem() {
        return new Item(this, 1);
    }

    public String getType() {
        return "Outil";
    }

    public abstract void fonction();

}

