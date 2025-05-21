package universite_paris8.iut.fan.the_namek_quest.object;

public class Arme extends ObjectMain {
    private int PointDattaqueMax;

    public Arme(int id, String name , int PointDattaqueMax) {
        super(id, name);
        this.PointDattaqueMax = PointDattaqueMax;
    }
     public int getPointDattaque() {
        return PointDattaqueMax;
     }

     public int Taper(){
        return PointDattaqueMax;
     }

    @Override
    public int Creuser() {
        return 1;
    }
}
