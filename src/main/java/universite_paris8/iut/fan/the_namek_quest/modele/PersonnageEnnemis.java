package universite_paris8.iut.fan.the_namek_quest.modele;

import universite_paris8.iut.fan.the_namek_quest.Constante;

public class PersonnageEnnemis extends Personnage{

    public PersonnageEnnemis(Environnement env) {
        super(250, 400, env);
        this.setVitesse(1);
        this.setDirection(0);
    }


    public void dectecteDirection(int xTrunks, int yTrunks) {
        if (this.getX() < xTrunks) {
            this.setDirection(1);
        } else if (this.getX() > xTrunks) {
            this.setDirection(-1);
        } else if (this.getY() == yTrunks) {
            this.setDirection(0);
        }
    }


    public void deplacement(int x, int y) {
        int cibleX = x * Constante.TAILLE_TUILE;
        int cibleY = y * Constante.TAILLE_TUILE;
        int vitesse = this.getVitesse();

        // Déplacement horizontal vers la prochaine case du chemin
        if (this.getX() < cibleX) {
            int newX = this.getX() + vitesse;
            if (newX > cibleX) newX = cibleX;
            if (!getEnv().collisionDroite(newX, this.getY())) {
                this.setX(newX);
            }
        } else if (this.getX() > cibleX) {
            int newX = this.getX() - vitesse;
            if (newX < cibleX) newX = cibleX;
            if (!getEnv().collisionGauche(newX, this.getY())) {
                this.setX(newX);
            }
        }

        // Monter si besoin (ex : échelle)
        if (this.getY() > cibleY) {
            int newY = this.getY() - vitesse;
            if (newY < cibleY) newY = cibleY;
            if (!getEnv().collisionHaut(this.getX(), newY)) {
                this.setY(newY);
            }
        }

        // La gravité gère la descente
        System.out.println("Position ennemis : " + this.getX() + " - " + this.getY());
    }
}





