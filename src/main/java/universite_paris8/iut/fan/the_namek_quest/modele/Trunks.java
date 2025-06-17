package universite_paris8.iut.fan.the_namek_quest.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.fan.the_namek_quest.Constante;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.Inventaire;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.*;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.Element;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.arme.Epee;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.outils.Hache;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.outils.MainVide;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.outils.Pioche;
import universite_paris8.iut.fan.the_namek_quest.modele.personnage.Personnage;

/**
 * Classe Trunks
 * -------------
 * Représente le personnage principal du joueur (Trunks).
 * - Stocke la position, la direction et la vitesse du personnage.
 * - Gère les déplacements (droite, gauche, saut) en tenant compte des collisions.
 * - Applique les commandes envoyées par le clavier.
 */

public class Trunks extends Personnage {

    // TODO remplacer par un IntegerProperty et un listener qui change l('image.
    private IntegerProperty direction;


    private boolean enSaut = false;
    private int hauteurMax = 0;
    private Element objectEquipe;
    private Inventaire inventaire;

    public Trunks(Environnement env) {
        super(33*33, 500, env);
        this.setVitesse(2);
        this.direction = new SimpleIntegerProperty(0); // 0 => ne bouge pas
        this.inventaire = new Inventaire();
        this.objectEquipe = new MainVide();
        this.inventaire.addObject(this.objectEquipe);
        this.inventaire.addObject(new Epee());
        this.inventaire.addObject(new Hache());
        this.inventaire.addObject(new Pioche());
    }


    public Object getObjectEquipe(){
        return this.objectEquipe;
    }

    public void setObjectEquipe(Element element){
        this.objectEquipe = element;
    }
    public void setDirection(int direction) {
        this.direction.setValue(direction);
    }

    public void seDeplacer() {

        int vitesse = getVitesse();
        Terrain terrain = this.getEnv().getTerrain();
        int x = this.getX();
        int y = this.getY();
 
        if (this.direction.getValue() == 1) {
            int newX = x + vitesse;
            if (terrain.dansTerrain(newX+Constante.MARGE_DROITE, y) && !getEnv().collisionDroite(newX, y)) {
                setX(newX);
            }

        } else if (this.direction.getValue() == -1) {
            int newX = x - vitesse;
            if (terrain.dansTerrain(newX, y) && !this.getEnv().collisionGauche(newX , y)) {
                setX(newX);
            }
        }

    }

   public void sauter() {
       if (!enSaut && this.getEnv().collisionBas(getX(), getY()) && !this.getEnv().collisionHaut(getX(), getY())) {
           enSaut = true;
           hauteurMax = 33;
       }
   }

    public void gererSaut() {
        if (enSaut) {
            int newY = getY() - 8;

            if (newY < 0 || !this.getEnv().collisionBas(getX(), newY)) {
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


    /*private double velocite;

    public void gererSaut2(){
        velocite = -12;
    }

    public void gravite2(){
            velocite += 0.5;
            int steps = (int) Math.abs(velocite);
            int direction = velocite > 0 ? 1 : -1;

            for (int i = 0; i < steps; i++) {
                if (direction > 0 && !getEnv().collisionBas(this.getX(), this.getY()+1) && !this.getEnv().collisionHaut(this.getX(), this.getY())) {
                    this.setY(this.getY() + 1);
                }
                else {
                    velocite = 0;
                    break;
                }

            }
    }*/

    public int getDirection() {
        return this.direction.getValue();
    }

    public IntegerProperty getDirectionProperty() {
        return this.direction;
    }
    public boolean estEnSaut() {
        return enSaut;
    }

    public void decrementerPv(){
        this.setPv(this.getPv() - 10);
    }

    public Inventaire getInventaire(){
        return this.inventaire;
    }

    public void changerEquipement(int sens){
        int indexEquipement=this.inventaire.getIndexObject(this.objectEquipe);

        if(sens<0){
            if(indexEquipement>0){
                setObjectEquipe(this.inventaire.getListObjects().get(indexEquipement-1));
            }
        } else if (sens>0) {
            if(indexEquipement<this.inventaire.getListObjects().size()-1){
                setObjectEquipe(this.inventaire.getListObjects().get(indexEquipement+1));
            }
        }
        this.objectEquipe.toString();
    }

    public int gravite(int x, int y) {
        if (!getEnv().collisionBas(x, y)) {
            y += 2;
        }
        return y;
    }
}