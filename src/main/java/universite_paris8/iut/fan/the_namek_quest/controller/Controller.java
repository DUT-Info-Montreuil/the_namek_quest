package universite_paris8.iut.fan.the_namek_quest.controller;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.util.Duration;
import universite_paris8.iut.fan.the_namek_quest.model.Environnement;
import universite_paris8.iut.fan.the_namek_quest.model.Inventaire.Inventaire;
import universite_paris8.iut.fan.the_namek_quest.model.Terrain;
import universite_paris8.iut.fan.the_namek_quest.model.Trunks;
import universite_paris8.iut.fan.the_namek_quest.view.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private boolean cameraActive = false;
    private boolean toucheSolInitial = false;


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
    @FXML private Pane pane; // contient le terrain
    @FXML private Pane paneInventaire;
    @FXML private Pane borderpane;
    @FXML private StackPane stackPane;
    @FXML private Group group; // gameGroup dans le FXML
    @FXML private Label lifeLabel;
    @FXML private AnchorPane hudPane;



    private GameOver gameOver;
    private MenuDemarrage menuDemarrage;

    private Camera camera;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menuDemarrage = new MenuDemarrage();
        menuDemarrage.afficherMenuDemarrage(stackPane);
        Souris souris = new Souris(this);
        pane.addEventHandler(MouseEvent.MOUSE_CLICKED, souris);
    }

    public void demarrerJeu() {
        menuDemarrage.retirerMenuDemarrage(stackPane);
        this.camera = new Camera(stackPane, pane, group, trunks);
        this.environnement = new Environnement();
        this.terrain = new Terrain();
        this.trunks = new Trunks(environnement);
        this.terrainVue = new TerrainVue(tilePane, terrain);
        this.trunksVue = new TrunksVue(pane,hudPane, trunks);
        this.inventaire = new Inventaire();
        this.inventaireVue = new InventaireVue(inventaire, pane, paneInventaire, hudPane);
        this.clavier = new Clavier(trunks, trunksVue, inventaireVue, terrainVue);

        clavier.setupKeyHandlers(pane);
        pane.setFocusTraversable(true);
        Platform.runLater(() -> pane.requestFocus());

        initAnimation();
    }

    private void initAnimation() {
        gameLoop = new Timeline(new KeyFrame(Duration.millis(10), ev -> {
            trunks.seDeplacer();

            if (!trunks.estEnSaut()) {
                trunks.setY(terrain.gravite(trunks.getX(), trunks.getY()));
            } else {
                trunks.gererSaut();
            }

            clavier.setupKeyHandlers(pane);
            if(clavier.isQPressed()) clavier.handleLeft();
            if(clavier.isDPressed()) clavier.handleRight();
            if(clavier.isSpacePressed()) clavier.handleUp();
            if(clavier.isVPressed()) clavier.handleV();

            // MISE À JOUR DE LA "CAMÉRA"
            updateCamera();


            if(trunks.estMort()) {
                afficherGameOver();
                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(event -> {
                    Stage stage = (Stage) pane.getScene().getWindow();
                    stage.close();
                });
                pause.play();
            }
        }));

        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();
    }

    private void updateCamera(){
        double decalageX = borderPane.getwidth( /2 - ralph.getx()- (double) ralph.getLARGEUR() / 2=
        double decalageY = borderPane.getHeight() / 2 - ralph.getY()- (double) ralph.getHAUTEUR() / 2 + 100;
        paneScroll.setTranslateX(decalageX);
        paneScroll.setTranslateY(decalageY);}
    }


    /*private void updateCamera() {
        if (!cameraCenteredInitially) {
            double sceneWidth = stackPane.getWidth(); // Fenêtre visible
            double sceneHeight = stackPane.getHeight();

            double terrainWidth = pane.getPrefWidth(); // Largeur totale du terrain
            double terrainHeight = pane.getPrefHeight();

            double offsetX = (sceneWidth - terrainWidth) / 2;
            double offsetY = (sceneHeight - terrainHeight) / 2;

            group.setTranslateX(offsetX);
            group.setTranslateY(offsetY);

            cameraCenteredInitially = true;
            return; // On sort pour ne pas exécuter le centrage sur le joueur
        }

        // CAMÉRA QUI SUIT LE JOUEUR
        double playerX = trunks.getX();
        double playerY = trunks.getY();

        double offsetX = CAMERA_VIEWPORT_WIDTH / 2 - playerX;
        double offsetY = (CAMERA_VIEWPORT_HEIGHT/2) - playerY ;

        double maxOffsetX = 0;
        double maxOffsetY = 0;
        double minOffsetX = CAMERA_VIEWPORT_WIDTH - pane.getPrefWidth();
        double minOffsetY = CAMERA_VIEWPORT_HEIGHT - pane.getPrefHeight();

        offsetX = Math.max(minOffsetX, Math.min(maxOffsetX, offsetX));
        offsetY = Math.max(minOffsetY, Math.min(maxOffsetY, offsetY));

        group.setTranslateX(offsetX);
        group.setTranslateY(offsetY);
    }

*/
    public void afficherGameOver() {
        gameLoop.stop();
        gameOver = new GameOver();
        gameOver.afficherGameOver(pane);
    }
}
