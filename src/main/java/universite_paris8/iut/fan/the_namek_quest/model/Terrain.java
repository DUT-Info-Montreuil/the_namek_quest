package universite_paris8.iut.fan.the_namek_quest.model;

/*
    Cette classe définit le terrain. C'est-à-dire quel type de tuile est à quel endroit.


 */
public class Terrain {

    private int width;
    private int height;

    private final int TAILLE_TUILE = 32;
    private final int LARGEUR_PERSO = 24;
    private final int HAUTEUR_PERSO = 32;
    private final int MARGE_COLLISION_X = 4;
    private final int MARGE_COLLISION_Y = 4;


    public Terrain(){
        this.height = this.hauteurTerrain()*31;
        this.width = this.largeurTerrain()*31 ;

    }

    //1 => Ciel
    //3 => Herbe
    //2 => Sol
    //4 => blanc (nuage)

    private int [][] terrain= {
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
            {1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 3, 3, 3, 3, 3, 3, 3},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
    };

    public int hauteurTerrain(){
        return this.terrain.length;
    }

    public int largeurTerrain(){
        return this.terrain[0].length;
    }

    public int codeTuile(int c, int l) {
        return this.terrain[l][c];
    }

    public int codeTuilePixel(int x, int y){
        return this.terrain[y / TAILLE_TUILE][x / TAILLE_TUILE];
    }

    public int getCaseX(int xPixel) {
        return xPixel / TAILLE_TUILE;
    }

    public int getCaseY(int yPixel) {
        if (yPixel >= 0) {
            return yPixel / TAILLE_TUILE;
        } else {
            return -1;
        }
    }

    public boolean dansTerrain(int x, int y){
        return (0 <= x && x<this.width  && 0<=y && y< this.height );
    }

    public boolean peutMarcherDessus (int x, int y){
        return codeTuilePixel(x, y) == 1;
    }

    public boolean estMarchable(int xPixel, int yPixel) {
        int caseX = getCaseX(xPixel);
        int caseY = getCaseY(yPixel);

        if (caseY < 0 || caseY >= terrain.length || caseX < 0 || caseX >= terrain[0].length) {
            return false;
        }

        return terrain[caseY][caseX] == 1;
    }


    //Collision  //TODO déplacer dans terrain
    public boolean collisionDroite(int x, int y) {
        return !estMarchable(x + LARGEUR_PERSO, y + MARGE_COLLISION_Y) ||
                !estMarchable(x + LARGEUR_PERSO, y + HAUTEUR_PERSO - MARGE_COLLISION_Y);
    }

    public boolean collisionGauche(int x, int y) {
        return !estMarchable(x, y + MARGE_COLLISION_Y) ||
                !estMarchable(x, y + HAUTEUR_PERSO - MARGE_COLLISION_Y);
    }

    public boolean collisionVerticale(int x, int yBas) {
        int colonne = x / 32; // 32 pixels par tuile (cohérent avec la taille perso)
        int ligne = yBas/ 32; // on ajoute 1 pixel pour détecter juste sous les pieds

        if (colonne < 0 || colonne >= this.largeurTerrain() || ligne < 0 || ligne >= this.hauteurTerrain()) {
            return true;
        }

        int code = this.codeTuile(colonne, ligne);
        return (code == 2 || code == 3);
    }


    public int gravite(int x, int y) {
        int hauteurPerso = 32;

        if (y + hauteurPerso + 1 < this.hauteurTerrain() * 32 &&
                !this.collisionVerticale(x, y + hauteurPerso + 1)) {
            y += 2; // vitesse de chute (ajuste si trop rapide)
        }

        return y;
    }

    /*
    public boolean collisionBas(int x, int y) {
        int piedY = y + HAUTEUR_PERSO;
        return !estMarchable(x + MARGE_COLLISION_X, piedY) ||
                !estMarchable(x + LARGEUR_PERSO - MARGE_COLLISION_X, piedY);
    }


    public boolean collisionHaut(int x, int y) {
        int teteY = y - 1;
        return !estMarchable(x + MARGE_COLLISION_X, teteY) ||
                !estMarchable(x + LARGEUR_PERSO - MARGE_COLLISION_X, teteY);
    }*/
}
