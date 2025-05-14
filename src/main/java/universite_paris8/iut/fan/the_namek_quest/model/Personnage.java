package universite_paris8.iut.fan.the_namek_quest.model;

public abstract class Personnage {

    private int pv;
    private int x;
    private int y;
    private int vitesse;
    private Terrain terrain;

    public Personnage(int pv, int x, int y) {
        this.pv = pv;
        this.x = x;
        this.y = y;
    }


    //getter
    public int getPv() {
        return this.pv;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public int getVitesse() {
        return this.vitesse;
    }
    public Terrain getTerrain() {
        return this.terrain;
    }


    //setter
    public void setPv(int pv) {
        this.pv = pv;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }
}
