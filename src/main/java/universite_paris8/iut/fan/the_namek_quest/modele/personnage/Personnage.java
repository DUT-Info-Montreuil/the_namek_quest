package universite_paris8.iut.fan.the_namek_quest.modele.personnage;

/**
 * Classe abstraite Personnage
 * ----------------------------
 * Représente un personnage du jeu "The Namek Quest".
 * Cette classe est la base de tous les personnages (joueurs ou PNJ).
 *
 * Caractéristiques :
 * - Position (x, y) observable via JavaFX (IntegerProperty)
 * - Points de vie (PV) également observables
 * - Vitesse de déplacement
 * - Référence à l'environnement dans lequel se trouve le personnage
 *
 * Méthodes principales :
 * - Accesseurs/mutateurs de la position, des PV, et de la vitesse
 * - Méthode abstraite de déplacement à surcharger
 * - Détection de la mort (PV <= 0)
 */

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.fan.the_namek_quest.modele.Environnement;

public abstract class Personnage {

    // Position observable
    private final IntegerProperty xProp;
    private final IntegerProperty yProp;

    // Points de vie observables
    private final IntegerProperty pv;

    // Vitesse de déplacement
    private int vitesse;

    // Environnement du personnage
    private final Environnement env;

    // Constructeur
    public Personnage(int x, int y, Environnement env) {
        this.xProp = new SimpleIntegerProperty(x);
        this.yProp = new SimpleIntegerProperty(y);
        this.pv = new SimpleIntegerProperty(100);
        this.vitesse = 1;
        this.env = env;
    }

    /** Indique si le personnage est mort (PV <= 0) */
    public boolean estMort() {
        return this.getPv() <= 0;
    }

    // --- Getters ---

    public int getX() {
        return xProp.get();
    }

    public int getY() {
        return yProp.get();
    }

    public IntegerProperty getXProp() {
        return xProp;
    }

    public IntegerProperty getYProp() {
        return yProp;
    }

    public int getPv() {
        return pv.get();
    }

    public IntegerProperty getPvProp() {
        return pv;
    }

    public int getVitesse() {
        return vitesse;
    }

    public Environnement getEnv() {
        return env;
    }

    // --- Setters ---

    public void setX(int x) {
        this.xProp.set(x);
    }

    public void setY(int y) {
        this.yProp.set(y);
    }

    public void setPv(int pv) {
        this.pv.set(pv);
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    // --- Déplacement ---
    public void seDeplacer() {}
}

