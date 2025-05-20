package universite_paris8.iut.fan.the_namek_quest.model;

/**
 * Classe Trunks
 * -------------
 * Représente le personnage principal du joueur (Trunks).
 * - Stocke la position, la direction et la vitesse du personnage.
 * - Gère les déplacements (droite, gauche, saut) en tenant compte des collisions.
 * - Applique les commandes envoyées par le clavier.
 */

public class Trunks extends Personnage {

    private char direction;

    public Trunks(Environnement env) {
        super(0, 0, env);
        this.setVitesse(3);
        this.direction = 'h'; // h => ne bouge pas
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public void seDeplacer() {
        int vitesse = getVitesse();
        Terrain terrain = this.getEnv().getTerrain();
        int x = this.getX();
        int y = this.getY();

        if (this.direction == 'd') {
            int newX = x + vitesse;
            if (terrain.dansTerrain(newX, y) && !terrain.collisionDroite(newX, y)) {
                setX(newX);
            }
            setDirection('h');
        } else if (this.direction == 'g') {
            int newX = x - vitesse;
            if (terrain.dansTerrain(newX, y) && !terrain.collisionGauche(newX, y)) {
                setX(newX);
            }
            setDirection('h');
        }
    }

    public void sauter() {
        int newY = getY() - 8;
        // Empêche de sortir par le haut
        if (newY < 0) {
            newY = 0;
        }
        setY(newY);
    }
}