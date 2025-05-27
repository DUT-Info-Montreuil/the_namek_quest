package universite_paris8.iut.fan.the_namek_quest.model;

/**
 * Classe Environnement
 * --------------------
 *
 *
 */

public class Environnement {
    private Terrain terrain;
    private Trunks trunks;

    public Environnement() {
        this.terrain = new Terrain();
        this.trunks= new Trunks(this);
    }
    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public void setTrunks(Trunks trunks) {
        this.trunks = trunks;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public Trunks getTrunks() {
        return trunks;
    }


    public void update() {
        trunks.seDeplacer();

        if (!trunks.estEnSaut()) {
            int nouvelleY = terrain.gravite(trunks.getX(), trunks.getY());
            trunks.setY(nouvelleY);
        } else {
            trunks.gererSaut();
        }

    }

}
