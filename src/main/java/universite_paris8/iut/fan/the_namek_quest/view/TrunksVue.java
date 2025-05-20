package universite_paris8.iut.fan.the_namek_quest.view;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.fan.the_namek_quest.model.Trunks;

public class TrunksVue {
    @FXML private ImageView persoImage;
    @FXML private Pane pane;
    private Trunks trunks;

    public TrunksVue(Pane pane,Trunks trunks) {
        this.trunks = trunks;
        this.pane = pane;
        this.persoImage = new ImageView();
        this.afficherTrunks();
    }

    public void afficherTrunks() {
        Image imagePersoDroite = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/blanc.png").toExternalForm());
        persoImage.setImage(imagePersoDroite);
        persoImage.translateXProperty().bind(trunks.getXProp());
        persoImage.translateYProperty().bind(trunks.getYProp());
        pane.getChildren().add(persoImage);
    }

    public void changerImageDroite() {
        Image imagePersoDroite = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/blanc.png").toExternalForm());
        persoImage.setImage(imagePersoDroite);
    }

    public void changerImageGauche() {
        Image imagePersoGauche = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/blanc.png").toExternalForm());
        persoImage.setImage(imagePersoGauche);
    }
}
