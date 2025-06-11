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

public class MenuDemarrage {

    private Pane menuPane;

    public void afficherMenuDemarrage(Pane pane) {
        menuPane = new Pane();
        menuPane.setPrefSize(800, 600);

        Image menuImage = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/affichageGeneral/menu.png"));
        ImageView imageView = new ImageView(menuImage);
        imageView.setFitWidth(800);
        imageView.setFitHeight(600);

        menuPane.getChildren().add(imageView);
        pane.getChildren().add(menuPane);
    }

    public void retirerMenuDemarrage(Pane pane) {
        pane.getChildren().remove(menuPane);
    }
}
