package universite_paris8.iut.fan.the_namek_quest.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.InputStream;

public class MenuDemarrage {

    private ImageView imageView;

    public void afficherMenuDemarrage(StackPane stackPane) {
        InputStream input = getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/menu.png");

        Image menuImage = new Image(input);
        imageView = new ImageView(menuImage);

        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);

        imageView.setFitWidth(900);
        imageView.setFitHeight(900);

        // ✅ Aligné à gauche, centré verticalement
        StackPane.setAlignment(imageView, javafx.geometry.Pos.CENTER_LEFT);

        stackPane.getChildren().add(0, imageView);
    }


    public void retirerMenuDemarrage(StackPane stackPane) {
        stackPane.getChildren().remove(imageView);
    }
}
