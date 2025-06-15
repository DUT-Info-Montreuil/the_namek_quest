package universite_paris8.iut.fan.the_namek_quest.vue;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import universite_paris8.iut.fan.the_namek_quest.modele.Environnement;
import universite_paris8.iut.fan.the_namek_quest.modele.Terrain;
import universite_paris8.iut.fan.the_namek_quest.modele.personnage.*;

public class KiVue {
    private ProgressBar barreDeKI;
    private Trunks trunks;
    private Pane pane;
    private GameOver gameOver;
    private boolean kiEstActif = false;
    private ImageView ki;
    private PersonnageEnnemisVue ennemisVue; // à ajouter dans TrunksVue

    public KiVue(Trunks trunks, Pane pane){
        this.trunks = trunks;
        this.pane = pane;
        barreDeKI = new ProgressBar(1);
        this.ennemisVue = ennemisVue;
        afficherKI();
    }

    public void afficherKI(){

        this.barreDeKI.setStyle("-fx-accent: #007BFF;");
        this.barreDeKI.setPrefWidth(150);
        this.barreDeKI.setProgress(0.50);
        this.barreDeKI.progressProperty().bind(trunks.getKI().divide(100.0));

        this.barreDeKI.setLayoutX(0);
        this.barreDeKI.setLayoutY(pane.getHeight() -(pane.getHeight()-20));

        this.pane.getChildren().add(barreDeKI);

        /*
        Condition à rajouter avec les champignons pour augmenter le ki et retirer le ki quand on utilise pour attaquer
        un if ici puis
        for(int i = 0; i<100; i++){
            this.trunks.increaseKI();
        }
         */

    }

    public void lancerBouleDeKi(Environnement env) {
        ImageView bouleKi = new ImageView(new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/materieau/ki.png").toExternalForm()));
        bouleKi.setFitWidth(16);
        bouleKi.setFitHeight(16);

        // Positionner au niveau du joueur
        double startX = trunks.getX();
        double startY = trunks.getY();
        bouleKi.setLayoutX(startX);
        bouleKi.setLayoutY(startY);

        pane.getChildren().add(bouleKi);

        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(10), e -> {
            double nextX = bouleKi.getLayoutX() + 5;
            double nextY = bouleKi.getLayoutY();

            // Vérifier si on est encore dans les limites du terrain
            if (!terrain.estDansTerrain(nextX, nextY)) {
                pane.getChildren().remove(bouleKi);
                timeline.stop();
                return;
            }

            // Vérifier collision avec un obstacle (ex: mur, etc.)
            if (terrain.collisionADroite((int) nextX / 32, (int) nextY / 32)) {
                pane.getChildren().remove(bouleKi);
                timeline.stop();
                return;
            }

            // Vérifier collision avec l’ennemi
            if (bouleKi.getBoundsInParent().intersects(ennemisVue.getPersoImage().getBoundsInParent())) {
                pane.getChildren().remove(bouleKi);
                trunks.getEnv().getPersonnageEnnemis().setPv(
                        trunks.getEnv().getPersonnageEnnemis().getPv() - 10
                );
                timeline.stop();
                return;
            }

            // Déplacer la boule
            bouleKi.setLayoutX(nextX);
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }


}
