package universite_paris8.iut.fan.the_namek_quest.modele.personnage;

import javafx.beans.property.*;
import universite_paris8.iut.fan.the_namek_quest.Constante;
import universite_paris8.iut.fan.the_namek_quest.modele.Environnement;
import universite_paris8.iut.fan.the_namek_quest.modele.Terrain;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.Inventaire;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.arme.BouleDeKI;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.outils.MainVide;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.Element;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.arme.Epee;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.materiaux.Materieau;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.outils.Hache;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.outils.Pioche;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.materiaux.Energie;

/**
 * Classe Trunks
 * -------------
 * Représente le personnage principal du joueur dans le jeu "The Namek Quest".
 * Hérite de la classe abstraite Personnage.
 *
 * Fonctions principales :
 * - Gère le déplacement horizontal du joueur (droite/gauche)
 * - Gère le saut et l'application de la gravité
 * - Gère l'équipement (changement d’objet équipé)
 * - Permet de consommer un haricot magique pour se soigner
 * - Possède un inventaire avec des objets et ressources
 */

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.fan.the_namek_quest.modele.*;

public class Trunks extends Personnage {

    private IntegerProperty direction;        // -1 = gauche, 0 = immobile, 1 = droite
    private boolean enSaut = false;
    private int hauteurMax = 0;
    private Element objectEquipe;
    private Inventaire inventaire;
    private DoubleProperty KI;
    private BouleDeKI bouleDeKI;

    /**
     * Constructeur de Trunks
     * Initialise la position, l’inventaire de base, la vitesse, la direction, et l’objet équipé.
     */
    public Trunks(Environnement env) {
        super(300, 30, env);
        this.setVitesse(2);
        this.direction = new SimpleIntegerProperty(0);
        this.inventaire = new Inventaire();
        this.KI = new SimpleDoubleProperty(getEnv().getKI());
        this.bouleDeKI =new BouleDeKI(getX(), getY(), getEnv());


        // Par défaut, Trunks commence avec une main vide + 3 outils de base
        this.objectEquipe = new MainVide();
        this.inventaire.addObject(this.objectEquipe);
        this.inventaire.addObject(new Epee());
        this.inventaire.addObject(new Hache());
        this.inventaire.addObject(new Pioche());
    }


    // --- Déplacement horizontal ---

    /**
     * Met à jour la position horizontale de Trunks selon sa direction (gauche ou droite)
     * Vérifie les collisions à droite et à gauche.
     */
    public boolean seDeplacerT() {
        int vitesse = getVitesse();
        Terrain terrain = this.getEnv().getTerrain();
        int x = this.getX();
        int y = this.getY();



        if (this.direction.get() == 1) { // droite
            int newX = x + vitesse;
            if (terrain.dansTerrain(newX + Constante.MARGE_DROITE, y) && !getEnv().collisionDroite(newX, y)) {
                setX(newX);
            }

        } else if (this.direction.get() == -1) { // gauche
            int newX = x - vitesse;
            if (terrain.dansTerrain(newX, y) && !getEnv().collisionGauche(newX, y)) {
                setX(newX);
            }
        }

        if(this.direction.getValue() ==0){
            // Si Trunks est immobile, on ne fait rien
            return false;
        }
        return true;
    }

    // --- Saut & Gravité ---

    /**
     * Lance le saut si Trunks est au sol et qu'il n’est pas déjà en saut.
     */
    public void sauter() {
        if (!enSaut && getEnv().collisionBas(getX(), getY()) && !getEnv().collisionHaut(getX(), getY())) {
            enSaut = true;
            hauteurMax = 33;
        }
    }

    /**
     * Fait monter Trunks dans les airs jusqu'à atteindre la hauteur maximale ou une collision.
     */
    public void gererSaut() {
        if (enSaut) {
            int newY = getY() - 8;
            if (newY < 0 || !getEnv().collisionBas(getX(), newY)) {
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

    /**
     * Simule la gravité : si Trunks ne touche pas le sol, il descend.
     */
    public int gravite(int x, int y) {
        if (!getEnv().collisionBas(x, y)) {
            y += 2;
        }
        return y;
    }

    // --- Equipement & Inventaire ---

    public Element getObjectEquipe() {
        return objectEquipe;
    }

    public void setObjectEquipe(Element object) {
        this.objectEquipe = object;
    }

    public Inventaire getInventaire() {
        return this.inventaire;
    }

    /**
     * Permet de changer l’objet équipé selon le sens donné (-1 = précédent, +1 = suivant).
     */
    public void changerEquipement(int sens) {
        int indexEquipement = inventaire.getIndexObject(this.objectEquipe);

        if (sens < 0 && indexEquipement > 0) {
            setObjectEquipe(inventaire.getListObjects().get(indexEquipement - 1));
        } else if (sens > 0 && indexEquipement < inventaire.getListObjects().size() - 1) {
            setObjectEquipe(inventaire.getListObjects().get(indexEquipement + 1));
        }

        this.objectEquipe.toString(); // Appel utile si toString déclenche un affichage
    }

    // --- Vie & Soin ---

    /**
     * Diminue la vie de Trunks de 10 PV.
     */
    public void decrementerPv() {
        this.setPv(this.getPv() - 10);
    }

    /**
     * Soigne Trunks avec un haricot magique (id 6), si équipé et s’il lui manque de la vie.
     */
    public void mangerHaricot() {
        int pvActuel = getPv();
        if (objectEquipe.getId() == 6 && pvActuel < 100) {
            int pvManquant = 100 - pvActuel;
            setPv(pvActuel + pvManquant);
            Materieau materieau = (Materieau) objectEquipe;
            if (materieau.getQuantite() > 0) {
                materieau.decrementerRessource();
            }
        }
    }

    // --- Direction ---

    public int getDirection() {
        return this.direction.get();
    }

    public void setDirection(int direction) {
        this.direction.set(direction);
    }

    public IntegerProperty getDirectionProperty() {
        return this.direction;
    }

    public boolean estEnSaut() {
        return enSaut;
    }


    public void attaquerBouleDeKi(){

        if (!this.bouleDeKI.getEnAttaqueDistance()){
            this.bouleDeKI.reset(getX(), getY());
            this.bouleDeKI.setEnAttaqueDistance(true);
        }
        this.bouleDeKI.attaque();
        //System.out.println(bouleDeKI.getEnAttaqueDistance());

    }

    public BouleDeKI getBouleDeKI() {
        return bouleDeKI;
    }

}