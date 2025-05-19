package universite_paris8.iut.fan.the_namek_quest.model;

public class Trunks extends Personnage {

    private boolean enAir;
    public Trunks(Environnement env) {
        super(200, 250, 100, env);
        this.setVitesse(3);
    }

    public void seDeplacer(int d) {
        int vitesse = getVitesse();
        int largeur = this.getEnv().getTerrain().largeurTerrain();

        if (d == 0) {
            int newX = this.getX() + vitesse;
            int caseSuiv = (newX + largeur - 1) / 32;

            if (this.getEnv().dansTerrain(newX + largeur - 1, this.getY())) {
                if (!this.PasDeCollisionHorizontale(caseSuiv)) {
                    setX(newX);
                }
            }
        } else if (d == 1) {
            int newX = this.getX() - vitesse;
            int caseSuiv = ((newX) / 32);

            if (this.getEnv().dansTerrain(newX, this.getY())) {
                if (caseSuiv >= 0 && !this.PasDeCollisionHorizontale(caseSuiv)) {
                    setX(newX);
                }
            }
        }
    }
}
