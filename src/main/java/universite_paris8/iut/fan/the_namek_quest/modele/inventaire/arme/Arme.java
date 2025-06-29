package universite_paris8.iut.fan.the_namek_quest.modele.inventaire.arme;

import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.Element;

public class Arme extends Element {

    private int degat;
    public Arme(int id, String name,int degat) {
        super(id, name);
        this.degat = degat;
    }

    public int getDegat(){
        return this.degat;
    }

    public void incrementerDegat(int degat){
        setDegat(this.degat+degat);
    }

    public void setDegat(int degat){
        this.degat = degat;
    }

}