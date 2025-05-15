package universite_paris8.iut.fan.the_namek_quest.model;

import javafx.scene.image.ImageView;

import java.util.Scanner;

public class VueConsole {

    private Environnement env;
    private Scanner scanner;

    public VueConsole(Environnement env) {
        this.env = env;
        this.scanner = new Scanner(System.in);
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

    public char menuEtSaisie() {
        System.out.println("\n=== Menu ===");
        System.out.println("q : Reculer (gauche)");
        System.out.println("d : Avancer (droite)");
        System.out.println("x : Quitter");
        System.out.print("Entrez votre choix : ");
        String input = scanner.nextLine();
        return input.length() > 0 ? input.charAt(0) : ' ';
    }

    public void go() {
        boolean encore = true;
        while (encore) {
            afficherMap(tableauEnv());
            char choix = menuEtSaisie();
            switch (choix) {
                case 'd':
                    env.getTrunks().seDeplacer(0);
                    break;
                case 'q':
                    env.getTrunks().seDeplacer(1);
                    break;
                case 'x':
                    System.out.println("Au revoir !");
                    encore = false;
                    break;
                default:
                    System.out.println("Choix invalide. Essayez encore.");
            }
        }
    }
}
