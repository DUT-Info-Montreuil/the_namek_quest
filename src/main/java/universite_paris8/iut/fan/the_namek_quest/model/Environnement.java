package universite_paris8.iut.fan.the_namek_quest.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Environnement {
    private Terrain terrain;
    private ArrayList<Personnage> personnages;
    private Trunks trunks;
    private int width;
    private int height;

    public Environnement() {
        this.terrain = new Terrain();
        this.personnages= new ArrayList<Personnage>();
        this.trunks= new Trunks(this);
        this.height = (terrain.hauteurTerrain()-1)*16;
        this.width = (terrain.largeurTerrain()-1)*16 ;
    }


    public boolean dansTerrain(int x, int y){
        System.out.println("dans Terrain "+x+" "+y);
        return (20 < x && x<=this.width - 20 && 16 <= y && y< this.height - 16);
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
