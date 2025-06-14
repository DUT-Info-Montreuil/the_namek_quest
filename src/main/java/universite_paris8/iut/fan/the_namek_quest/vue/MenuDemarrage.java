package universite_paris8.iut.fan.the_namek_quest.vue;
/**
 * Classe MenuDemarrage
 * ---------------------
 * Gère l'affichage du menu de démarrage dans le jeu "The Namek Quest".
 * Ce menu s'affiche avant que la partie ne commence, comme un écran d'accueil.
 */


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MenuDemarrage {

    private Pane menuPane;
    private Button startButton;

    public void afficherMenuDemarrage(Pane pane, Runnable actionDemarrerJeu) {
        menuPane = new Pane();

        // Image du menu
        Image menuImage = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/affichageGeneral/menu.png"));
        ImageView imageView = new ImageView(menuImage);
        imageView.setFitWidth(800);
        imageView.setFitHeight(600);

        menuPane.getChildren().add(imageView);

        // Bouton transparent positionné sur la zone "Start"
        startButton = new Button();
        startButton.setStyle("-fx-background-color: transparent;");
        startButton.setPrefSize(150, 70); // largeur, hauteur du bouton
        startButton.setLayoutX(325); // position X
        startButton.setLayoutY(410); // position Y

        // Action au clic
        startButton.setOnAction(e -> {
            actionDemarrerJeu.run();
            retirerMenuDemarrage(pane);
        });

        menuPane.getChildren().add(startButton);
        pane.getChildren().add(menuPane);
    }

    public void retirerMenuDemarrage(Pane pane) {
        pane.getChildren().remove(menuPane);
    }
}
