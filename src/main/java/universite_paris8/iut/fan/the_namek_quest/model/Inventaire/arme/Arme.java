package universite_paris8.iut.fan.the_namek_quest.model.Inventaire.arme;

public class Arme extends Object {
    private int degats;

    public Arme(int id, String name , int degats) {
        super();
        this.degats = degats;
    }
     public int getDegats() {
        return degats;
     }

     public int Taper(){
        return degats;
     }

     public void setDegats(int degats) {
        this.degats = degats;
     }


}
