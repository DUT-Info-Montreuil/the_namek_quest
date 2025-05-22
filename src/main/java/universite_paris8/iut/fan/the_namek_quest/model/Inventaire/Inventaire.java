package universite_paris8.iut.fan.the_namek_quest.model.Inventaire;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventaire {
    private ObservableList<Object> objects;

    public Inventaire() {

        this.objects = FXCollections.observableArrayList();
    }

    public ObservableList<Object> getObjects() {
        return objects;
    }

    public void setObjects(ObservableList<Object> objects) {
        this.objects = objects;
    }
    public void addObject(Object object) {
        this.objects.add(object);
    }
    public void removeObject(Object object) {
        this.objects.remove(object);
    }
}

