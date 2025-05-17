package universite_paris8.iut.fan.the_namek_quest.model;

public class Trunks extends Personnage {

    public Trunks(Environnement env) {
        //super(200, 250, 352, env);
        super(200, 400, 348, env);
        this.setVitesse(16);
    }

    public void seDeplacer(int d) {
        int newX;
        if (d == 0) {
            newX = this.getX()+1;
            System.out.println(" 1 X = " + getX() + " / NewX = " + newX + " / Width = " + getEnv().getWidth());
            if (this.getEnv().dansTerrain(newX, this.getY()) && this.collisionHorizontale(newX)) {
                System.out.println("2 X = " + getX() + " / NewX = " + newX + " / Width = " + getEnv().getWidth());
                setX((newX -1) + getVitesse());
            }
        }
        else if (d == 1) {
            newX = this.getX() - 1;
            if (this.getEnv().dansTerrain(newX, this.getY())/* && this.collisionHorizontale(newX)*/) {
                setX((newX -1) - getVitesse());
            }
        }
    }

    /*public void sauter() {
        Timeline timeline = new Timeline();
        int sautHauteur = 20;

        // Monter
        for (int i = 0; i < sautHauteur; i++) {
            int delta = -1;
            timeline.getKeyFrames().add(
                    new KeyFrame(Duration.millis(i * 10), e -> {
                        if (env.dansTerrain(getX(), getY() + delta)) {
                            setY(getY() + delta);
                        }
                    })
            );
        }

        // Redescendre
        for (int i = 0; i < sautHauteur; i++) {
            int delta = 1;
            timeline.getKeyFrames().add(
                    new KeyFrame(Duration.millis((sautHauteur + i) * 10), e -> {
                        if (env.dansTerrain(getX(), getY() + delta)) {
                            setY(getY() + delta);
                        }
                    })
            );
        }

        timeline.play();
    }
*/

}
