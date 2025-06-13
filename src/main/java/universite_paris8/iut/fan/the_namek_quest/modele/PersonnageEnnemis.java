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
        //int cibleY = y * Constante.TAILLE_TUILE;
        int vitesse = this.getVitesse();

        // Déplacement horizontal vers la prochaine case du chemin
        if (this.getX() < cibleX) {
            int newX = this.getX() + vitesse;
            if (newX > cibleX) newX = cibleX;
            if (!getEnv().collisionDroite(newX, this.getY())) {
                System.out.println("Collision droite");
                this.setX(newX);
            }
        } else if (this.getX() > cibleX) {
            int newX = this.getX() - vitesse;
            if (newX < cibleX) newX = cibleX;
            if (!getEnv().collisionGauche(newX, this.getY())) {
                System.out.println("Collision gauche");
                this.setX(newX);
            }
        }


    }
}





