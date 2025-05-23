package universite_paris8.iut.fan.the_namek_quest.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Personnage {

    private IntegerProperty xProp;
    private IntegerProperty yProp;
    private int vitesse;
    private Environnement env;
    private IntegerProperty pv;

    public Personnage( int x, int y, Environnement env) {

        this.xProp = new SimpleIntegerProperty(x);
        this.yProp = new SimpleIntegerProperty(y);
        this.vitesse = 1;
        this.env=env;
        this.pv = new SimpleIntegerProperty(99);
    }

    public boolean estMort(){
        return this.getPv() <= 0;
    }

    //getter
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
    public Environnement getEnv(){
        return  this.env;
    }
    public IntegerProperty getPvProp() {
        return this.pv;
    }
    public int getPv(){
        return this.pv.getValue();
    }

    //setter
    public void setX(int x) {
        this.xProp.setValue(x);
    }
    public void setY(int y) {
        this.yProp.setValue(y);
    }
    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }
    public void setPv(int pv) {
        this.pv.setValue(pv);
    }

    // Deplacement
    public void seDeplacer(){}
}
