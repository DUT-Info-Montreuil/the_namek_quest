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
    /**
     * Constructeur
     * Initialise la grille avec ses dimensions et la position de départ.
     * Construit la carte et place les obstacles.
     */

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


    /**
     * Réinitialise la carte et les obstacles.
     * À appeler lorsqu'il y a un changement dans l'environnement.
     */
    public void refaireMap() {
        listeAdj.clear();
        obstacles.clear();
        construireMap();
        poseObstacles();
    }


    /**
     * Construit la liste d'adjacence pour chaque case de la grille.
     * Les cases adjacentes sont celles accessibles (haut, bas, gauche, droite).
     */
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




    /**
     * Indique si une position est considérée comme déconnectée (obstacle).
     */
    public boolean estDeconnecte(Position s) {
        return obstacles.contains(s);
    }


    /**
     * Retourne les positions adjacentes accessibles à partir de la position donnée.
     */
    public Set<Position> adjacents(Position s) {
        return estDeconnecte(s) ? new HashSet<>() : listeAdj.getOrDefault(s, new HashSet<>());
    }


    /**
     * Place les obstacles sur la grille en fonction du terrain de l'environnement.
     */
    private boolean dansGrille(int x, int y) {
        return x >= 0 && x < largeur && y >= 0 && y < hauteur;
    }

    /**
     * Place les obstacles sur la grille en fonction du terrain de l'environnement.
     */

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


}