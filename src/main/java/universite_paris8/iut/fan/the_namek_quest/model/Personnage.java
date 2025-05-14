package universite_paris8.iut.fan.the_namek_quest.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Personnage {

    private int pv;
    private IntegerProperty xProp;
    private IntegerProperty yProp;
    private int vitesse;
    private Terrain terrain;

    public Personnage(int pv, int x, int y) {
        this.pv = pv;
        this.xProp = new SimpleIntegerProperty(x);
        this.yProp = new SimpleIntegerProperty(y);
    }


    //getter
    public int getPv() {
        return this.pv;
    }
    public int getX() {
        return this.xProp.getValue();
    }
    public int getY() {
        return this.yProp.getValue();
    }
    public IntegerProperty getXProp() {
        return this.xProp;
    }
    public IntegerProperty getYProp() {
        return this.yProp;
    }
    public int getVitesse() {
        return this.vitesse;
    }
    public Terrain getTerrain() {
        return this.terrain;
    }


    //setter
    public void setPv(int pv) {
        this.pv = pv;
    }
    public void setX(int x) {
        this.xProp.setValue(x);
    }
    public void setY(int y) {
        this.yProp.setValue(y);
    }
    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }
}
