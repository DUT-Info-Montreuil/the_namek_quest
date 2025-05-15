package universite_paris8.iut.fan.the_namek_quest.model;

public class Trunks extends Personnage {
    private Environnement env;

    public Trunks(Environnement env) {
        super(200, 250, 300);
        //super(200, 8, 6);
        this.env = env;
    }

    public void seDeplacer(int d) {
        int newX;
        if (d == 0) {
            newX = this.getX() + 10;
            if (env.dansTerrain(newX, this.getY())) {
                setX(newX);
            }
        }
        else if (d == 1) {
            newX = this.getX() - 10;
            if (env.dansTerrain(newX, this.getY())) {
                setX(newX);
            }
        }
    }

    public void sauter(int d) {
        int newY;
        if (d == 0) {
            newY = this.getY() - 20;
            if (env.dansTerrain(newY, this.getY())) {
                setY(newY);
            }
        }
    }

    /*public void finSauter(int d) {
        int newY;
        if (d == 0) {}
    }*/
}
