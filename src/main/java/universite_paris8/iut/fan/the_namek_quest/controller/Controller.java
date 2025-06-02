package universite_paris8.iut.fan.the_namek_quest.controller;

/**
 * Classe Controller
 * ----------------
 * Contrôleur principal du jeu. Gère l'initialisation,
 * la liaison entre modèle (Environnement, Trunks, Inventaire, Terrain),
 * et vue (TerrainVue, TrunksVue, InventaireVue, MenuDemarrage).
 * Assure la gestion des événements clavier, souris et molette,
 * ainsi que la boucle de jeu (animation et mise à jour).
 */

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
import universite_paris8.iut.fan.the_namek_quest.modele.Environnement;
import universite_paris8.iut.fan.the_namek_quest.modele.Inventaire.Inventaire;
import universite_paris8.iut.fan.the_namek_quest.modele.Terrain;
import universite_paris8.iut.fan.the_namek_quest.modele.Trunks;
import universite_paris8.iut.fan.the_namek_quest.vue.GameOver;
import universite_paris8.iut.fan.the_namek_quest.vue.InventaireVue;
import universite_paris8.iut.fan.the_namek_quest.vue.MenuDemarrage;
import universite_paris8.iut.fan.the_namek_quest.vue.TerrainVue;
import universite_paris8.iut.fan.the_namek_quest.vue.TrunksVue;
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
    private Souris souris;


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
        menuDemarrage.afficherMenuDemarrage(pane);


        this.environnement = new Environnement();
        this.trunks = environnement.getTrunks();

        this.terrainVue = new TerrainVue(tilePane, environnement.getTerrain());
        souris = new Souris(this,this.environnement,this.terrainVue );
        pane.addEventHandler(MouseEvent.MOUSE_CLICKED, souris);


    }

    public void demarrerJeu() {
        menuDemarrage.retirerMenuDemarrage(pane); // enlève le menu
        this.trunksVue = new TrunksVue(pane,trunks);
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
        pane.addEventHandler(MouseEvent.MOUSE_CLICKED, souris);

        Platform.runLater(() -> pane.requestFocus()); // donne le focus réellement

        initAnimation();
    }

    private void initAnimation() {
        gameLoop = new Timeline(new KeyFrame(Duration.millis(10), ev -> {
            environnement.update();

            //TODO : à déplacer dans l'écouteur de clavier
            if(clavier.isQPressed()) {
                clavier.handleLeft();
            }
            if(clavier.isDPressed()) {
                clavier.handleRight();
            }
            if(clavier.isSpacePressed()){
                clavier.handleUp();
            }
            if(clavier.isVPressed()){
                clavier.handleV();
            }


            if(trunks.estMort()) { //TODO déclencher par un listener sur les pts de vie
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