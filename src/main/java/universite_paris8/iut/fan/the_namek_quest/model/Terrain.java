package universite_paris8.iut.fan.the_namek_quest.model;

/*
    Cette classe définit le terrain. C'est-à-dire quel type de tuile est à quel endroit.


 */
public class Terrain {

    private int width;
    private int height;

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
            {1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1},
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
        return this.terrain[x/32][y/32];
    }

    public boolean dansTerrain(int x, int y){
        return (0 <= x && x<this.width  && 0<=y && y< this.height );
    }

    public boolean peutMarcherDessus (int x, int y){
        return codeTuilePixel(x, y) == 1;
    }

    //Collision  //TODO déplacer dans terrain
    public boolean collisionHorizontale(int newX,int y) {
        if(this.codeTuile(newX,y/31) == 1) {  //TODO c'est le terrain qui gère les coordonnées ligne/colonne (partout ailleurs on parle pixels), c'est le terrain qui peut dire si on peut marcher sur une tuile
            return false;
        }
        return true;
    }

    public boolean collisionVerticale(int x,int newY) {
        if (x / 31 < 0 || x / 31 >= this.largeurTerrain() || newY / 31 < 0 || newY / 31 >= this.hauteurTerrain()) {
            return true;
        }
        return (this.codeTuile(x / 31, newY / 31) == 2 || this.codeTuile(x / 31, newY / 31) == 3);
    }

    public int gravite(int x,int newY) {
        if (newY + 32 < this.hauteurTerrain() * 31 && !this.collisionVerticale(x, newY + 32)) {
            newY = (newY + 2);
        }
        return newY;
    }
}
