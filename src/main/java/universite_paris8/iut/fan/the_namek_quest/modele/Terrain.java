package universite_paris8.iut.fan.the_namek_quest.modele;

import universite_paris8.iut.fan.the_namek_quest.Constante;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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

    // Tableau 2D représentant la map (code des tuiles)
    private int[][] terrain;

    /**
     * Charge la map depuis un fichier texte
     */
    public int[][] loadMapFromResource(String resourcePath) throws IOException {
        List<int[]> rows = new ArrayList<>();

        try (InputStream is = getClass().getResourceAsStream(resourcePath);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            if (is == null) {
                throw new FileNotFoundException("Ressource non trouvée : " + resourcePath);
            }

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.trim().split("\\s+");
                int[] row = new int[values.length];
                for (int i = 0; i < values.length; i++) {
                    row[i] = Integer.parseInt(values[i]);
                }
                rows.add(row);
            }
        }

        return rows.toArray(new int[0][]);
    }
    /**
     * Constructeur : initialise la map et la taille du terrain en pixels.
     */

    public Terrain() {
        try {
            terrain = loadMapFromResource("/universite_paris8/iut/fan/the_namek_quest/map/terrain.txt");
        } catch (IOException e) {
            terrain = new int[0][0];
        }

        if (terrain.length == 0 || terrain[0].length == 0) {
            this.height = 0;
            this.width = 0;
        } else {
            this.height = hauteurTerrain() * Constante.TAILLE_TUILE;
            this.width = largeurTerrain() * Constante.TAILLE_TUILE;
        }
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
        return terrain.length > 0 ? terrain[0].length : 0;
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

        public int getCaseX(int xPixel) {
            return xPixel / Constante.TAILLE_TUILE;
        }

        public int getCaseY(int yPixel) {
            return yPixel / Constante.TAILLE_TUILE;
        }

        public boolean dansTerrain(int x, int y) {
            return (x >= 0 && x < width && y >= 0 && y < height);
        }

        public void setTuileCiel(int c, int l) {
            terrain[l][c] = 1;
        }

        public void setTuile(int c, int l, int typeTuile) {
            terrain[l][c] = typeTuile;
        }

        public boolean estTraversable(int xPixel, int yPixel) {
            int caseX = getCaseX(xPixel);
            int caseY = getCaseY(yPixel);

            if (caseY < 0 || caseY >= hauteurTerrain() || caseX < 0 || caseX >= largeurTerrain()) {
                return false;
            }

            int code = terrain[caseY][caseX];

            if (code == 1 /* ciel */ || code == 4 /* energie */ || code == 11 /* feuillage */ || code == 81 || code == 82 || code == 83 || code == 84 || code == 85 || code == 86 || code == 87 || code == 88 || code == 89 /* temple pnj */ ) {
                return true;
            }

            if (code == 10 /* mur */) {
                int decalageX = xPixel % Constante.TAILLE_TUILE;
                return decalageX < Constante.MARGE_GAUCHE_MUR || decalageX >= (Constante.TAILLE_TUILE - Constante.MARGE_DROITE_MUR);
            }

            return false;
        }

        public boolean rangeCreuser(int xTrunks, int yTrunks, double xSouris, double ySouris) {
            boolean dansX = xSouris >= xTrunks - Constante.TAILLE_TUILE && xSouris <= xTrunks + 2 * Constante.TAILLE_TUILE;
            boolean dansY = ySouris >= yTrunks - Constante.TAILLE_TUILE && ySouris <= yTrunks + 2 * Constante.TAILLE_TUILE;
            boolean surTrunks = xSouris >= xTrunks && xSouris <= xTrunks + Constante.TAILLE_TUILE
                    && ySouris >= yTrunks && ySouris <= yTrunks + Constante.TAILLE_TUILE;

            return (dansX && dansY) && !surTrunks;
        }

        public void casserBloc(double xSouris, double ySouris) {
            int c = (int) (xSouris / Constante.TAILLE_TUILE);
            int l = (int) (ySouris / Constante.TAILLE_TUILE);
            setTuileCiel(c, l);
        }

        public void poserBloc(double xSouris, double ySouris, int typeTuile) {
            int c = (int) (xSouris / Constante.TAILLE_TUILE);
            int l = (int) (ySouris / Constante.TAILLE_TUILE);
            setTuile(c, l, typeTuile);
        }
    }
