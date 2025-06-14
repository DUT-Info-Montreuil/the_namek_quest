package universite_paris8.iut.fan.the_namek_quest.modele;

import universite_paris8.iut.fan.the_namek_quest.Constante;

public class PersonnageEnnemis extends Personnage{

    private String id;
    public static int compteur = 0;

    public PersonnageEnnemis(Environnement env) {
        super(250, 400, env);
        this.setVitesse(1);
        this.setDirection(0);
        this.id = "E" + compteur;
    }

    public PersonnageEnnemis(Environnement env ,int x,int y) {
        super(x, y, env);
        this.setVitesse(1);
        this.setDirection(0);
        this.id = "E" + compteur;
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
                this.setX(newX);
            }
        } else if (this.getX() > cibleX) {
            int newX = this.getX() - vitesse;
            if (newX < cibleX) newX = cibleX;
            if (!getEnv().collisionGauche(newX, this.getY())) {
                this.setX(newX);
            }
        }


    }

    public void deplacementAleatoire(){
        int x = (int) Math.random()*2;
        deplacement(this.getX()+(x*getVitesse()),this.getY());
    }

    public String getId() {
        return id;
    }

    public void attaque(){

    }
}





