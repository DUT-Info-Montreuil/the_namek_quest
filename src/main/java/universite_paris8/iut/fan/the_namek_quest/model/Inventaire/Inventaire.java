package universite_paris8.iut.fan.the_namek_quest.model.Inventaire;

import java.util.ArrayList;

public class Inventaire {
    private ArrayList<Object> objects;

    public Inventaire() {
        this.objects = new ArrayList<>();
    }

    public ArrayList<Object> getObjects() {
        return objects;
    }

    public void setObjects(ArrayList<Object> objects) {
        this.objects = objects;
    }
    public void addObject(Object object) {
        this.objects.add(object);
    }
    public void removeObject(Object object) {
        this.objects.remove(object);
    }
}

