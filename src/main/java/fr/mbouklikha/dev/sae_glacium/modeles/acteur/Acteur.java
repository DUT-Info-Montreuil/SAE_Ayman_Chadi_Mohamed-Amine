package fr.mbouklikha.dev.sae_glacium.modeles.acteur;

import fr.mbouklikha.dev.sae_glacium.modeles.monde.Environnement;
import javafx.animation.AnimationTimer;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Random;


public abstract class Acteur {

    private String nom;
    private int pv;
    private Environnement environnement;
    private IntegerProperty x, y;
    protected double vitesseY = 0;
    protected boolean enSaut = false;

    public Acteur(String nom, int pv, int x, int y, Environnement environnement) {
        this.nom = nom;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.pv=pv;
        this.environnement = environnement;
    }

    public Acteur(String nom, int pv, Environnement environnement) {
        this.nom = nom;
        this.pv = pv;
        this.x= new SimpleIntegerProperty(0);
        this.y = new SimpleIntegerProperty(0);
    }


    public String getNom(){
        return this.nom;
    }

    public int getPv() {
        return pv;
    }

    public int getX() {
        return x.getValue();
    }

    public IntegerProperty getXProperty(){
        return this.x;
    }

    public  void setX(int n){
        x.setValue(n);
    }

    public  int getY() {
        return y.getValue();
    }

    public IntegerProperty getYProperty(){
        return this.y;
    }

    public  void setY(int n){
        y.setValue(n);
    }

    public Environnement getEnvironnement(){
        return this.environnement;
    }



    public void decrementerPv(int n) {
        this.pv-=n;
    }

    public void incrementerPv(int n) {
        this.pv+=n;
    }




    public boolean estVivant() {
        return this.pv>0;
    }

    public void meurt(){
        this.pv=0;
    }

    public abstract void deplacer(KeyCode code);
    public abstract void appliquerGravite(int[][] map, int tailleBloc);

    public boolean estEnSaut() {
        return enSaut;
    }

    public void setEnSaut(boolean s) {
        enSaut = s;
    }


    @Override
    public String toString() {
        return "x=" + x + ", y=" + y;
    }




}
