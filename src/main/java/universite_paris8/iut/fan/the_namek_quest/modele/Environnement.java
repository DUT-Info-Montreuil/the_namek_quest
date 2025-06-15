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
 * Elle contient le terrain, le personnage principal (Trunks),
 * ainsi que les PNJ (GrandChef, Dende, VieuxNamek),
 * et gère les interactions et les mises à jour du jeu.
 */

public class Environnement {

    // --- Attributs principaux ---
    private Terrain terrain;
    private Trunks trunks;
    private GrandChef grandChef;
    private Dende dende;
    private VieuxNamek vieuxNamek;

    // --- Constructeur ---
    public Environnement() {
        this.terrain = new Terrain();
        this.trunks = new Trunks(this);
        this.grandChef = new GrandChef(450, 720, this, this.trunks);
        this.dende = new Dende(700, 720, this, this.trunks);
        this.vieuxNamek = new VieuxNamek(this.trunks.getX() - 64, this.trunks.getY(), this, this.trunks);
    }

    // --- Getters et Setters ---
    public Terrain getTerrain() {
        return terrain;
    }

    public Trunks getTrunks() {
        return trunks;
    }

    public void setTrunks(Trunks trunks) {
        this.trunks = trunks;
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

    // --- Mise à jour globale de l'environnement (appelée à chaque frame) ---
    public void update() {
        trunks.seDeplacer();
        grandChef.setY(gravite(grandChef.getX(), grandChef.getY()));
        vieuxNamek.setY(gravite(vieuxNamek.getX(), vieuxNamek.getY()));
        if (!trunks.estEnSaut()) {
            int nouvelleY = trunks.gravite(trunks.getX(), trunks.getY());
            trunks.setY(nouvelleY);
        } else {
            trunks.gererSaut();
        }

        dende.apparitionOuDisparition();
        vieuxNamek.apparitionOuDisparition();
    }

    // --- Méthodes de détection de collision (marges comprises) ---

    /**
     * Vérifie s'il y a une collision sous le personnage (sol).
     * @param x Position X du personnage
     * @param y Position Y du personnage
     * @return true si une collision est détectée en bas
     */
    public boolean collisionBas(int x, int y) {
        int yTest = y + Constante.HAUTEUR_PERSO;

        for (int testX = x + Constante.MARGE_GAUCHE;
             testX < x + Constante.LARGEUR_PERSO - Constante.MARGE_DROITE;
             testX++) {
            if (!terrain.estTraversable(testX, yTest)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Vérifie s'il y a une collision au-dessus du personnage.
     * @param x Position X du personnage
     * @param y Position Y du personnage
     * @return true si une collision est détectée en haut
     */
    public boolean collisionHaut(int x, int y) {
        for (int testX = x + Constante.MARGE_GAUCHE;
             testX < x + Constante.LARGEUR_PERSO - Constante.MARGE_DROITE;
             testX++) {
            if (!terrain.estTraversable(testX, y - 1)) { // Juste au-dessus
                return true;
            }
        }

        return false;
    }

    /**
     * Vérifie s'il y a une collision à droite du personnage.
     * @param x Position X du personnage
     * @param y Position Y du personnage
     * @return true si une collision est détectée à droite
     */
    public boolean collisionDroite(int x, int y) {
        int xTest = x + Constante.LARGEUR_PERSO - Constante.MARGE_DROITE;

        for (int testY = y; testY < y + Constante.HAUTEUR_PERSO; testY++) {
            if (!terrain.estTraversable(xTest, testY)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Vérifie s'il y a une collision à gauche du personnage.
     * @param x Position X du personnage
     * @param y Position Y du personnage
     * @return true si une collision est détectée à gauche
     */
    public boolean collisionGauche(int x, int y) {
        int xTest = x + Constante.MARGE_DROITE;

        for (int testY = y; testY < y + Constante.HAUTEUR_PERSO; testY++) {
            if (!terrain.estTraversable(xTest, testY)) {
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
