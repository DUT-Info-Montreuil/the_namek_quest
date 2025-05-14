package universite_paris8.iut.fan.the_namek_quest.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Environnement {
    private Terrain terrain;
    private ArrayList<Personnage> personnages;
    private Trunks trunks;

    public Environnement(Terrain terrain) {
        this.terrain = terrain;
        this.personnages= new ArrayList<>();
        this.trunks= new Trunks();
    }



    public Terrain getTerrain() {
        return terrain;
    }
    public ArrayList<Personnage> getPersonnages() {
        return personnages;
    }
    public Trunks getTrunks() {
        return trunks;
    }

}
