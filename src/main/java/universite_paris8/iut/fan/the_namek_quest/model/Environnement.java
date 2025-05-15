package universite_paris8.iut.fan.the_namek_quest.model;


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
        this.height = 720;
        this.width = 800;
    }

    public boolean dansTerrain(int x, int y){
        return (0 <= x && x<this.width && 0<=y && y< this.height);
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
