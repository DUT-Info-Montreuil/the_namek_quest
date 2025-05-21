package universite_paris8.iut.fan.the_namek_quest.object;

public abstract class Utilitaire extends Object {
    private int PointDeCreusage ;

    public Utilitaire(int id, String name , int PointDeCreusage ) {
        super(id, name);
        this.PointDeCreusage = PointDeCreusage;
    }

    @Override
    public int Taper() {
        return 1;
    }

    @Override
    public int Creuser() {
        return PointDeCreusage;
    }
}
