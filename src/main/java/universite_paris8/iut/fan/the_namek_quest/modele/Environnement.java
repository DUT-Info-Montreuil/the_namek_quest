package universite_paris8.iut.fan.the_namek_quest.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.fan.the_namek_quest.Algo.BFS;
import universite_paris8.iut.fan.the_namek_quest.Algo.Position;
import universite_paris8.iut.fan.the_namek_quest.Constante;
import universite_paris8.iut.fan.the_namek_quest.controlleur.ObservableEnnemis;

import java.util.ArrayList;

/**
 * Classe Environnement
 * --------------------
 * Cette classe représente l'environnement global du jeu.
 * Elle contient le terrain et le personnage principal (Trunks),
 * et gère les interactions entre eux.
 */

public class Environnement {

    private Terrain terrain;
    private Trunks trunks;
    private PersonnageEnnemis personnageEnnemis;
    private ObservableList<PersonnageEnnemis> personnageEnnemisList ;
    private BFS bfs;

    public Environnement() {
        this.terrain = new Terrain();
        this.trunks= new Trunks(this);
        this.personnageEnnemis = new PersonnageEnnemis(this);
        this.personnageEnnemisList = FXCollections.observableArrayList();
        this.bfs = new BFS(this);

    }

    public void ajouterEnnemi() {
       for(int i=0;i<3;i++) {
        this.personnageEnnemisList.add(new  PersonnageEnnemis(this,250 + i * 50, 400));
       }
    }

    public void supprimerEnnemi() {
        this.personnageEnnemis = null;
    }
    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public void setTrunks(Trunks trunks) {
        this.trunks = trunks;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public Trunks getTrunks() {
        return trunks;
    }
    public PersonnageEnnemis getPersonnageEnnemis() {
        return this.personnageEnnemis;
    }

    public ObservableList<PersonnageEnnemis> getPersonnageEnnemisList() {
        return personnageEnnemisList;
    }

    public PersonnageEnnemis trouverEnnemi(int xSouris, int ySouris) {
        for (PersonnageEnnemis ennemi : personnageEnnemisList) {
            if(ennemiTouche(ennemi.getX(),ennemi.getY(), xSouris, ySouris)) {
                return ennemi;
            }
        }
        return null;
    }


    public boolean ennemiTouche(int x, int y,int xSouris, int ySouris) {
        int xEnnemi = x;
        int yEnnemi = y ;
        return (xSouris >= xEnnemi && xSouris <= xEnnemi + Constante.TAILLE_TUILE &&
                ySouris >= yEnnemi && ySouris <= yEnnemi + Constante.TAILLE_TUILE);

    }

    public void update() {
        bfs = new BFS(this);

        if(!this.personnageEnnemisList.isEmpty()){
            for(PersonnageEnnemis p : this.personnageEnnemisList){
                //gavité l'ennemi
                int nouvelleYEnnemis = this.gravite(p.getX(), p.getY());
                p.setY(nouvelleYEnnemis);

                // Déplacement de l'ennemi
                Position  ennemiPos = new Position(p.getX() / Constante.TAILLE_TUILE, p.getY() / Constante.TAILLE_TUILE);
                Position cible = bfs.getNextMove(ennemiPos);
                if (cible != null && !caseOccupeeParEnnemi(cible.getX(), cible.getY(), p)) {
                    p.deplacement(cible.getX(), cible.getY());
                }else {
                    System.out.println("rentre ici");
                    p.deplacementAleatoire();
                }

                //Attaque de l'ennemi
//                if (trunksAProximite(p.getX(), p.getY())) {
//                    trunks.decrementerPv(10); // Trunks subit des dégâts
//                    System.out.println("Trunks a été attaqué par " + p.getId());
//                }
            }
        }

        trunks.seDeplacer();

        // Gestion de la gravité pour Trunks

        if (!trunks.estEnSaut()) {
            int nouvelleY = this.gravite(trunks.getX(), trunks.getY());
            trunks.setY(nouvelleY);
        } else {
            trunks.gererSaut();
        }

    }

    public boolean trunksAProximite(int x,int y) {
        return trunks.getX() >= x - 80 && trunks.getX() <= x + 50;
    }

    public boolean collisionBas(int x, int y) {
        int yTest = y + Constante.HAUTEUR_PERSO;
        for (int testX = x + Constante.MARGE_GAUCHE; testX < x + Constante.LARGEUR_PERSO - Constante.MARGE_DROITE; testX++) {
            if (!getTerrain().estTraversable(testX, yTest)) {
                return true;
            }
        }
        return false;
    }

    public boolean collisionHaut(int x, int y) {
        for (int testX = x + Constante.MARGE_GAUCHE; testX < x + (Constante.LARGEUR_PERSO - Constante.MARGE_DROITE); testX++) {
            if (!getTerrain().estTraversable(testX, y - 1)) { // y - 1 pour tester le pixel juste au-dessus
                return true;
            }
        }
        return false;
    }

    public boolean collisionDroite(int x, int y) {
        int xTest = x + Constante.LARGEUR_PERSO - Constante.MARGE_DROITE;
        for (int testY = y; testY < y + Constante.HAUTEUR_PERSO; testY++) {
            if (!getTerrain().estTraversable(xTest, testY)) {
                return true;
            }
        }
        return false;
    }

    public boolean collisionGauche(int x, int y) {
        int xTest = x + Constante.MARGE_DROITE;
        for (int testY = y; testY < y + Constante.HAUTEUR_PERSO; testY++) {
            if (!getTerrain().estTraversable(xTest, testY)) {
                return true;
            }
        }
        return false;
    }

    public int gravite(int x, int y) {
        if (!this.collisionBas(x, y)) {
            y += 2;
        }
        return y;
    }

    public boolean caseOccupeeParEnnemi(int xCase, int yCase, PersonnageEnnemis perso) {
        for (PersonnageEnnemis ennemi : personnageEnnemisList) {
            if (ennemi != perso &&
                    ennemi.getX() / Constante.TAILLE_TUILE == xCase &&
                    ennemi.getY() / Constante.TAILLE_TUILE == yCase) {
                return true;
            }
        }
        return false;
    }


}
