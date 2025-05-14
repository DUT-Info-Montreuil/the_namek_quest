package universite_paris8.iut.fan.the_namek_quest.vue;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.fan.the_namek_quest.Controller;
import universite_paris8.iut.fan.the_namek_quest.Launcher;
import universite_paris8.iut.fan.the_namek_quest.model.Terrain;
import universite_paris8.iut.fan.the_namek_quest.model.Trunks;

public class TrunksVue {
    @FXML
    private ImageView persoImage;
    private double persoImageX = 0;
    private final int tailleTuile = 16;
    private Pane pane;
    private Trunks trunks;


    public TrunksVue(Pane pane) {
        this.trunks = new Trunks();
        this.pane = pane;
        this.persoImage = new ImageView();

        this.afficherTrunks();
    }

    public void afficherTrunks() {

        Image imagePerso = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/trunks.png").toExternalForm());
        System.out.println(imagePerso);
        ImageView imageViewPerso = new ImageView(imagePerso);
        persoImage.setImage(imagePerso);
        persoImage.translateXProperty().bind(trunks.getXProp());
        persoImage.translateYProperty().bind(trunks.getYProp());
        pane.getChildren().add(persoImage);

        //tilePane.setFocusTraversable(true);


           Platform.runLater(() -> {
                pane.getScene().setOnKeyPressed(event -> {
                    switch (event.getCode()) {
                        case D:
                            this.trunks.setX(this.trunks.getX()+16);
                            break;
                        case Q:
                            this.trunks.setX(this.trunks.getX()-16);
                            break;
                    }
                    //persoImage.setTranslateX(persoImageX);
                    //persoImage.setTranslateX(persoImageX);
                });
            });

    }
}
