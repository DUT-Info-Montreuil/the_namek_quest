package universite_paris8.iut.fan.the_namek_quest.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import universite_paris8.iut.fan.the_namek_quest.model.Environnement;
import universite_paris8.iut.fan.the_namek_quest.model.Inventaire.Inventaire;
import universite_paris8.iut.fan.the_namek_quest.model.Terrain;
import universite_paris8.iut.fan.the_namek_quest.model.Trunks;
import universite_paris8.iut.fan.the_namek_quest.view.InventaireVue;
import universite_paris8.iut.fan.the_namek_quest.view.TerrainVue;
import universite_paris8.iut.fan.the_namek_quest.view.TrunksVue;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private Environnement environnement;
    private Terrain terrain;
    private Trunks trunks;
    private TrunksVue trunksVue;

    private Timeline gameLoop;
    private InventaireVue inventaireVue;
    private Inventaire inventaire;
    private Clavier clavier;

    @FXML private TilePane tilePane;
    @FXML private Pane pane;
    @FXML private Pane paneInventaire;

    private Pane menuPane;  // Menu temporaire

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        afficherMenuDemarrage();
    }

    private void afficherMenuDemarrage() {
        menuPane = new Pane();
        menuPane.setPrefSize(800, 600);

        // Image de fond du menu
        Image menuImage = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/menu.png"));
        ImageView imageView = new ImageView(menuImage);
        imageView.setFitWidth(800);
        imageView.setFitHeight(600);

        // Bouton "Démarrer"
        Button startButton = new Button(" ");
        startButton.setStyle("""
        -fx-font-size: 60px;
        -fx-background-color: transparent;
        -fx-border-color: transparent;
        -fx-text-fill: white;
    """);
        startButton.setLayoutX(330);
        startButton.setLayoutY(420);
        startButton.setOnAction(e -> {
            pane.getChildren().remove(menuPane);
            demarrerJeu();
        });

        menuPane.getChildren().addAll(imageView, startButton);
        pane.getChildren().add(menuPane);
    }

    private void demarrerJeu() {
        this.environnement = new Environnement();
        this.terrain = new Terrain();
        this.trunks = new Trunks(environnement);
        TerrainVue terrainVue = new TerrainVue(tilePane, terrain);
        this.trunksVue = new TrunksVue(pane, trunks);
        this.inventaire = new Inventaire();
        this.inventaireVue = new InventaireVue(inventaire, pane, paneInventaire);

        clavier = new Clavier(trunks, trunksVue, inventaireVue);
        clavier.setupKeyHandlers(pane);

        pane.setFocusTraversable(true);
        Platform.runLater(pane::requestFocus);
        initAnimation();
    }

    private void initAnimation() {
        gameLoop = new Timeline(new KeyFrame(Duration.millis(10), (ev -> {
            trunks.seDeplacer();
            if (!trunks.estEnSaut()) {
                trunks.setY(terrain.gravite(trunks.getX(), trunks.getY()));
            } else {
                trunks.gererSaut();
            }

            if (clavier.isQPressed()) clavier.handleLeft();
            if (clavier.isDPressed()) clavier.handleRight();
            if (clavier.isSpacePressed()) clavier.handleUp();
            if (clavier.isVPressed()) clavier.handleV();

        })));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();
    }
}
