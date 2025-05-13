package universite_paris8.iut.fan.the_namek_quest.model;

public class Terrain {

    public Terrain(){}

    private int [][] terrain= {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
            {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},


    };

    //1 => Ciel
    //3 => herbe
    //2 => Sol
    public int[][] getTerrain() {
        return this.terrain;
    }

    public int hauteurTerrain(){
       return this.terrain.length;
    }

    public int largeurTerrain(){
        return this.terrain[0].length;
    }

    /*
    public void tab(){
        for(int i = 0 ; i <  ; i++){

        }
    }
*/

}
