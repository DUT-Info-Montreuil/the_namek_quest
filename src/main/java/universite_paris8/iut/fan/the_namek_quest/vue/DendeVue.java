package universite_paris8.iut.fan.the_namek_quest.vue;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import universite_paris8.iut.fan.the_namek_quest.modele.personnage.Dende;

/**
 * Classe DendeVue
 * ----------------
 * Gère l'affichage graphique de l'objet Dende dans la scène JavaFX.
 *
 * Fonctionnalités :
 * - Affiche ou masque l'image du personnage Dende selon sa visibilité.
 * - Affiche un message d'accueil lorsque Dende est visible.
 */
public class DendeVue {

    private final Dende dende;          // Objet métier Dende (position, visibilité)
    private ImageView persoImage = null; // ImageView pour afficher le sprite de Dende
    @FXML
    private final Pane pane;             // Pane JavaFX dans lequel on affiche Dende
    private Label labelMessage = null;  // Label affichant le message d'accueil

    /**
     * Constructeur.
     *
     * @param pane Pane JavaFX où afficher les éléments graphiques
     * @param dende Instance de Dende à représenter graphiquement
     */
    public DendeVue(Pane pane, Dende dende) {
        this.pane = pane;
        this.dende = dende;
    }

    /**
     * Met à jour l'affichage de Dende.
     *
     * - Si Dende est visible et que l'image n'est pas encore créée, la crée et l'ajoute au pane.
     * - Si Dende n'est pas visible et que l'image existe, la retire du pane.
     * - Met à jour l'affichage du message d'accueil selon visibilité.
     */
    public void updateAffichageDende() {
        if (dende.estVisible()) {
            // Création et ajout de l'image si pas encore créée
            if (persoImage == null) {
                Image imageDende = new Image(getClass().getResource(
                                "/universite_paris8/iut/fan/the_namek_quest/images/PNJ/dendeNuage.png")
                        .toExternalForm());
                persoImage = new ImageView(imageDende);

                // Lie la position de l'image aux propriétés X et Y de Dende
                persoImage.translateXProperty().bind(dende.getXProp());
                persoImage.translateYProperty().bind(dende.getYProp());

                pane.getChildren().add(persoImage);
            }
        } else {
            // Dende n'est pas visible, on enlève l'image si elle existe
            if (persoImage != null) {
                pane.getChildren().remove(persoImage);
                persoImage = null;
            }
        }
        // Met à jour le message d'accueil en fonction de la visibilité
        afficherMessageAcceuil();
    }

    /**
     * Affiche ou masque le message d'accueil de Dende.
     *
     * Le message s'affiche uniquement si Dende est visible.
     * Positionné juste au-dessus du personnage.
     */
    public void afficherMessageAcceuil() {
        if (dende.estVisible()) {
            if (labelMessage == null) {
                labelMessage = new Label(
                        " Trunks! \n Avec moi tu peux augmenter tes PV.\n Appuie sur 'P' une fois devant moi !");
                labelMessage.setFont(new Font("Arial", 12));
                labelMessage.setPrefSize(160, 90);
                labelMessage.setWrapText(true);

                // Style : fond blanc arrondi, bordure noire arrondie
                labelMessage.setStyle(
                        "-fx-background-color: white;" +
                                "-fx-border-color: black;" +
                                "-fx-background-radius: 10;" +
                                "-fx-border-radius: 10;"
                );

                // Positionner le label juste au-dessus de Dende (décalage de 106 pixels)
                labelMessage.setLayoutX(dende.getXProp().doubleValue());
                labelMessage.setLayoutY(dende.getYProp().doubleValue() - 106);

                pane.getChildren().add(labelMessage);
            }
        } else {
            // Dende non visible : on enlève le message s'il est présent
            if (labelMessage != null) {
                pane.getChildren().remove(labelMessage);
                labelMessage = null;
            }
        }
    }
}