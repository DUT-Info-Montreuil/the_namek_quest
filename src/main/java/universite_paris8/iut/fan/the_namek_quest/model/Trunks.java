package universite_paris8.iut.fan.the_namek_quest.model;

public class Trunks extends Personnage {

    private boolean enAir;
    private char direction;

    public Trunks(Environnement env) {
        super(200, 100, env);
        this.setVitesse(3);
        this.direction = 'h'; //h => ne bouge pas
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public void seDeplacer() {
        int vitesse = getVitesse();
        int largeur = this.getEnv().getTerrain().largeurTerrain();

        if (this.direction== 'd') {
            int newX = this.getX() + vitesse; //TODO gérer le déplacement de la façon suivante : losqu'on appuie sur une touche le personnage stocke sa direction. La gamloop dit au personnage de se déplacer
            int caseSuiv = (newX + largeur - 1) / 32;

            if (this.getEnv().getTerrain().dansTerrain(newX + largeur - 1, this.getY())) {
                if (!this.getEnv().getTerrain().collisionHorizontale(caseSuiv,this.getY())) {
                    setX(newX);
                }
            }
        } else if (this.direction== 'g') {
            int newX = this.getX() - vitesse;
            int caseSuiv = ((newX) / 32);

            if (this.getEnv().getTerrain().dansTerrain(newX, this.getY())) {
                if (caseSuiv >= 0 && !this.getEnv().getTerrain().collisionHorizontale(caseSuiv,this.getY())) {
                    setX(newX);
                }
            }
        }
    }
}
