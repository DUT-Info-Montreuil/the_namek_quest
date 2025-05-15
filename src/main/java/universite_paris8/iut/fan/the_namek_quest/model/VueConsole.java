package universite_paris8.iut.fan.the_namek_quest.model;

import javafx.scene.image.ImageView;

public class VueConsole {

    private Environnement env;

    public VueConsole(Environnement env) {
        this.env = env;
    }

    public void afficherMap(String [][] tab){
        for(int y = 0; y< env.getTerrain().hauteurTerrain(); y++) {
            for(int x = 0; x< env.getTerrain().largeurTerrain(); x++) {
                System.out.print("|" +tab[y][x]);
            }
            System.out.println("");
        }
    }


    public String [][] tableauEnv(){
            String [][]  tab = new String[env.getTerrain().hauteurTerrain()][env.getTerrain().largeurTerrain()];
            for(int y = 0; y< env.getTerrain().hauteurTerrain(); y++) {
                for(int x = 0; x< env.getTerrain().largeurTerrain(); x++) {
                     if(env.getTrunks().getX() == x && env.getTrunks().getY() ==y){
                        tab[y][x]="T";
                    }else{
                        tab[y][x] = " ";
                       }
                }
                System.out.println();

            }
            return tab;
    }

    public void go(){
        afficherMap(tableauEnv());
    }
}
