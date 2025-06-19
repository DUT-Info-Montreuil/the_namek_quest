package universite_paris8.iut.fan.the_namek_quest.Algo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.fan.the_namek_quest.modele.Environnement;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Grille {
    private Environnement environnement;
    private int hauteur;
    private int largeur;
    private Map<Position, Set<Position>> listeAdj;
    private ObservableList<Position> obstacles;
    private int debutx;
    private int debuty;

    private static final int OBSTACLE_CODE = 1;

    public Grille(Environnement environnement, int hauteur, int largeur, int debutX, int debutY) {
        this.environnement = environnement;
        this.debutx = Math.max(debutX, 0);
        this.debuty = Math.max(debutY, 0);
        this.hauteur = hauteur;
        this.largeur = largeur;

        this.listeAdj = new HashMap<>();
        this.obstacles = FXCollections.observableArrayList();
        refaireMap();
    }

    public void refaireMap() {
        listeAdj.clear();
        obstacles.clear();
        construireMap();
        poseObstacles();
    }

    public void construireMap() {
        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < hauteur; y++) {
                Position position = new Position(x, y);
                listeAdj.put(position, new HashSet<>());

                if (dansGrille(x - 1, y)) listeAdj.get(position).add(new Position(x - 1, y));
                if (dansGrille(x + 1, y)) listeAdj.get(position).add(new Position(x + 1, y));
                if (dansGrille(x, y - 1)) listeAdj.get(position).add(new Position(x, y - 1));
                if (dansGrille(x, y + 1)) listeAdj.get(position).add(new Position(x, y + 1));
            }
        }
    }

    public Position getPosition(int x, int y) {
        return listeAdj.keySet().stream()
                .filter(p -> p.getX() == x && p.getY() == y)
                .findFirst()
                .orElse(null);
    }

    public boolean estDeconnecte(Position s) {
        return obstacles.contains(s);
    }

    public Set<Position> adjacents(Position s) {
        return estDeconnecte(s) ? new HashSet<>() : listeAdj.getOrDefault(s, new HashSet<>());
    }

    private boolean dansGrille(int x, int y) {
        return x >= 0 && x < largeur && y >= 0 && y < hauteur;
    }

    public void poseObstacles() {
        obstacles.clear();
        for (int l = debuty; l < environnement.getTerrain().hauteurTerrain(); l++) {
            for (int c = debutx; c < environnement.getTerrain().largeurTerrain(); c++) {
                if (environnement.getTerrain().codeTuile(c, l) != OBSTACLE_CODE) {
                    obstacles.add(new Position(c, l));
                }
            }
        }
    }

    public void reconnecte(Position s) {
        obstacles.remove(s);
    }

    public void deconnecte(Position s) {
        if (!obstacles.contains(s)) {
            obstacles.add(s);
        }
    }
}