package universite_paris8.iut.fan.the_namek_quest.vue;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;

public class FondVue {
    private Pane fondPane;

    public FondVue(Pane fondPane) {
        this.fondPane = fondPane;
        this.afficherFond("/universite_paris8/iut/fan/the_namek_quest/images/namek.png");
    }

    public void afficherFond(String imagePath) {
        if (fondPane == null) {
            System.err.println("fondPane est null ! Assure-toi qu'il est bien lié au FXML.");
            return;
        }

        URL imageUrl = getClass().getResource(imagePath);
        if (imageUrl == null) {
            System.err.println("Image non trouvée : " + imagePath);
            return;
        }

        Image image = new Image(imageUrl.toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(1920);
        imageView.setFitHeight(1080);
        imageView.setPreserveRatio(false);
        fondPane.getChildren().add(imageView);
    }
}