package universite_paris8.iut.fan.the_namek_quest.modele.inventaire.materiaux;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.Object;

public abstract class Materieau extends Object {
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

    public void setQuantite(IntegerProperty Quantite) {
        this.Quantite = Quantite;
    }

    public int getQuantite() {
        return Quantite.getValue();
    }

    public void setQuantite(int Quantite) {
        this.Quantite.setValue(Quantite);
    }


}
