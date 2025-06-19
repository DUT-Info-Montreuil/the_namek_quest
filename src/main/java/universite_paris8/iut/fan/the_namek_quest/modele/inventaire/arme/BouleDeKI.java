package universite_paris8.iut.fan.the_namek_quest.modele.inventaire.arme;

/**
 * Classe BouleDeKI
 * -----------------
 * Représente une arme de type "Boule de Ki" dans l'inventaire du jeu.
 * Hérite de la classe Arme.
 */


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.fan.the_namek_quest.modele.Environnement;
import universite_paris8.iut.fan.the_namek_quest.modele.personnage.PersonnageEnnemis;

public class BouleDeKI extends Arme {

    private IntegerProperty xProp;
    private IntegerProperty yProp;
    private Environnement env;
    private BooleanProperty enAttaqueDistance;
    private int distanceParcourue = 0;
    private static final int PORTEE_MAX = 600;

    public BouleDeKI(int x, int y , Environnement environnement) {
        super(93290, "Boule de Ki", 20);
        this.env = environnement;
        this.xProp = new SimpleIntegerProperty(x);
        this.yProp = new SimpleIntegerProperty(y);
        this.enAttaqueDistance = new SimpleBooleanProperty(false);
        this.distanceParcourue = 0;
    }

    public Boolean getEnAttaqueDistance() {
        return enAttaqueDistance.getValue();
    }

    public BooleanProperty getEnAttaqueDistanceProperty() {
        return enAttaqueDistance;
    }

    public void setEnAttaqueDistance(Boolean enAttaqueDistance) {
        this.enAttaqueDistance.setValue(enAttaqueDistance);
    }

    public void deplacement() {
        int nouvelleX = getX() + 2;
        if (env.getTerrain().dansTerrain(nouvelleX, this.yProp.getValue())) {
            this.xProp.setValue(nouvelleX);
            distanceParcourue += 2;
        }
    }

    public void attaque() {
        deplacement();
        PersonnageEnnemis persotouché = env.trouverEnnemi(getX(), getY());
        if (persotouché != null) {
            persotouché.decrementerPv(getDegat());
            detruireBoule();
        } else if (distanceParcourue >= PORTEE_MAX) {
            detruireBoule();
        } else if (!env.getTerrain().estTraversable(getX(), getY())) {
            detruireBoule();
        }
    }

    private void detruireBoule() {
        this.xProp.setValue(-100);
        this.yProp.setValue(-100);
        this.setEnAttaqueDistance(false);
        this.distanceParcourue = 0;
    }

    public void reset(int x, int y) {
        this.setX(x);
        this.setY(y);
        setEnAttaqueDistance(true);
        this.distanceParcourue = 0;
    }

    public int getX() {
        return xProp.getValue();
    }
    public int getY() {
        return yProp.getValue();
    }
    public IntegerProperty getXProp() {
        return xProp;
    }
    public IntegerProperty getYProp() {
        return yProp;
    }
    public void setX(int x) {
        this.xProp.set(x);
    }
    public void setY(int y) {
        this.yProp.set(y);
    }

}
