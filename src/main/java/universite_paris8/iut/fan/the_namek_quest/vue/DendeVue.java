package universite_paris8.iut.fan.the_namek_quest.vue;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import universite_paris8.iut.fan.the_namek_quest.modele.personnage.Dende;

public class DendeVue {

    private Dende dende;
    private ImageView persoImage;
    @FXML private Pane pane;
    private Label labelMessage = null;

    public DendeVue(Pane pane, Dende dende) {
        this.dende = dende;
        this.pane = pane;
    }

    public void updateAffichageDende() {
        if (dende.estVisible()) {
            if (persoImage == null) {
                Image imageDende = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/PNJ/dendeNuage.png").toExternalForm());
                persoImage = new ImageView(imageDende);
                persoImage.translateXProperty().bind(dende.getXProp());
                persoImage.translateYProperty().bind(dende.getYProp());
                pane.getChildren().add(persoImage);
            }
        } else {
            if (persoImage != null) {
                pane.getChildren().remove(persoImage);
                persoImage = null;
            }
        }
        afficherMessageAcceuil();
    }


    public void afficherMessageAcceuil() {
        if (dende.estVisible()) {
            if (labelMessage == null) {
                labelMessage = new Label(" Trunks! \n Avec moi tu peux augmenter tes PV.\n Appuie sur 'P' une fois devant moi !");
                labelMessage.setFont(new Font("Arial", 12));
                labelMessage.setPrefSize(160, 90);
                labelMessage.setWrapText(true);
                labelMessage.setStyle(
                        "-fx-background-color: white;" +
                                "-fx-border-color: black;" +
                                "-fx-background-radius: 10;" +
                                "-fx-border-radius: 10;"
                );
                labelMessage.setLayoutX(dende.getXProp().doubleValue());
                labelMessage.setLayoutY(dende.getYProp().doubleValue() - 106);
                pane.getChildren().add(labelMessage);
            }
        } else {
            if (labelMessage != null) {
                pane.getChildren().remove(labelMessage);
                labelMessage = null;
            }
        }
    }

}
