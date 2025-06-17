package universite_paris8.iut.fan.the_namek_quest.modele.inventaire.arme;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.fan.the_namek_quest.modele.Environnement;
import universite_paris8.iut.fan.the_namek_quest.modele.personnage.PersonnageEnnemis;

public class BouleDeKI extends Arme{

    /**
     * Classe BouleDeKI
     * -----------------
     * Représente une arme de type "Boule de Ki" dans l'inventaire du jeu.
     * Hérite de la classe Arme.
     */

    private IntegerProperty xProp; // Position X de la boule de Ki
    private IntegerProperty yProp; // Position Y de la boule de Ki
    private Environnement env;
    private Boolean enAttaqueDistance= false; // Indique si la boule de Ki est en train d'attaquer à distance

    public BouleDeKI(int x, int y , Environnement environnement) {
        super(93290, "Boule de Ki", 5);
        this.env = environnement;
        this.xProp = new SimpleIntegerProperty(x);
        this.yProp = new SimpleIntegerProperty(y);
    }
    public Boolean getEnAttaqueDistance() {
        return enAttaqueDistance;
    }

    public void setEnAttaqueDistance(Boolean enAttaqueDistance) {
        this.enAttaqueDistance = enAttaqueDistance;
    }

    public void deplacement() {
        // Implémentation du déplacement de la Boule de Ki
        // Par exemple, vous pouvez mettre à jour les coordonnées de la boule
        // en fonction de la direction et de la vitesse.
        this.xProp.setValue(getX()+2);
    }

    public void attaque(){
        System.out.println("rentre dans la méthode attaque de BouleDeKI"+
                " x = " + getX() +
                " y = " + getY() +
                " direction = " + env.getTrunks().getDirection());
        deplacement();
        PersonnageEnnemis persotouché = env.trouverEnnemi(getX(),getY());

        if (persotouché != null) {
            // Logique pour vérifier si la boule de Ki touche un ennemi
            // Si oui, infliger des dégâts à l'ennemi
            System.out.println("persotouché = " + persotouché.toString());
            System.out.println("ennemi touché");
            persotouché.decrementerPv(getDegat());
            System.out.println("Boule de Ki détruite");
            this.xProp.setValue(-100); // Position hors écran pour simuler la destruction
            this.yProp.setValue(-100);
            this.setEnAttaqueDistance(false);


        } else {
            // Si la boule de Ki touche un mur ou un obstacle, elle est détruite

        }

//        while(env.getTerrain().estTraversable(getX()+(env.getTrunks().getDirection()*2), getY())  ){
//
//        }
//        if(env.getTerrain().estTraversable(getX()+(env.getTrunks().getDirection()*2), getY())) {
//
//
//        }else {
//            // Si la boule de Ki touche un mur ou un obstacle, elle est détruite
//            System.out.println("Boule de Ki détruite");
//            this.xProp.setValue(-100); // Position hors écran pour simuler la destruction
//            this.yProp.setValue(-100);
//        }
    }

    public int getX() {
        return xProp.getValue();
    }
    public int getY() {
        return yProp.getValue();
    }
    public IntegerProperty getXProp() {
        return xProp;
    }
    public IntegerProperty getYProp() {
        return yProp;
    }
    public void setX(int x) {
        this.xProp.set(x);
    }
    public void setY(int y) {
        this.yProp.set(y);
    }

}
