package universite_paris8.iut.fan.the_namek_quest.modele.personnage;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.fan.the_namek_quest.modele.Environnement;


/**
 * Classe PersonnageNonJoueur
 * ----------------------------
 * Représente un personnage non joueur (PNJ) dans le jeu .
 * Chaque PNJ a une position, une référence à l’environnement et peut interagir avec Trunks.
 *
 * Attributs :
 * - Position observable (x, y) pour l'affichage JavaFX
 * - Référence à l'environnement de jeu
 * - Référence à Trunks (joueur) obtenue depuis l’environnement
 *
 * Méthodes :
 * - Getters/Setters pour la position
 * - Détection de proximité avec Trunks (utile pour interactions)
 */

public class PersonnageNonJoueur {

    // Position observable
    private final IntegerProperty xProp;
    private final IntegerProperty yProp;

    // Références de l’environnement et de Trunks
    private final Environnement env;
    private final Trunks trunks;

    // Constructeur
    public PersonnageNonJoueur(int x, int y, Environnement env) {
        this.xProp = new SimpleIntegerProperty(x);
        this.yProp = new SimpleIntegerProperty(y);
        this.env = env;
        this.trunks = this.env.getTrunks();
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

    // --- Setters ---

    public void setX(int x) {
        this.xProp.set(x);
    }

    public void setY(int y) {
        this.yProp.set(y);
    }

    // --- Logique d'interaction ---

    /**
     * Vérifie si Trunks est à proximité horizontale du PNJ (entre -96 et +64 pixels en X)
     * Peut être utilisée pour déclencher des dialogues, soins, ou échanges.
     */
    public boolean trunksAProximite() {
        return trunks.getX() >= this.getX() - 96 && trunks.getX() <= this.getX() + 64;
    }
}