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
    }

    public void updateAffichageVieuxNamek() {
        boolean doitAfficher = vieuxNamek.estVisible();

        if (doitAfficher) {
            if (persoImage == null) {
                Image imageVieuxNamek = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/vieux.png").toExternalForm());
                persoImage = new ImageView(imageVieuxNamek);
                persoImage.translateXProperty().bind(vieuxNamek.getXProp());
                persoImage.translateYProperty().bind(vieuxNamek.getYProp());
                pane.getChildren().add(persoImage);
            }
            // Afficher message si Trunks est proche
            afficherMessageAcceuil();
        } else {
            if (persoImage != null) {
                pane.getChildren().remove(persoImage);
                persoImage = null;
            }
            if (labelMessage != null) {
                pane.getChildren().remove(labelMessage);
                labelMessage = null;
            }
        }
    }

    public void afficherMessageAcceuil() {
        if (vieuxNamek.trunksAProximite()) {
            if (labelMessage == null) {
                labelMessage = new Label("Félicitations Trunks !\nTu as récupéré une nouvelle boule de cristal !");
                labelMessage.setFont(new Font("Arial", 12));
                labelMessage.setPrefSize(160, 90);
                labelMessage.setWrapText(true);
                labelMessage.setStyle(
                        "-fx-background-color: white;" +
                                "-fx-border-color: black;" +
                                "-fx-background-radius: 10;" +
                                "-fx-border-radius: 10;"
                );
                pane.getChildren().add(labelMessage);
            }

            // Mise à jour de la position du message pour qu’il suive le Namek
            labelMessage.setLayoutX(vieuxNamek.getXProp().doubleValue());
            labelMessage.setLayoutY(vieuxNamek.getYProp().doubleValue() - 106);
        } else {
            if (labelMessage != null) {
                pane.getChildren().remove(labelMessage);
                labelMessage = null;
            }
        }
    }
}






