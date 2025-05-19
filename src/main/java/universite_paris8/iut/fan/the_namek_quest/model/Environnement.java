package universite_paris8.iut.fan.the_namek_quest.model;

import java.util.ArrayList;

public class Environnement {
    private Terrain terrain;
    private Trunks trunks;

    public Environnement() {
        this.terrain = new Terrain();
        this.trunks= new Trunks(this);

    }

    public Terrain getTerrain() {
        return terrain;
    }

    public Trunks getTrunks() {
        return trunks;
    }
}
