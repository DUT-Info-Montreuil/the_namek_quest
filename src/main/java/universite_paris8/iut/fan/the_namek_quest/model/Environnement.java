package universite_paris8.iut.fan.the_namek_quest.model;

import java.util.ArrayList;

public class Environnement {
    private Terrain terrain;
    private ArrayList<Personnage> personnages;
    private Trunks trunks;
    private int width;//TODO dans Terrain
    private int height;

    public Environnement() {
        this.terrain = new Terrain();
        this.personnages= new ArrayList<Personnage>();
        this.trunks= new Trunks(this);
        this.height = terrain.hauteurTerrain()*31;
        this.width = terrain.largeurTerrain()*31 ;
    }


    public boolean dansTerrain(int x, int y){
        return (0 <= x && x<this.width  && 0<=y && y< this.height ); // TODO déplacer dans Terrain
    }

    public Terrain getTerrain() {
        return terrain;
    }
    public ArrayList<Personnage> getPersonnages() {
        return personnages;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Trunks getTrunks() {
        return trunks;
    }
}
