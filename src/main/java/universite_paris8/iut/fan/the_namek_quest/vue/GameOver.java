package universite_paris8.iut.fan.the_namek_quest.vue;

/**
 * Classe GameOver
 * ------------------
 * Gère l'affichage de l'écran de fin de jeu (Game Over).
 * Affiche une image plein écran pour informer le joueur que la partie est terminée.
 **/


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class GameOver {

    private Pane gameOverPane;

    public void afficherGameOver(Pane pane) {
        gameOverPane = new Pane();
        gameOverPane.setPrefSize(800, 600);

        Image gameOverImage = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/affichageGeneral/gameOver.png"));
        ImageView imageView = new ImageView(gameOverImage);
        imageView.setFitWidth(800);
        imageView.setFitHeight(600);

        gameOverPane.getChildren().add(imageView);
        pane.getChildren().clear();
        pane.getChildren().add(gameOverPane);
    }
}

