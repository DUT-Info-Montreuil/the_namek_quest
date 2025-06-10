package universite_paris8.iut.fan.the_namek_quest.modele;

public class Dende extends PersonnageNonJoueur{

    private Trunks trunks;

    public Dende(int x, int y, Environnement env, Trunks trunks) {
        super(x, y, env);
        this.trunks = trunks;
    }

    public boolean dendePeutAider(){
        if(trunks.getPv() <= 20){
            return true;
        }
        return false;
    }

    public void apparitionDende(){
        if(dendePeutAider()){
            setX(trunks.getX()-64);
            setY(trunks.getY());
            incrementerPvTrunks();
        }
    }

    public void incrementerPvTrunks(){
        if(trunksAProximite()) {
            trunks.setPv(trunks.getPv() + 65);
        }
    }

}
