package universite_paris8.iut.fan.the_namek_quest.controller;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.input.MouseEvent;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;

import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.util.Duration;
import universite_paris8.iut.fan.the_namek_quest.model.Environnement;
import universite_paris8.iut.fan.the_namek_quest.model.Inventaire.Inventaire;
import universite_paris8.iut.fan.the_namek_quest.model.Terrain;
import universite_paris8.iut.fan.the_namek_quest.model.Trunks;
import universite_paris8.iut.fan.the_namek_quest.view.GameOver;
import universite_paris8.iut.fan.the_namek_quest.view.InventaireVue;
import universite_paris8.iut.fan.the_namek_quest.view.MenuDemarrage;
import universite_paris8.iut.fan.the_namek_quest.view.TerrainVue;
import universite_paris8.iut.fan.the_namek_quest.view.TrunksVue;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private Environnement environnement;
    private Terrain terrain;
    private Trunks trunks;
    private TrunksVue trunksVue;
    private TerrainVue terrainVue;
    private Timeline gameLoop;
    private InventaireVue inventaireVue;
    private Inventaire inventaire;
    private InventaireListener inventaireListener;
    private Clavier clavier;

    private MoletteController moletteController;


    @FXML private TilePane tilePane;
    @FXML private Pane pane;
    @FXML private Pane paneInventaire;
    @FXML private Pane borderpane;
    private GameOver gameOver;



    private MenuDemarrage menuDemarrage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menuDemarrage = new MenuDemarrage();
        menuDemarrage.afficherMenuDemarrage(pane); // Affiche le menu image

        Souris souris = new Souris(this);
        pane.addEventHandler(MouseEvent.MOUSE_CLICKED, souris);
    }

    public void demarrerJeu() {
        menuDemarrage.retirerMenuDemarrage(pane); // enlève le menu
        this.environnement = new Environnement();
        this.terrain = new Terrain();
        this.trunks = new Trunks(environnement);

        this.terrainVue = new TerrainVue(tilePane, terrain);




        this.trunksVue = new TrunksVue(pane,trunks);
        //this.inventaire = new Inventaire();
        this.inventaireVue = new InventaireVue(trunks.getInventaire(), pane, paneInventaire,this.trunks);
        this.inventaireListener = new InventaireListener(inventaireVue,trunks.getInventaire(), paneInventaire);
        trunks.getInventaire().getListObjects().addListener(inventaireListener);
        this.clavier = new Clavier(trunks, trunksVue, inventaireVue,terrainVue);
        this.clavier.setupKeyHandlers(pane);
        this.moletteController = new MoletteController(trunks,inventaireVue);
        this.pane.addEventHandler(ScrollEvent.SCROLL, moletteController);
        this.pane.addEventHandler(KeyEvent.KEY_PRESSED,clavier);
        pane.setFocusTraversable(true); // autorise le focus
        clavier.setupKeyHandlers(pane);
        pane.setFocusTraversable(true);


        Platform.runLater(() -> pane.requestFocus()); // donne le focus réellement



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
            }if(trunks.estMort()) {
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

    public void afficherGameOver() {
        gameLoop.stop();
        gameOver = new GameOver();
        gameOver.afficherGameOver(pane);
    }

}
