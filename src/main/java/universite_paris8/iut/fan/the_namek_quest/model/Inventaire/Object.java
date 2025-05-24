package universite_paris8.iut.fan.the_namek_quest.model.Inventaire;

import universite_paris8.iut.fan.the_namek_quest.model.Inventaire.arme.Epee;
import universite_paris8.iut.fan.the_namek_quest.model.Inventaire.outils.Hache;
import universite_paris8.iut.fan.the_namek_quest.model.Inventaire.outils.Pioche;

public class Object {
    private int id;
    private String name;

    public Object(int id, String name) {
        this.id = id;
        this.name = name;
    }

   // public Object() {}

    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }


    public String toString(){
        return "id :"+this.id+"arme"+this.name;
    }
}

