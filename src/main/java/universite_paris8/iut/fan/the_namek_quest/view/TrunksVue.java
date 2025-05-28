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

    public TrunksVue(Pane pane,Trunks trunks) {
        this.trunks = trunks;
        this.pane = pane;
        this.persoImage = new ImageView();
        this.afficherTrunks();
        this.afficherPv();
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


    public void changerImagePioche(){
        if(this.trunks.getObjectEquipe().getId() == 1){
            Image imagePersoPiocheDroite = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/trunks-pioche-droite.png").toExternalForm());
            persoImage.setImage(imagePersoPiocheDroite);
        }
    }
    public void changerImageEpee(){
        if(this.trunks.getObjectEquipe().getId() == 0){
            Image imagePersoEpeeDroite = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/trunks-epee-droite.png").toExternalForm());
            persoImage.setImage(imagePersoEpeeDroite);
        }
    }

    public void changerImageHache(){
        if(this.trunks.getObjectEquipe().getId() == 2){
            Image imagePersoHacheDroite = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/trunks-hache-droite.png").toExternalForm());
            persoImage.setImage(imagePersoHacheDroite);
        }
    }

}
