package universite_paris8.iut.fan.the_namek_quest.Algo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.fan.the_namek_quest.modele.Environnement;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Map {
    private Environnement environnement;
    private int hauteur;
    private int largeur;
    private java.util.Map<Position, Set<Position>> listeAdj;
    private ObservableList<Position> obstacles;

    public Map(Environnement environnement, int hauteur, int largeur) {
        this.environnement = environnement;
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.listeAdj = new HashMap();
        this.obstacles = FXCollections.observableArrayList();
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
        for(Position p : this.listeAdj.keySet()) {
            if (p.getX() == x && p.getY() == y) {
                return p;
            }
        }

        return null;
    }


    private boolean dansGrille(int x, int y) {
        return x >= 0 && x < this.hauteur && y >= 0 && y < this.largeur;
    }

    /*public Set<Position> adjacents(Position s) {
        return (Set<Position>)(!this.estDeconnecte(s) ? (Set)this.listeAdj.get(s) : new HashSet());
    }*/
}
