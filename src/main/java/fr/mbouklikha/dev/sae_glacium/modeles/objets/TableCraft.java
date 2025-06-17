package fr.mbouklikha.dev.sae_glacium.modeles.objets;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;

import java.util.ArrayList;
import java.util.List;

public class TableCraft {
    private Objets[][] grille = new Objets[3][3];
    private List<CraftRecette> recettes;
    private Sid sid;

    Objets bois = new Bois(sid.getEnvironnement().getTerrain(), sid.getInventaire(), sid);
    Objets glace = new Glace(sid.getEnvironnement().getTerrain(), sid.getInventaire(), sid);
    Objets fer = new Fer(sid.getEnvironnement().getTerrain(), sid.getInventaire(), sid);
    Objets eclatFeu = new EclatFeu(sid.getEnvironnement().getTerrain(), sid.getInventaire(), sid);

    Objets pioche = new Pioche(sid.getEnvironnement().getTerrain(), sid.getInventaire(), sid);
    Objets dague = new Dague(sid.getEnvironnement().getTerrain(), sid.getInventaire(), sid);
    Objets arcFeu = new Arc(sid.getEnvironnement().getTerrain(), sid.getInventaire(), sid);

    public TableCraft(Sid sid) {
        this.sid = sid;
        recettes = new ArrayList<>();

        // Recette pioche : 1 glace et 1 bois
        Objets[][] recettePioche = {
                {null, bois, null},
                {null, glace, null},
                {null, null, null}
        };
        recettes.add(new CraftRecette(recettePioche, pioche));

        // Dague : 2 fer et 3 glace
        Objets[][] recetteDague = {
                {null, fer, null},
                {glace, glace, glace},
                {null, fer, null}
        };
        recettes.add(new CraftRecette(recetteDague, dague));

        // Arc feu
        Objets[][] recetteArcFeu = {
                {eclatFeu, null, null},
                {null, fer, fer},
                {bois, bois, bois}
        };
        recettes.add(new CraftRecette(recetteArcFeu, arcFeu));
    }

    public void placerObjet(int x, int y, Objets objet) {
        grille[y][x] = objet;
    }

    public Objets[][] getGrille() {
        return grille;
    }

    public Objets essayerCraft() {
        for (CraftRecette r : recettes) {
            if (r.correspond(grille)) {
                viderGrille(); // Enlève les objets utilisés
                return r.getResultat();
            }
        }
        return null;
    }

    public void viderGrille() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grille[i][j] = null;
            }
        }
    }
}
