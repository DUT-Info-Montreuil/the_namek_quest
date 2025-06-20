package universite_paris8.iut.fan.the_namek_quest.vue;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import universite_paris8.iut.fan.the_namek_quest.modele.personnage.GrandChef;
import javafx.scene.image.Image;

/**
 * Classe GrandChefVue
 * --------------------
 * Gère l'affichage du personnage GrandChef et
 * affiche un message d'accueil si Trunks est à proximité.
 */
public class GrandChefVue {

    private ImageView persoImage;       // Image représentant le GrandChef
    @FXML
    private Pane pane;                  // Pane JavaFX où on affiche le GrandChef et le message
    private GrandChef grandChef;        // Objet métier GrandChef à représenter
    private Label labelMessage = null;  // Label pour le message d'accueil

    /**
     * Constructeur.
     * Initialise les références et affiche l'image du GrandChef.
     *
     * @param pane Pane JavaFX pour l'affichage
     * @param grandChef Instance métier GrandChef à afficher
     */
    public GrandChefVue(Pane pane, GrandChef grandChef) {
        this.grandChef = grandChef;
        this.pane = pane;
        this.persoImage = new ImageView();
        afficherGrandChef();
    }

    /**
     * Affiche l'image du GrandChef et la lie à sa position.
     */
    public void afficherGrandChef() {
        Image imageGrandChef = new Image(
                getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/PNJ/grandchef.png").toExternalForm()
        );

        persoImage.setImage(imageGrandChef);
        persoImage.translateXProperty().bind(grandChef.getXProp());
        persoImage.translateYProperty().bind(grandChef.getYProp());

        pane.getChildren().add(persoImage);
    }

    /**
     * Affiche un message d'accueil si Trunks est proche du GrandChef.
     * Le message disparaît sinon.
     */
    public void afficherMessageAcceuil() {
        if (grandChef.trunksAProximite()) {
            if (labelMessage == null) {
                labelMessage = new Label(
                        "Bonjour Trunks,\navec moi tu peux améliorer ton épée.\n" +
                                "Appuie sur 'e' avec ton épée en main et d'avoir 3 roches et 2 boules d'énergie !"
                );

                labelMessage.setFont(new Font("Arial", 12));
                labelMessage.setPrefSize(180, 105);
                labelMessage.setWrapText(true);
                labelMessage.setStyle(
                        "-fx-background-color: white;" +
                                "-fx-border-color: black;" +
                                "-fx-background-radius: 10;" +
                                "-fx-border-radius: 10;"
                );

                // Positionner le message légèrement au-dessus et à gauche du GrandChef
                labelMessage.setLayoutX(grandChef.getXProp().doubleValue() - 155);
                labelMessage.setLayoutY(grandChef.getYProp().doubleValue() - 106);

                pane.getChildren().add(labelMessage);
            }
        } else {
            // Supprime le message si Trunks n'est plus proche
            if (labelMessage != null) {
                pane.getChildren().remove(labelMessage);
                labelMessage = null;
            }
        }
    }
}