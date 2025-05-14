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

public class TrunksVue {
    @FXML
    private ImageView persoImage;
    private double persoImageX = 0;
    private final int tailleTuile = 16;
    private Pane pane;


    public TrunksVue(Pane pane) {
        this.pane = pane;
        this.persoImage = new ImageView();

        this.afficherTrunks();
    }

    public void afficherTrunks() {

        Image imagePerso = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/trunks.png").toExternalForm());
        System.out.println(imagePerso);
        ImageView imageViewPerso = new ImageView(imagePerso);
        //persoImage.setImage(imagePerso);
        persoImage.setTranslateX(25 * tailleTuile);
        persoImage.setTranslateY(30 * tailleTuile);
        pane.getChildren().add(imageViewPerso);

        //tilePane.setFocusTraversable(true);

    /*
           Platform.runLater(() -> {
                pane.getScene().setOnKeyPressed(event -> {
                    switch (event.getCode()) {
                        case D:
                            persoImageX += 16;
                            break;
                        case Q:
                            persoImageX -= 16;
                            break;
                    }
                    persoImage.setTranslateX(persoImageX);
                    persoImage.setTranslateX(persoImageX);
                });
            });
*/
    }
}
