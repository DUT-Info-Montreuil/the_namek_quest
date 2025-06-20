package universite_paris8.iut.fan.the_namek_quest.modele.inventaire.materiaux;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.Element;

public abstract class Materieau extends Element {
    private IntegerProperty Quantite ;

    public Materieau(int id, String name) {
        super(id, name);
        this.Quantite = new SimpleIntegerProperty(1);
    }

    public IntegerProperty getQuantiteProp() {
        return this.Quantite;
    }

    public void incrementerRessource(){
        this.Quantite.setValue( this.Quantite.getValue() + 1);
    }

    public void decrementerRessource(){
        this.Quantite.setValue( this.Quantite.getValue() - 1);
    }

    public int getQuantite() {
        return Quantite.getValue();
    }
}
