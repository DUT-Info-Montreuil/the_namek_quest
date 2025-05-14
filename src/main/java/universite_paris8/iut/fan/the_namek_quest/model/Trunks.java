package universite_paris8.iut.fan.the_namek_quest.model;

public class Trunks extends Personnage {

    public Trunks() {
        super(200, 5, 4);
    }


    public void seDeplacer(int d){
        if(d==0){
            setX(this.getX()+1);
        }else if(d==1){
            setX(this.getX()-1);
        }
    }



}
