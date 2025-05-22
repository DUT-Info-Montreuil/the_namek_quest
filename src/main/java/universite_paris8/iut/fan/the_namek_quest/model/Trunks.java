package universite_paris8.iut.fan.the_namek_quest.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.net.HttpCookie;

/**
 * Classe Trunks
 * -------------
 * Représente le personnage principal du joueur (Trunks).
 * - Stocke la position, la direction et la vitesse du personnage.
 * - Gère les déplacements (droite, gauche, saut) en tenant compte des collisions.
 * - Applique les commandes envoyées par le clavier.
 */

public class Trunks extends Personnage {

    private IntegerProperty KI;
    private char direction;

    public Trunks(Environnement env) {
        super(0, 0, env);
        this.setVitesse(3);
        this.direction = 'h'; // h => ne bouge pas
        this.KI = new SimpleIntegerProperty(50);
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public void seDeplacer() {
        int vitesse = getVitesse();
        Terrain terrain = this.getEnv().getTerrain();
        int x = this.getX();
        int y = this.getY();

        if (this.direction == 'd') {
            int newX = x + vitesse;
            if (terrain.dansTerrain(newX, y) && !terrain.collisionDroite(newX, y)) {
                setX(newX);
            }
            setDirection('h');
        } else if (this.direction == 'g') {
            int newX = x - vitesse;
            if (terrain.dansTerrain(newX, y) && !terrain.collisionGauche(newX, y)) {
                setX(newX);
            }
            setDirection('h');
        }
    }

    public void sauter(){
        int newY = getY() - 64;
        int yMax = this.getY() - 128;
        if (this.getEnv().getTerrain().collisionBas(getX(), getY())) {
            if (newY < 0) {
                newY = 0;
            }
            if (newY > yMax) {
                setY(newY);
            }
        }

    }

    public void setKI(int KI) {
        this.KI.setValue(KI);
    }

    public IntegerProperty getKI() {
        return this.KI;
    }

    public void increaseKI() {
        this.KI.set(this.KI.get() + 10);
    }
}