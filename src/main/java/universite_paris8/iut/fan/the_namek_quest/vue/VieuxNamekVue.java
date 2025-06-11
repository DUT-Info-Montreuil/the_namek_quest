package universite_paris8.iut.fan.the_namek_quest.vue;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import universite_paris8.iut.fan.the_namek_quest.modele.personnage.Dende;
import universite_paris8.iut.fan.the_namek_quest.modele.personnage.VieuxNamek;

public class VieuxNamekVue {

    private VieuxNamek vieuxNamek;
    private ImageView persoImage;
    @FXML private Pane pane;
    private Label labelMessage = null;

    public VieuxNamekVue(Pane pane, VieuxNamek vieuxNamek) {
        this.vieuxNamek = vieuxNamek;
        this.pane = pane;
        this.persoImage = new ImageView();
        this.afficherVieuxNamek();
    }

    public void afficherVieuxNamek() {
        Image imageVieuxNamek = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/vieux.png").toExternalForm());
        persoImage.setImage(imageVieuxNamek);
        persoImage.translateXProperty().bind(vieuxNamek.getXProp());
        persoImage.translateYProperty().bind(vieuxNamek.getYProp());
        pane.getChildren().add(persoImage);
    }

    public void afficherMessageAcceuil() {
        if(vieuxNamek.trunksAProximite()) {
            if (labelMessage == null) {
                labelMessage = new Label("Felicitation Trunks !\n Tu as recupéré une boule de cristale!");
                labelMessage.setFont(new Font("Arial", 12));
                labelMessage.setPrefSize(125, 60);
                labelMessage.setWrapText(true);
                labelMessage.setStyle(
                        "-fx-background-color: white;" +
                                "-fx-border-color: black;" +
                                "-fx-background-radius: 10;" +
                                "-fx-border-radius: 10;"
                );

                labelMessage.setLayoutX(vieuxNamek.getXProp().doubleValue());
                labelMessage.setLayoutY(vieuxNamek.getYProp().doubleValue() - 64);
                pane.getChildren().add(labelMessage);
            }
        } else {
            if (labelMessage != null) {
                System.out.println("pas de vieux namek");
                pane.getChildren().remove(labelMessage);
                labelMessage = null;
            }
        }
    }


}
