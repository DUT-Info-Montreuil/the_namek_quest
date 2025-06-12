package universite_paris8.iut.fan.the_namek_quest.vue;

import javafx.animation.PauseTransition;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import universite_paris8.iut.fan.the_namek_quest.modele.Personnage;
import universite_paris8.iut.fan.the_namek_quest.modele.Trunks;

public class PointVieVue {
    private ProgressBar barreDeVie;
    private Personnage trunks;
    private Pane pane;
    private GameOver gameOver;


    public PointVieVue(Trunks trunks, Pane pane){
        this.trunks = trunks;
        this.pane = pane;
        barreDeVie = new ProgressBar();
        afficherPv();
    }

    public void afficherPv(){
        barreDeVie = new ProgressBar(1);
        barreDeVie.setStyle("-fx-accent: red;");
        barreDeVie.setPrefWidth(150);
        barreDeVie.setProgress(0.50);
        barreDeVie.progressProperty().bind(trunks.getPvProp().divide(100.0));

        pane.getChildren().add(barreDeVie);
    }

    public ProgressBar getBarreDeVie () {
        return barreDeVie;
    }
}
