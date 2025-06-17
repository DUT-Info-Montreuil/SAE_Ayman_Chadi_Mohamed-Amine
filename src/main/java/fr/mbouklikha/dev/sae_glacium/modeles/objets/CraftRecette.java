package fr.mbouklikha.dev.sae_glacium.modeles.objets;

public class CraftRecette {
    private Objets[][] grille; // 3x3
    private Objets resultat;

    public CraftRecette(Objets[][] grille, Objets resultat) {
        this.grille = grille;
        this.resultat = resultat;
    }

    public boolean correspond(Objets[][] autreGrille) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Objets attendu = grille[i][j];
                Objets donné = autreGrille[i][j];
                if ((attendu == null && donné != null) || (attendu != null && !attendu.equals(donné))) {
                    return false;
                }
            }
        }
        return true;
    }


    public Objets getResultat() {
        return resultat;
    }
}
