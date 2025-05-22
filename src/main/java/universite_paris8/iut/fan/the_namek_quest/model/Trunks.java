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
    private boolean enSaut = false;
    private int hauteurMax = 0; // Nombre de pixels à encore monter


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
       if (!enSaut && this.getEnv().getTerrain().collisionBas(getX(), getY())) {
           enSaut = true;
           hauteurMax = 33;
       }
   }

    public void gererSaut() {
        if (enSaut) {
            int newY = getY() - 7;

            if (newY < 0 || !this.getEnv().getTerrain().collisionBas(getX(), newY)) {
                setY(newY);
                hauteurMax -= 4;
                if (hauteurMax <= 0) {
                    enSaut = false;
                }
            } else {
                enSaut = false;
            }
        }
    }

    public boolean estEnSaut() {
        return enSaut;
    }

    public void decrementerPv(){
        this.setPv(this.getPv() - 10);
    }
}