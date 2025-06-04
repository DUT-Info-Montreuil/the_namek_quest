package universite_paris8.iut.fan.the_namek_quest.vue;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import universite_paris8.iut.fan.the_namek_quest.modele.GrandChef;
import javafx.scene.image.Image;


import java.awt.*;

public class GrandChefVue {
    private ImageView persoImage;
    @FXML
    private Pane pane;
    private GrandChef grandChef;

    public GrandChefVue(Pane pane, GrandChef grandChef) {
        this.grandChef = grandChef;
        this.pane = pane;
        this.persoImage = new ImageView();
        this.afficherGrandChef();
        afficherMessageAcceuil();
    }

    public void afficherGrandChef() {
        Image imageGrandChef = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/grandchef.png").toExternalForm());
        persoImage.setImage(imageGrandChef);
        persoImage.translateXProperty().bind(grandChef.getXProp());
        persoImage.translateYProperty().bind(grandChef.getYProp());
        pane.getChildren().add(persoImage);
    }

    /*public void afficherMessageAcceuil() {
        if(grandChef.trunksAProximite()){
            Label labelMessage = new Label();
            labelMessage.setFont(new Font("Arial",12));
            labelMessage.setText("Bonjour Trunks, \n avec moi tu peux amélioré ton épée il te suffit de cliquer sur 'e' et d'avoir de la roche (x3) et des boules d'énergie (x2)!");
            labelMessage.setBackground();

        }

    }*/

    public void afficherMessageAcceuil() {
        if(grandChef.trunksAProximite()) {
            Label labelMessage = new Label("Bonjour Trunks,\navec moi tu peux améliorer ton épée.\nAppuie sur 'e' avec 3 roches et 2 boules d'énergie !");
            labelMessage.setFont(new Font("Arial", 12));
            labelMessage.setPrefSize(160, 96);
            labelMessage.setWrapText(true); // retour à la ligne automatique
            labelMessage.setStyle(
                    "-fx-background-color: white;" +
                            "-fx-border-color: black;" +
                            "-fx-background-radius: 10;" +
                            "-fx-border-radius: 10;"
            );

            // Position de la bulle (à adapter selon ta scène)
            labelMessage.setLayoutX(100);
            labelMessage.setLayoutY(100);

            pane.getChildren().add(labelMessage);
        }
    }


}
