package universite_paris8.iut.fan.the_namek_quest.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class PersonnageNonJoueur {

    private IntegerProperty xProp;
    private IntegerProperty yProp;
    private Trunks trunks;
    private Environnement env;

    public PersonnageNonJoueur( int x, int y, Environnement env) {
        this.xProp = new SimpleIntegerProperty(x);
        this.yProp = new SimpleIntegerProperty(y);
        this.env=env;
        this.trunks = this.env.getTrunks();

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
    //setter
    public void setX(int x) {
        this.xProp.setValue(x);
    }
    public void setY(int y) {
        this.yProp.setValue(y);
    }
    public boolean trunksAProximite(){
        return trunks.getX() >= this.getX()-96 && trunks.getX() <= this.getX()+32;
    }
}
