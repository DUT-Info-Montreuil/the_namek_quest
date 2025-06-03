package universite_paris8.iut.fan.the_namek_quest.modele.inventaire.arme;

import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.Object;

public class Arme extends Object {

    private int degat;
    public Arme(int id, String name,int degat) {
        super(id, name);
        this.degat = degat;
    }

    public int getDegat(){
        return this.degat;
    }



}