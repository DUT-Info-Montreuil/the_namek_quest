package universite_paris8.iut.fan.the_namek_quest.Algo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.fan.the_namek_quest.modele.Environnement;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Grille {
    private Environnement environnement;
    private int hauteur;
    private int largeur;
    private java.util.Map<Position, Set<Position>> listeAdj;
    private ObservableList<Position> obstacles;

    public Grille(Environnement environnement, int hauteur, int largeur) {
        this.environnement = environnement;
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.listeAdj = new HashMap();
        this.obstacles = FXCollections.observableArrayList();
        construireMap();
    }

    public void construireMap() {
        for(int x=0; x<hauteur; x++) {
            for(int y=0; y<largeur; y++) {
                this.listeAdj.put(new Position(x,y),new HashSet<>());
            }
        }

        for(int i = 0; i < this.hauteur; ++i) {
            for(int j = 0; j < this.largeur; ++j) {
                Position s = this.getPosition(i, j);
                if (this.dansGrille(i - 1, j)) {

                        ((Set)this.listeAdj.get(s)).add(this.getPosition(i - 1, j));

                }

                if (this.dansGrille(i + 1, j)) {

                    ((Set)this.listeAdj.get(s)).add(this.getPosition(i + 1, j));
                }

                if (this.dansGrille(i, j + 1)) {

                        ((Set)this.listeAdj.get(s)).add(this.getPosition(i, j + 1));

                }

                if (this.dansGrille(i, j - 1)) {

                        ((Set)this.listeAdj.get(s)).add(this.getPosition(i, j - 1));
                }
            }
        }



    }



    public Position getPosition(int x, int y) {
        for (Position p : this.listeAdj.keySet()) {
            if (p.getX() == x && p.getY() == y) {
                return p;
            }
        }
        return null;
    }
    public boolean estDeconnecte(Position s) {
        return this.obstacles.contains(s);
    }

    public Set<Position> adjacents(Position s) {
        return (Set)(!this.estDeconnecte(s) ? (Set)this.listeAdj.get(s) : new HashSet());
    }

    private boolean dansGrille(int x, int y) {
        return x >= 0 && x < this.hauteur && y >= 0 && y < this.largeur;
    }

    public void poseObstacles() {
        for(int x=0; x<this.hauteur; x++) {
            for(int y=0; y<this.largeur; y++) {
                if(environnement.getTerrain().codeTuile(x,y)!=1){
                    this.obstacles.add(this.getPosition(x,y));
                }
            }
        }

        this.reconnecte(this.getPosition(0, 0));

    }

    public void reconnecte(Position s) {
        this.obstacles.remove(s);
    }

    public void deconnecte(Position s) {
        this.obstacles.add(s);
    }



    /*public Set<Position> adjacents(Position s) {
        return (Set<Position>)(!this.estDeconnecte(s) ? (Set)this.listeAdj.get(s) : new HashSet());
    }*/
}
