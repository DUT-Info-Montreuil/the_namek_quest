package universite_paris8.iut.fan.the_namek_quest.model;

import universite_paris8.iut.fan.the_namek_quest.model.bloc.Bloc;

/**
 * Classe Terrain
 * ---------------
 * Représente le terrain du jeu sous forme de grille de tuiles (tableau 2D).
 * - Gère les types de tuiles (sol, herbe, ciel, nuage).
 * - Gère les détections de collisions entre le personnage et le terrain.
 * - Gère la gravité pour les personnages/joueurs.
 * - Vérifie si une case est traversable ou non.
 * - Contrôle que les coordonnées sont dans le terrain.
 */

public class Terrain {

    private Bloc bloc;
    private int width;
    private int height;
    private Bloc typeBloc;

    private static final int TAILLE_TUILE = 32;
    private static final int LARGEUR_PERSO = 32;
    private static final int HAUTEUR_PERSO = 32;

    // 1 => Ciel; 2 => Sol; 3 => Herbe; 4 => blanc (nuage)
    private int[][] terrain = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 1, 1, 1},
            {3, 1, 1, 3, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 3, 1, 1},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
    };

    public Terrain() {
        this.height = this.hauteurTerrain() * TAILLE_TUILE;
        this.width = this.largeurTerrain() * TAILLE_TUILE;
    }

    public int hauteurTerrain() {
        return terrain.length;
    }

    public int largeurTerrain() {
        return terrain[0].length;
    }

    public int codeTuile(int c, int l) {
        return terrain[l][c];
    }

    public int codeTuilePixel(int x, int y) {
        int l = y / TAILLE_TUILE;
        int c = x / TAILLE_TUILE;
        if (l < 0 || l >= terrain.length || c < 0 || c >= terrain[0].length) {
            return -1; // Hors du terrain
        }
        return terrain[l][c];
    }

    public int getCaseX(int xPixel) {
        return xPixel / TAILLE_TUILE;
    }

    public int getCaseY(int yPixel) {
        return yPixel / TAILLE_TUILE;
    }

    public boolean dansTerrain(int x, int y) {
        return (x >= 0 && x < width && y >= 0 && y < height);
    }

    // Détermine si la case (en pixels) est "marchable" (ciel, nuage)
    public boolean estMarchable(int xPixel, int yPixel) {
        int caseX = getCaseX(xPixel);
        int caseY = getCaseY(yPixel);

        if (caseY < 0 || caseY >= terrain.length || caseX < 0 || caseX >= terrain[0].length) {
            return false;
        }
        int code = terrain[caseY][caseX];

        return (code == 1 || code == 4);
    }

    public boolean collisionBas(int x, int y) {
        int yTest = y + HAUTEUR_PERSO;
        for (int testX = x; testX < x + LARGEUR_PERSO; testX++) {
            if (!estMarchable(testX, yTest)) {
                return true;
            }
        }
        return false;
    }

    public boolean collisionHaut(int x, int y) {
        int yTest = y;
        for (int testX = x; testX < x + LARGEUR_PERSO; testX++) {
            if (!estMarchable(testX, yTest)) {
                return true;
            }
        }
        return false;
    }

    public boolean collisionDroite(int x, int y) {
        int xTest = x + LARGEUR_PERSO;
        for (int testY = y; testY < y + HAUTEUR_PERSO; testY++) {
            if (!estMarchable(xTest, testY)) {
                return true;
            }
        }
        return false;
    }

    public boolean collisionGauche(int x, int y) {
        int xTest = x;
        for (int testY = y; testY < y + HAUTEUR_PERSO; testY++) {
            if (!estMarchable(xTest, testY)) {
                return true;
            }
        }
        return false;
    }

    public boolean collisionVerticale(int x, int yBas) {
        int colonne = x / TAILLE_TUILE;
        int ligne = yBas / TAILLE_TUILE;
        if (colonne < 0 || colonne >= largeurTerrain() || ligne < 0 || ligne >= hauteurTerrain()) {
            return true;
        }
        int code = codeTuile(colonne, ligne);
        return (code == 2 || code == 3);
    }

    public int gravite(int x, int y) {
        if (!collisionBas(x, y)) {
            y += 2;
        }
        return y;
    }
}