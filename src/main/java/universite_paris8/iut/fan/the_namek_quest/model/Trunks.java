package universite_paris8.iut.fan.the_namek_quest.model;

public class Trunks extends Personnage {

    private char direction;

    public Trunks(Environnement env) {
        super(0, 0, env);
        this.setVitesse(3);
        this.direction = 'h'; //h => ne bouge pas
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
            if(terrain.dansTerrain(newX, y)) {
                // On vérifie la collision à droite à partir de la position actuelle
                if (!terrain.collisionDroite(x, y)) {
                    setX(newX);
                    setDirection('h');
                }
            }
        } else if (this.direction == 'g') {
            int newX = x - vitesse;
            if (terrain.dansTerrain(newX, y)) {
                // Vérifie la collision à gauche à partir de la position actuelle
                if (!terrain.collisionGauche(x, y)) {
                    setX(newX);
                    setDirection('h');
                }
            }
        }
    }

}
