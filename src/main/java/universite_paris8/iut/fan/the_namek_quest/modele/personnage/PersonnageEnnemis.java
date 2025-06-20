package universite_paris8.iut.fan.the_namek_quest.modele.personnage;

import universite_paris8.iut.fan.the_namek_quest.Constante;
import universite_paris8.iut.fan.the_namek_quest.modele.Environnement;
import universite_paris8.iut.fan.the_namek_quest.modele.personnage.*;
/**
 * Classe représentant un personnage ennemi dans le jeu.
 * Hérite de {@link Personnage} et possède un identifiant unique.
 */
public class PersonnageEnnemis extends Personnage {

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

    /**
     * Déplace l'ennemi horizontalement vers la case cible (x, y).
     * @param x abscisse de la case cible
     * @param y ordonnée de la case cible (non utilisée ici)
     */
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
    /**
     * Déplace l'ennemi aléatoirement d'une case à gauche ou à droite.
     */

    public void deplacementAleatoire(){
        // 0 = gauche, 1 = droite
        int direction = (int) (Math.random() * 2);
        int caseX = this.getX() / Constante.TAILLE_TUILE;
        int caseY = this.getY() / Constante.TAILLE_TUILE;

        if (direction == 0) {
            // Gauche
            int cibleX = caseX - 1;
            if (!getEnv().collisionGauche(this.getX() - getVitesse(), this.getY())) {
                this.setX(this.getX() - getVitesse());
            }
        } else {
            // Droite
            int cibleX = caseX + 1;
            if (!getEnv().collisionDroite(this.getX() + getVitesse(), this.getY())) {
                this.setX(this.getX() + getVitesse());
            }
        }
    }

    public String getId() {
        return id;
    }

}




