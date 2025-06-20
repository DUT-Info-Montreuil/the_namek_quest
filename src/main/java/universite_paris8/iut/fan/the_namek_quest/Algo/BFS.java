package universite_paris8.iut.fan.the_namek_quest.Algo;

import universite_paris8.iut.fan.the_namek_quest.Constante;
import universite_paris8.iut.fan.the_namek_quest.modele.Environnement;

import java.util.*;

public class BFS {

    private Environnement env;
    private Position positionTrunks;
    private Grille grille;
    private Map<Position, Position> predecesseurs;

    /**
     * Constructeur
     * Initialise la position de Trunks, crée la grille locale, puis lance l’algorithme BFS.
     */
    public BFS(Environnement env) {
        this.env = env;
        this.predecesseurs = new HashMap<>();
        this.positionTrunks = calculerPositionTrunks();
        this.grille = creerGrilleAutourDeTrunks();
        algoBFS();
    }

    /**
     * Calcule la position de Trunks en cases (et non en pixels).
     */
    private Position calculerPositionTrunks() {
        return new Position(
                env.getTrunks().getX() / Constante.TAILLE_TUILE,
                env.getTrunks().getY() / Constante.TAILLE_TUILE
        );
    }

    /**
     * Crée une sous-grille centrée autour de Trunks pour optimiser le calcul du chemin.
     */
    private Grille creerGrilleAutourDeTrunks() {
        int x = env.getTrunks().getX();
        int y = env.getTrunks().getY();
        int taille = 10*Constante.TAILLE_TUILE;

        return new Grille(
                env,
                (y + taille) / Constante.TAILLE_TUILE,
                (x + taille) / Constante.TAILLE_TUILE,
                (x - taille) / Constante.TAILLE_TUILE,
                (y - taille) / Constante.TAILLE_TUILE
        );
    }


    /**
     * Exécute l’algorithme de parcours en largeur (BFS) depuis la position de Trunks.
     * Remplit la table des prédécesseurs pour chaque case atteignable.
     */
    public void algoBFS() {
        LinkedList<Position> fifo = new LinkedList<>();
        predecesseurs.clear();

        predecesseurs.put(positionTrunks, null);
        fifo.add(positionTrunks);

        while (!fifo.isEmpty()) {
            Position positionCourante = fifo.pollFirst();
            for (Position voisin : grille.adjacents(positionCourante)) {
                if (!predecesseurs.containsKey(voisin)) {
                    predecesseurs.put(voisin, positionCourante);
                    fifo.addLast(voisin);
                }
            }
        }
    }

    /**
     * Retourne la prochaine case à atteindre pour aller de la position cible vers Trunks.
     * @param cible Position de départ (souvent celle d’un ennemi)
     * @return Prochaine position à atteindre, ou null si inatteignable
     */
    public Position getNextMove(Position cible) {
        if (!predecesseurs.containsKey(cible)) {
            return null; // Cible inatteignable
        }

        List<Position> chemin = new ArrayList<>();
        Position positionCourante = cible;

        while (positionCourante != null) {
            chemin.add(positionCourante);
            positionCourante = predecesseurs.get(positionCourante);
        }

        //Collections.reverse(chemin);
        return chemin.size() > 1 ? chemin.get(1) : chemin.get(0);
    }

    /**
     * Met à jour l’environnement, recalcule la position de Trunks et relance le BFS.
     */
    public void setEnv(Environnement env) {
        this.env = env;
        this.positionTrunks = calculerPositionTrunks();
        this.grille.refaireMap();
        algoBFS();
    }
}