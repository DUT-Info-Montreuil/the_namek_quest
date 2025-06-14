package universite_paris8.iut.fan.the_namek_quest.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Classe GameOver
 * ------------------
 * Gère l'affichage de l'écran de fin de jeu (Game Over).
 * Affiche une image plein écran pour informer le joueur que la partie est terminée.
 */
public class GameOver {

    private Pane gameOverPane;

    /**
     * Affiche l'écran Game Over sur le pane donné.
     *
     * - Crée un nouveau Pane contenant une ImageView avec l'image "gameOver".
     * - L'image est redimensionnée en 800x600 (plein écran supposé).
     * - Vide le pane passé en paramètre et ajoute ce nouvel écran Game Over.
     *
     * @param pane Pane JavaFX sur lequel afficher l'écran Game Over
     */
    public void afficherGameOver(Pane pane) {
        gameOverPane = new Pane();

        // Chargement de l'image Game Over depuis les ressources
        Image gameOverImage = new Image(getClass().getResourceAsStream(
                "/universite_paris8/iut/fan/the_namek_quest/images/affichageGeneral/gameOver.png"));

        ImageView imageView = new ImageView(gameOverImage);

        // Redimensionnement de l'image pour correspondre à la taille souhaitée
        imageView.setFitWidth(800);
        imageView.setFitHeight(600);

        // Ajout de l'image dans le pane dédié à Game Over
        gameOverPane.getChildren().add(imageView);

        // Nettoyage du pane principal et ajout du pane Game Over
        pane.getChildren().clear();
        pane.getChildren().add(gameOverPane);
    }
}