package universite_paris8.iut.fan.the_namek_quest.modele;

import universite_paris8.iut.fan.the_namek_quest.Constante;

/**
 * Classe Terrain
 * ---------------
 * Représente le terrain du jeu sous forme d'une grille 2D de tuiles.
 *
 * Chaque case est codée par un entier correspondant à un type de tuile :
 * - 1 : Ciel (traversable)
 * - 2 : Sol (non traversable)
 * - 3 : Herbe (non traversable)
 * - 6 : haricot
 * - 8 : boule de cristal
 * - 9 : Roche
 * - 10 : tronc d'arbre (non traversable)
 * - 11 : feuillage (non traversable)
 *
 * Gère :
 * - la taille du terrain en pixels,
 * - l'accès aux codes des tuiles par coordonnées en cases ou en pixels,
 * - la vérification si une position est traversable,
 * - la modification des tuiles (poser, casser),
 * - la gestion d'une zone de creusage autour du personnage.
 */


public class Terrain {

    // Largeur et hauteur du terrain en pixels
    private final int width;
    private final int height;

    // Taille d'une tuile en pixels (ex : 32)
    // Constante utilisée dans la classe (à définir ailleurs)
    // Exemple : public static final int TAILLE_TUILE = 32;

    // Représentation 2D du terrain par codes de tuiles (lignes x colonnes)
    private final int[][] terrain = {
            // 20 lignes, 25 colonnes
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
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 11, 1, 3, 3, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1},
            {1, 1, 1, 10, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 3, 2, 3, 1, 1, 1},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 3, 3, 2, 2, 2, 3, 3, 3},
            {2, 2, 2, 6, 2, 2, 9, 2, 2, 2, 2, 8, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2, 2},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 3, 3, 3},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
    };

    /**
     * Constructeur : initialise la largeur et hauteur du terrain en pixels.
     */
    public Terrain() {
        this.height = hauteurTerrain() * Constante.TAILLE_TUILE;
        this.width = largeurTerrain() * Constante.TAILLE_TUILE;
    }


    /**
     * Retourne la hauteur du terrain en nombre de cases (lignes).
     */
    public int hauteurTerrain() {
        return terrain.length;
    }

    /**
     * Retourne la largeur du terrain en nombre de cases (colonnes).
     */
    public int largeurTerrain() {
        return terrain[0].length;
    }

    /**
     * Retourne le code de la tuile à la position (c, l) en cases.
     * @param c colonne (case)
     * @param l ligne (case)
     * @return code entier de la tuile
     */
    public int codeTuile(int c, int l) {
        return terrain[l][c];
    }

    /**
     * Retourne le code de la tuile correspondant à une position en pixels (x, y).
     * Si en dehors du terrain, retourne -1.
     * @param x position en pixels sur l'axe horizontal
     * @param y position en pixels sur l'axe vertical
     * @return code de la tuile ou -1 si hors limites
     */
    public int codeTuilePixel(int x, int y) {
        int l = y / Constante.TAILLE_TUILE;
        int c = x / Constante.TAILLE_TUILE;

        if (l < 0 || l >= hauteurTerrain() || c < 0 || c >= largeurTerrain()) {
            return -1; // hors terrain
        }
        return terrain[l][c];
    }

    /**
     * Retourne la colonne (case) correspondant à une coordonnée pixel x.
     */
    public int getCaseX(int xPixel) {
        return xPixel / Constante.TAILLE_TUILE;
    }

    /**
     * Retourne la ligne (case) correspondant à une coordonnée pixel y.
     */
    public int getCaseY(int yPixel) {
        return yPixel / Constante.TAILLE_TUILE;
    }

    /**
     * Vérifie si les coordonnées (en pixels) sont à l'intérieur du terrain.
     * @param x position horizontale en pixels
     * @param y position verticale en pixels
     * @return true si dans le terrain, false sinon
     */
    public boolean dansTerrain(int x, int y) {
        return (x >= 0 && x < width && y >= 0 && y < height);
    }
    public boolean dansTerrainModel(int x, int y) { return (x >= 0 && x < hauteurTerrain() && y >= 0 && y < largeurTerrain());}

    /**
     * Modifie la tuile en position (c, l) en ciel (code 1).
     * @param c colonne (case)
     * @param l ligne (case)
     */
    public void setTuileCiel(int c, int l) {
        this.terrain[l][c] = 1;
    }

    /**
     * Modifie la tuile en position (c, l) avec le type donné.
     * @param c colonne (case)
     * @param l ligne (case)
     * @param typeTuile code entier du type de tuile à poser
     */
    public void setTuile(int c, int l, int typeTuile) {
        this.terrain[l][c] = typeTuile;
    }

    /**
     * Indique si la position en pixels (xPixel, yPixel) est traversable.
     * Exemples : ciel et nuage sont traversables, murs non.
     *
     * Pour le code 10 (mur), la zone proche des bords est traversable.
     *
     * @param xPixel position horizontale en pixels
     * @param yPixel position verticale en pixels
     * @return true si traversable, false sinon
     */
    public boolean estTraversable(int xPixel, int yPixel) {
        int caseX = getCaseX(xPixel);
        int caseY = getCaseY(yPixel);

        if (caseY < 0 || caseY >= hauteurTerrain() || caseX < 0 || caseX >= largeurTerrain()) {
            return false;
        }

        int code = terrain[caseY][caseX];

        if (code == 1 /* ciel */ || code == 4 /* nuage */) {
            return true;
        }

        if (code == 10 /* mur */) {
            int decalageX = xPixel % Constante.TAILLE_TUILE;

            // Zone "vide" proche des bords gauche/droite du mur : traversable
            return decalageX < Constante.MARGE_GAUCHE_MUR || decalageX >= (Constante.TAILLE_TUILE - Constante.MARGE_DROITE_MUR);
        }

        return false;
    }

    /**
     * Indique si la position de la souris (xSouris, ySouris) est dans la zone
     * de creusage autour de Trunks, mais pas sur Trunks lui-même.
     *
     * @param xTrunks position X (pixels) de Trunks
     * @param yTrunks position Y (pixels) de Trunks
     * @param xSouris position X (pixels) de la souris
     * @param ySouris position Y (pixels) de la souris
     * @return true si on peut creuser, false sinon
     */
    public boolean rangeCreuser(int xTrunks, int yTrunks, double xSouris, double ySouris) {
        boolean dansX = xSouris >= xTrunks - Constante.TAILLE_TUILE && xSouris <= xTrunks + 2 * Constante.TAILLE_TUILE;
        boolean dansY = ySouris >= yTrunks - Constante.TAILLE_TUILE && ySouris <= yTrunks + 2 * Constante.TAILLE_TUILE;
        boolean surTrunks = xSouris >= xTrunks && xSouris <= xTrunks + Constante.TAILLE_TUILE
                && ySouris >= yTrunks && ySouris <= yTrunks + Constante.TAILLE_TUILE;

        return (dansX && dansY) && !surTrunks;
    }

    /**
     * Casse le bloc à la position donnée par la souris, en la remplaçant par du ciel.
     * @param xSouris position X en pixels
     * @param ySouris position Y en pixels
     */
    public void casserBloc(double xSouris, double ySouris) {
        int c = (int) (xSouris / Constante.TAILLE_TUILE);
        int l = (int) (ySouris / Constante.TAILLE_TUILE);
        setTuileCiel(c, l);
    }

    /**
     * Pose un bloc de type `typeTuile` à la position donnée par la souris.
     * @param xSouris position X en pixels
     * @param ySouris position Y en pixels
     * @param typeTuile code du type de tuile à poser
     */
    public void poserBloc(double xSouris, double ySouris, int typeTuile) {
        int c = (int) (xSouris / Constante.TAILLE_TUILE);
        int l = (int) (ySouris / Constante.TAILLE_TUILE);
        setTuile(c, l, typeTuile);
    }
}