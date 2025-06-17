package universite_paris8.iut.fan.the_namek_quest.vue;


import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.fan.the_namek_quest.modele.personnage.Trunks;

public class EnergieVue {

    private ProgressBar barreDeKI;
    private Trunks trunks;
    private Pane pane;
    private ImageView ki;
    private PersonnageEnnemisVue ennemisVue;

    public EnergieVue(Trunks trunks, Pane pane/*, PersonnageEnnemisVue ennemisVue*/) {
        this.trunks = trunks;
        this.pane = pane;
        //this.ennemisVue = ennemisVue;
        afficherBarreKI();
    }

    private void afficherBarreKI() {
        this.barreDeKI = new ProgressBar();
        this.barreDeKI.setStyle("-fx-accent: #007BFF;");
        this.barreDeKI.setPrefWidth(150);

        // Binding du KI avec la barre
        this.barreDeKI.progressProperty().bind(trunks.getEnv().getKI().divide(100.0));

        this.barreDeKI.setLayoutX(0);
        this.barreDeKI.setLayoutY(20);
        this.pane.getChildren().add(barreDeKI);
    }

    public void lancerBouleDeKi() {
        ImageView bouleKi = new ImageView(new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/materieau/ki.png").toExternalForm()));
        bouleKi.setFitWidth(16);
        bouleKi.setFitHeight(16);


    }
}
