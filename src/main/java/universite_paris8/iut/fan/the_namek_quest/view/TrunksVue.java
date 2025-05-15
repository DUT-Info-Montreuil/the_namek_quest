package universite_paris8.iut.fan.the_namek_quest.view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.fan.the_namek_quest.controller.Clavier;
import universite_paris8.iut.fan.the_namek_quest.model.Trunks;

import java.awt.event.KeyEvent;

public class TrunksVue {
    @FXML
    private ImageView persoImage;
    private double persoImageX = 0;
    private final int tailleTuile = 16;
    private Pane pane;
    private Trunks trunks;
    private Clavier clavier;


    public TrunksVue(Pane pane,Trunks trunks) {
        this.trunks = trunks;
        this.pane = pane;
        this.persoImage = new ImageView();

        this.afficherTrunks();
    }

    public void afficherTrunks() {

        Image imagePerso = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/trunks(3).png").toExternalForm());
        System.out.println(imagePerso);
        persoImage.setImage(imagePerso);
        persoImage.translateXProperty().bind(trunks.getXProp());
        persoImage.translateYProperty().bind(trunks.getYProp());
        pane.getChildren().add(persoImage);



        //tilePane.setFocusTraversable(true);
    }
}
