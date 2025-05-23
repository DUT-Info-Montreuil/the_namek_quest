package universite_paris8.iut.fan.the_namek_quest.controller;

/**
 * Classe Controller
 * -----------------
 * Contrôleur principal de l'application JavaFX. Gère l'initialisation, la boucle de jeu et la communication entre modèle et vue.
 * - Initialise l'environnement, le terrain, le personnage et les vues.
 * - Lance et maintenir la boucle principale du jeu (animation, gravité, déplacements).
 * - Gère les entrées clavier et les actions correspondantes.
 */

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
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


import static javafx.application.Application.launch;
import static javafx.application.Application.setUserAgentStylesheet;

public class Controller implements Initializable {

    private Environnement environnement;
    private Terrain terrain;
    private Trunks trunks;
    private TrunksVue trunksVue;
    private TerrainVue terrainVue;

    private Timeline gameLoop;
    private InventaireVue inventaireVue;
    private Inventaire inventaire;
    private Clavier clavier;

    @FXML private TilePane tilePane;
    @FXML private Pane pane;
    @FXML private Pane paneInventaire;
    @FXML private Pane borderpane;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.environnement = new Environnement();
        this.terrain = new Terrain();
        this.trunks = new Trunks(environnement);
        this.terrainVue = new TerrainVue(tilePane, terrain);
        this.trunksVue = new TrunksVue(pane,trunks);
        this.inventaire = new Inventaire();
        this.inventaireVue = new InventaireVue(inventaire, pane, paneInventaire);
        clavier = new Clavier(trunks, trunksVue, inventaireVue);
        clavier.setupKeyHandlers(pane);
        pane.setFocusTraversable(true); // autorise le focus
        Platform.runLater(() -> pane.requestFocus()); // donne le focus réellement
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
            clavier.setupKeyHandlers(pane);
            if(clavier.isQPressed()) {
                clavier.handleLeft();
            }
            if(clavier.isDPressed()) {
                clavier.handleRight();
            }
            if(clavier.isSpacePressed()){
                clavier.handleUp();
            }if(clavier.isVPressed()){
                clavier.handleV();
            }if(trunks.estMort()){
                terrainVue.GameOver();

                PauseTransition pause = new PauseTransition(Duration.seconds(5));
                pause.setOnFinished(event -> {

                    System.out.println("fait la pose");
                    Stage stage = (Stage) pane.getScene().getWindow();
                    stage.close();
                });
                pause.play();


            }

        })));

        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();
    }
}

