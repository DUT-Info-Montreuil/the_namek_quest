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
    private Label labelMessage = null;


    public GrandChefVue(Pane pane, GrandChef grandChef) {
        this.grandChef = grandChef;
        this.pane = pane;
        this.persoImage = new ImageView();
        this.afficherGrandChef();
    }

    public void afficherGrandChef() {
        Image imageGrandChef = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/grandchef.png").toExternalForm());
        persoImage.setImage(imageGrandChef);
        persoImage.translateXProperty().bind(grandChef.getXProp());
        persoImage.translateYProperty().bind(grandChef.getYProp());
        pane.getChildren().add(persoImage);
    }

    public void afficherMessageAcceuil() {
        if(grandChef.trunksAProximite()) {
            if (labelMessage == null) {
                labelMessage = new Label("Bonjour Trunks,\navec moi tu peux améliorer ton épée.\nAppuie sur 'e' avec ton épée en main et d'avoir 3 roches et 2 boules d'énergie !");
                labelMessage.setFont(new Font("Arial", 12));
                labelMessage.setPrefSize(180, 105);
                labelMessage.setWrapText(true);
                labelMessage.setStyle(
                        "-fx-background-color: white;" +
                                "-fx-border-color: black;" +
                                "-fx-background-radius: 10;" +
                                "-fx-border-radius: 10;"
                );

                labelMessage.setLayoutX(grandChef.getXProp().doubleValue() - 155);
                labelMessage.setLayoutY(grandChef.getYProp().doubleValue() - 106);
                pane.getChildren().add(labelMessage);
            }
        } else {
            if (labelMessage != null) {
                System.out.println("pas de grand chef");
                pane.getChildren().remove(labelMessage);
                labelMessage = null;
            }
        }
    }



}
