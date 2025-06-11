package universite_paris8.iut.fan.the_namek_quest.modele;

import universite_paris8.iut.fan.the_namek_quest.Constante;
import universite_paris8.iut.fan.the_namek_quest.modele.personnage.Dende;
import universite_paris8.iut.fan.the_namek_quest.modele.personnage.GrandChef;
import universite_paris8.iut.fan.the_namek_quest.modele.personnage.Trunks;
import universite_paris8.iut.fan.the_namek_quest.modele.personnage.VieuxNamek;

/**
 * Classe Environnement
 * --------------------
 * Cette classe représente l'environnement global du jeu.
 * Elle contient le terrain et le personnage principal (Trunks),
 * et gère les interactions entre eux.
 */

public class Environnement {

    private Terrain terrain;
    private Trunks trunks;
    private GrandChef grandChef;
    private Dende dende;
    private VieuxNamek vieuxNamek;


    public Environnement() {
        this.terrain = new Terrain();
        this.trunks= new Trunks(this);
        this.grandChef = new GrandChef(450,513,this, this.trunks);
        this.dende = new Dende(700,513,this, this.trunks);
        this.vieuxNamek = new VieuxNamek(100,513, this,this.trunks);
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

    public GrandChef getGrandChef() {
        return grandChef;
    }

    public Dende getDende() {
        return dende;
    }

    public VieuxNamek getVieuxNamek() {
        return vieuxNamek;
    }

    public void update() {

        trunks.seDeplacer();
        if (!trunks.estEnSaut()) {
            int nouvelleY = trunks.gravite(trunks.getX(), trunks.getY());
            trunks.setY(nouvelleY);
        } else {
            trunks.gererSaut();
        }
        dende.apparitionOuDisparition();
       // vieuxNamek.apparitionOuDisparition();
    }

    public boolean collisionBas(int x, int y) {
        int yTest = y + Constante.HAUTEUR_PERSO;
        for (int testX = x + Constante.MARGE_GAUCHE; testX < x + Constante.LARGEUR_PERSO - Constante.MARGE_DROITE; testX++) {
            if (!getTerrain().estTraversable(testX, yTest)) {
                return true;
            }
        }
        return false;
    }

    public boolean collisionHaut(int x, int y) {
        for (int testX = x + Constante.MARGE_GAUCHE; testX < x + (Constante.LARGEUR_PERSO - Constante.MARGE_DROITE); testX++) {
            if (!getTerrain().estTraversable(testX, y - 1)) { // y - 1 pour tester le pixel juste au-dessus
                return true;
            }
        }
        return false;
    }

    public boolean collisionDroite(int x, int y) {
        int xTest = x + Constante.LARGEUR_PERSO - Constante.MARGE_DROITE;
        for (int testY = y; testY < y + Constante.HAUTEUR_PERSO; testY++) {
            if (!getTerrain().estTraversable(xTest, testY)) {
                return true;
            }
        }
        return false;
    }

    public boolean collisionGauche(int x, int y) {
        int xTest = x + Constante.MARGE_DROITE;
        for (int testY = y; testY < y + Constante.HAUTEUR_PERSO; testY++) {
            if (!getTerrain().estTraversable(xTest, testY)) {
                return true;
            }
        }
        return false;
    }

}
