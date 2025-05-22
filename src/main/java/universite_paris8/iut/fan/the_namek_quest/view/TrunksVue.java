package universite_paris8.iut.fan.the_namek_quest.view;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.fan.the_namek_quest.model.Trunks;

public class TrunksVue {
     private ImageView persoImage;
    @FXML private Pane pane;
    private Trunks trunks;
    private ProgressBar barreDeVie;
    private ProgressBar barreDeKI;

    public TrunksVue(Pane pane,Trunks trunks) {
        this.trunks = trunks;
        this.pane = pane;
        this.persoImage = new ImageView();
        this.afficherTrunks();
        this.afficherPv();
        this.afficherKI();
    }

    public void afficherTrunks() {
        Image imagePersoDroite = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/trunks-droite.png").toExternalForm());
        persoImage.setImage(imagePersoDroite);
        persoImage.translateXProperty().bind(trunks.getXProp());
        persoImage.translateYProperty().bind(trunks.getYProp());
        pane.getChildren().add(persoImage);
    }

    public void changerImageDroite() {
        Image imagePersoDroite = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/trunks-droite.png").toExternalForm());
        persoImage.setImage(imagePersoDroite);
    }

    public void changerImageGauche() {
        Image imagePersoGauche = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/trunks-gauche.png").toExternalForm());
        persoImage.setImage(imagePersoGauche);
    }

    public void afficherPv(){
        barreDeVie = new ProgressBar(1);
        barreDeVie.setStyle("-fx-accent: red;");
        barreDeVie.setPrefWidth(150);
        barreDeVie.setProgress(0.50);
        barreDeVie.progressProperty().bind(trunks.getPvProp().divide(100.0));

        pane.getChildren().add(barreDeVie);

    }

    public void afficherKI(){
        barreDeKI = new ProgressBar(1);
        barreDeKI.setStyle("-fx-accent: #007BFF;");
        barreDeKI.setPrefWidth(150);
        barreDeKI.setProgress(0.50);
        barreDeKI.progressProperty().bind(trunks.getKI().divide(100.0));
        barreDeKI.setLayoutX(0);
        barreDeKI.setLayoutY(pane.getHeight() +20);
        pane.getChildren().add(barreDeKI);


        /*
        Condition à rajouter avec les champignons pour augmenter le ki et retirer le ki quand on utilise pour attaquer
        un if ici puis
        for(int i = 0; i<100; i++){
            this.trunks.increaseKI();
        }
         */

    }

}
