package universite_paris8.iut.fan.the_namek_quest.modele;

import universite_paris8.iut.fan.the_namek_quest.Constante;

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
    private PersonnageEnnemis personnageEnnemis;

    public Environnement() {
        this.terrain = new Terrain();
        this.trunks= new Trunks(this);
        this.personnageEnnemis = new PersonnageEnnemis(this);
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
    public PersonnageEnnemis getPersonnageEnnemis() {
        return this.personnageEnnemis;
    }

    public void update() {

        trunks.seDeplacer();
        personnageEnnemis.deplacement();
        if (!trunks.estEnSaut()) {
            int nouvelleY = this.gravite(trunks.getX(), trunks.getY());
            trunks.setY(nouvelleY);
        } else {
            trunks.gererSaut();
        }
        int nouvelleYEnnemis = this.gravite(personnageEnnemis.getX(), personnageEnnemis.getY());
        personnageEnnemis.setY(nouvelleYEnnemis);
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

    public int gravite(int x, int y) {
        if (!this.collisionBas(x, y)) {
            y += 2;
        }
        return y;
    }


}
