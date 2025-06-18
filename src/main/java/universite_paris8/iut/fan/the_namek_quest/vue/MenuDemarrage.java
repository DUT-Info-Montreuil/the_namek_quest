package universite_paris8.iut.fan.the_namek_quest.vue;



import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;

/**
 * Classe MenuDemarrage
 * ---------------------
 * Gère l'affichage du menu de démarrage dans le jeu "The Namek Quest".
 * Ce menu s'affiche avant que la partie ne commence, comme un écran d'accueil.
 */

public class MenuDemarrage {

    private Pane menuPane;
    private Button startButton;

    /**
     * Affiche le menu de démarrage sur le pane donné.
     * @param pane Le conteneur principal dans lequel afficher le menu.
     * @param actionDemarrerJeu Action à exécuter lors du clic sur le bouton "Start".
     */
    public void afficherMenuDemarrage(Pane pane, Runnable actionDemarrerJeu) {
        menuPane = new Pane();


        // Image de fond du menu
        Image menuImage = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/affichageGeneral/menu.png"));
        ImageView imageView = new ImageView(menuImage);
        menuPane.getChildren().add(imageView);

        // Bouton transparent pour déclencher le démarrage du jeu
        startButton = new Button();
        startButton.setStyle("-fx-background-color: transparent;");
        startButton.setPrefSize(400, 175);    // Taille du bouton
        startButton.setLayoutX(765);         // Position X
        startButton.setLayoutY(725);         // Position Y

        // Action au clic du bouton "Start"
        startButton.setOnAction(e -> {
            actionDemarrerJeu.run();
            retirerMenuDemarrage(pane);
        });

        menuPane.getChildren().add(startButton);
        pane.getChildren().add(menuPane);
    }

    /**
     * Retire le menu de démarrage du pane.
     * @param pane Le conteneur principal dans lequel le menu est affiché.
     */
    public void retirerMenuDemarrage(Pane pane) {
        pane.getChildren().remove(menuPane);
    }
}