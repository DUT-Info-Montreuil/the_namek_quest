package universite_paris8.iut.fan.the_namek_quest.object;

public abstract class Materieau extends Object{
    private int Quantite ;
    public Materieau(int id, String name) {
        super(id, name);
        this.Quantite = 0;
    }
}
