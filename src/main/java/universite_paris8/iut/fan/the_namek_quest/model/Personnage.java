package universite_paris8.iut.fan.the_namek_quest.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Personnage {

    private int pv;
    private IntegerProperty xProp;
    private IntegerProperty yProp;
    private int vitesse;
    private Environnement env;


    public Personnage(int pv, int x, int y, Environnement env) {
        this.pv = pv;
        this.xProp = new SimpleIntegerProperty(x);
        this.yProp = new SimpleIntegerProperty(y);
        this.vitesse = 1;
        this.env=env;
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

    public Environnement getEnv(){
        return  this.env;
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

    // Deplacement
    public void seDeplacer(int d) {}

    //Collision
    public boolean collisionHorizontale(int newX) {
        if(this.getEnv().getTerrain().codeTuile(newX,this.getY()/getVitesse()) == 1){
            return false;
        }
        return true;
    }

    public boolean collisionVerticale(int newY) {
        if(this.getEnv().getTerrain().getTerrain()[this.getX()][newY] == 2 /*|| this.getEnv().getTerrain().codeTuile(this.getX(), newY) == 3*/){
            return true;
        }
        return false;
    }
}
