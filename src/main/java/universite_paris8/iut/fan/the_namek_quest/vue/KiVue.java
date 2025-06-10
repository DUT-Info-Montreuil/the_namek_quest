package universite_paris8.iut.fan.the_namek_quest.vue;

import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import universite_paris8.iut.fan.the_namek_quest.modele.Personnage;
import universite_paris8.iut.fan.the_namek_quest.modele.Trunks;

public class KiVue {
    private ProgressBar barreDeKI;
    private Trunks trunks;
    private Pane pane;
    private GameOver gameOver;



    public KiVue(Trunks trunks, Pane pane){
        this.trunks = trunks;
        this.pane = pane;
        barreDeKI = new ProgressBar(1);
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

}
