package universite_paris8.iut.fan.the_namek_quest.model;

import universite_paris8.iut.fan.the_namek_quest.model.Inventaire.Inventaire;
import universite_paris8.iut.fan.the_namek_quest.model.Inventaire.Object;
import universite_paris8.iut.fan.the_namek_quest.model.Inventaire.arme.Epee;
import universite_paris8.iut.fan.the_namek_quest.model.Inventaire.outils.Hache;
import universite_paris8.iut.fan.the_namek_quest.model.Inventaire.outils.Pioche;

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
    private Object objectEquipe;
    private Inventaire inventaire;

    public Trunks(Environnement env) {
        super(0, 0, env);
        this.setVitesse(3);
        this.direction = 'h';// h => ne bouge pas

        this.inventaire = new Inventaire();
        this.objectEquipe = new Epee();
        this.inventaire.addObject(this.objectEquipe);
        this.inventaire.addObject(new Pioche());
        this.inventaire.addObject(new Hache());



    }
    public Object getObjectEquipe(){
        return this.objectEquipe;
    }

    public void setObjectEquipe(Object object){
        this.objectEquipe = object;
    }
    public void setDirection(char direction) {
        this.direction = direction;
    }

    /*public void setObjectEquipé(Object o){
        this.objectEquipe = o;
    }*/

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

    public void sauter(){
        int newY = getY() - 64;
        int yMax = this.getY() - 128;
        if (this.getEnv().getTerrain().collisionBas(getX(), getY())) {
            if (newY < 0) {
                newY = 0;
            }
            if (newY > yMax) {
                setY(newY);
            }
        }

    }

    public Inventaire getInventaire(){
        return this.inventaire;
    }

    public void changerEquipement(int sens){
        if(sens<0){
            if(this.objectEquipe.getId()>0){
                setObjectEquipe(this.getInventaire().getListObjects().get(1));
            }
        }else if(sens>0){
            if(this.objectEquipe.getId()<3){
                setObjectEquipe(this.getInventaire().getListObjects().get(2));
            }
        }

        this.objectEquipe.toString();
    }
}