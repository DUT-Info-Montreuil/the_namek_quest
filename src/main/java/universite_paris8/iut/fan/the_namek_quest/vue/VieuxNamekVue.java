package universite_paris8.iut.fan.the_namek_quest.vue;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import universite_paris8.iut.fan.the_namek_quest.modele.personnage.VieuxNamek;

/**
 * Classe VieuxNamekVue
 * --------------------
 * Gère l'affichage graphique du personnage PNJ VieuxNamek dans l'interface JavaFX.
 * Elle affiche ou cache l'image du personnage en fonction de sa visibilité,
 * et affiche un message d’accueil lorsque Trunks est proche.
 */

public class VieuxNamekVue {

    private VieuxNamek vieuxNamek;     // Modèle représentant le PNJ
    private ImageView persoImage;      // ImageView du VieuxNamek affichée dans le Pane
    private Pane pane;                 // Conteneur JavaFX où s’affiche le PNJ et le message
    private Label labelMessage = null; // Label affichant un message d’accueil

    /**
     * Constructeur.
     * @param pane Pane JavaFX sur lequel afficher le PNJ et le message.
     * @param vieuxNamek Instance du modèle VieuxNamek à représenter.
     */
    public VieuxNamekVue(Pane pane, VieuxNamek vieuxNamek) {
        this.vieuxNamek = vieuxNamek;
        this.pane = pane;
    }

    /**
     * Met à jour l’affichage du VieuxNamek.
     * Affiche ou cache l’image selon la visibilité,
     * et affiche un message si Trunks est à proximité.
     */
    public void updateAffichageVieuxNamek() {
        boolean doitAfficher = vieuxNamek.estVisible();

        if (doitAfficher) {
            if (persoImage == null) {
                Image imageVieuxNamek = new Image(getClass().getResource(
                        "/universite_paris8/iut/fan/the_namek_quest/images/PNJ/vieux.png"
                ).toExternalForm());
                persoImage = new ImageView(imageVieuxNamek);
                persoImage.translateXProperty().bind(vieuxNamek.getXProp());
                persoImage.translateYProperty().bind(vieuxNamek.getYProp());
                pane.getChildren().add(persoImage);
            }
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

    /**
     * Affiche un message d’accueil au-dessus du PNJ
     * si Trunks est proche. Le message suit la position du PNJ.
     */
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

