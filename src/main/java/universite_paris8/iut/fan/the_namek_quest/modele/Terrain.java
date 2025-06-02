package universite_paris8.iut.fan.the_namek_quest.modele;

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

    private int width;
    private int height;

    private static final int TAILLE_TUILE = 32;
    private static final int LARGEUR_PERSO = 32;
    private static final int HAUTEUR_PERSO = 32;
    private static final int MARGE_DROITE = 7;
    private static final int MARGE_GAUCHE = 11;

    // 1 => Ciel; 2 => Sol; 3 => Herbe; 4 => blanc (nuage) 5 => arbre 6 => Champignon 7 => rocher 8 => roche
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
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 3, 3, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 3, 2, 3, 1, 1, 1},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 3, 3, 2, 2, 2, 3, 3, 3},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2, 2},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
    };

    public Terrain() {
        this.height = this.hauteurTerrain() * TAILLE_TUILE;
        this.width = this.largeurTerrain() * TAILLE_TUILE;
    }

    public void setTuileCiel(int c, int l) {
        System.out.println("modif de la tuile en ciel modele");
        this.terrain[l][c] = 1;
        System.out.println(l + "-" + c);
    }

    public void setTuile(int c, int l,int typeTuile) {
        System.out.println("modif de la tuile en sol modele");
        this.terrain[l][c] = 2;
        System.out.println(l + "-" + c);
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
        return (x + MARGE_DROITE >= 0 && x < width - MARGE_GAUCHE && y >= 0 && y < height);
    }

    // Détermine si la case (en pixels) est "traversable" (ciel, nuage)
    public boolean estTraversable(int xPixel, int yPixel) {
        int caseX = getCaseX(xPixel);
        int caseY = getCaseY(yPixel);

        if (caseY < 0 || caseY >= terrain.length || caseX < 0 || caseX >= terrain[0].length) {
            return false;
        }
        int code = terrain[caseY][caseX];

        return (code == 1 || code == 4 || code == 5 || code == 6 || code == 7 || code == 8 || code == 9);
    }

    //TODO mettre dans Personnage (ou Trunks) tout ce quiconcerne Trunks = sa position, sa largeur, sa hauteur
    public boolean collisionBas(int x, int y) {
        int yTest = y + HAUTEUR_PERSO;
        for (int testX = x + MARGE_GAUCHE; testX < x + LARGEUR_PERSO - MARGE_DROITE; testX++) {
            if (!estTraversable(testX, yTest)) {
                return true;
            }
        }
        return false;
    }

    public boolean collisionHaut(int x, int y) {
        for (int testX = x + MARGE_GAUCHE; testX < x + (LARGEUR_PERSO - MARGE_DROITE); testX++) {
            if (!estTraversable(testX, y - 1)) { // y - 1 pour tester le pixel juste au-dessus
                return true;
            }
        }
        return false;
    }

    public boolean collisionDroite(int x, int y) {
        int xTest = x + LARGEUR_PERSO - MARGE_DROITE;
        for (int testY = y; testY < y + HAUTEUR_PERSO; testY++) {
            if (!estTraversable(xTest, testY)) {
                return true;
            }
        }
        return false;
    }

    public boolean collisionGauche(int x, int y) {
        int xTest = x + MARGE_DROITE;
        for (int testY = y; testY < y + HAUTEUR_PERSO; testY++) {
            if (!estTraversable(xTest, testY)) {
                return true;
            }
        }
        return false;
    }

    public int gravite(int x, int y) {
        if (!collisionBas(x, y)) {
            y += 2;
        }
        return y;
    }

    /// TODO a modifier , changer le nom de la fonction (range perso) faire une range autour du perso
    public boolean rangeCreuser(int xTrunks, int yTrunks, double xSouris, double ySouris) {
        boolean peutCreuser = false;
        if(xTrunks-TAILLE_TUILE<= xSouris && xSouris<=xTrunks+2*TAILLE_TUILE
        && yTrunks-TAILLE_TUILE<= ySouris && ySouris<=yTrunks+2*TAILLE_TUILE && !(xTrunks<=xSouris && xSouris<=xTrunks+TAILLE_TUILE && yTrunks<=ySouris && ySouris<=yTrunks+TAILLE_TUILE)) {
            peutCreuser = true;
        }

        return peutCreuser;
    }

    public void casserBloc(double xSouris, double ySouris) {
        setTuileCiel((int) (xSouris / TAILLE_TUILE), (int) (ySouris / TAILLE_TUILE));
        System.out.println("tuile casser");
    }

    public void poserBloc(double xSouris, double ySouris,int typeTuile) {
        setTuile((int) (xSouris / TAILLE_TUILE), (int) (ySouris / TAILLE_TUILE),typeTuile);
        System.out.println("tuile creuser");
    }

}