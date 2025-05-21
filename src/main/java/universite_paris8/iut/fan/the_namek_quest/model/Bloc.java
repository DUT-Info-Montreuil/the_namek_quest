package universite_paris8.iut.fan.the_namek_quest.model;

public abstract class Bloc {
    // 1 => Ciel; 2 => Sol; 3 => Herbe; 4 => blanc (nuage);5 => arbre ; 6 => champignon ; 7 => Roche

    private String type;
    private int pv ;
    private int id ;


    public Bloc (int id, int pv, String type){
        this.type = type ;
        this.pv = pv ;
        this.id = id ;
    }

    public String getType () {
        return type ;
    }

    public  int getPv () {
        return pv ;
    }
    public int getId () {
        return id ;
    }




}
