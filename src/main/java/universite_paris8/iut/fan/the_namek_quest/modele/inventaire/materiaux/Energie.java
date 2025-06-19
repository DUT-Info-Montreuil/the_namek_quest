package universite_paris8.iut.fan.the_namek_quest.modele.inventaire.materiaux;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.fan.the_namek_quest.modele.Environnement;
import universite_paris8.iut.fan.the_namek_quest.modele.personnage.Trunks;

public class Energie extends Materieau{
    private IntegerProperty x;
    private IntegerProperty y;
    private int porte;
    private Trunks trunks;
    private Environnement environnement;
    private DoubleProperty KI;


    public Energie() {
        super(4, "energie");
    }

    public Energie( Environnement environnement) {
        super(4,"energie");
        this.environnement = environnement;
        this.trunks = trunks;
        this.KI = new SimpleDoubleProperty(0);
        this.porte = 400;
    }

    public void setX(int x) {
        this.x.setValue(x);
    }

    public int getX() {
        return this.x.getValue();
    }

    public DoubleProperty getKI() {
        return this.KI;
    }

}
