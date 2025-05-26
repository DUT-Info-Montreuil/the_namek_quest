package universite_paris8.iut.fan.the_namek_quest.model.Inventaire;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventaire {
    private ObservableList<Object> inventaire;

    public Inventaire() {

        this.inventaire = FXCollections.observableArrayList();
    }

    public ObservableList<Object> getListObjects() {
        return inventaire;
    }

    public void setInventaire(ObservableList<Object> inventaire) {

        this.inventaire = inventaire;
    }
    public void addObject(Object o) {
        this.inventaire.add(o);
    }


    public void removeObject(Object object) {
        this.inventaire.remove(object);
    }
    public void removeObjectsIndex(int index) {
        this.inventaire.remove(index);
    }

    public int getIndexObject(Object object) {
        for (int i = 0; i < this.inventaire.size(); i++) {
            if (this.inventaire.get(i).equals(object)) {
                return i;
            }
        }
        return -1;
    }



}

