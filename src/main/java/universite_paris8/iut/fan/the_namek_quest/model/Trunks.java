package universite_paris8.iut.fan.the_namek_quest.model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Trunks extends Personnage {
    private Environnement env;

    public Trunks(Environnement env) {
        super(200, 250, 352);
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
