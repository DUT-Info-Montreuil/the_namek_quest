package universite_paris8.iut.fan.the_namek_quest.controlleur;

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
import universite_paris8.iut.fan.the_namek_quest.modele.GrandChef;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.Inventaire;
import universite_paris8.iut.fan.the_namek_quest.modele.Terrain;
import universite_paris8.iut.fan.the_namek_quest.modele.Trunks;
import universite_paris8.iut.fan.the_namek_quest.vue.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controlleur implements Initializable {

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
    private GrandChefVue grandChefVue;
    private GrandChef grandChef;

    private MoletteControlleur moletteController;


    @FXML private TilePane tilePane;
    @FXML private Pane pane;
    @FXML private Pane paneInventaire;
    @FXML private Pane borderpane;
    private GameOver gameOver;



    private MenuDemarrage menuDemarrage;
    private PointVieVue pointVieVue;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menuDemarrage = new MenuDemarrage();
        menuDemarrage.afficherMenuDemarrage(pane);


        this.environnement = new Environnement();
        this.trunks = environnement.getTrunks();
        this.grandChef = environnement.getGrandChef();

        this.terrainVue = new TerrainVue(tilePane, environnement.getTerrain());
        souris = new Souris(this,this.environnement,this.terrainVue );
        pane.addEventHandler(MouseEvent.MOUSE_CLICKED, souris);


    }

    public void demarrerJeu() {
        menuDemarrage.retirerMenuDemarrage(pane); // enlève le menu
        this.trunksVue = new TrunksVue(pane,trunks);
        this.grandChefVue = new GrandChefVue(pane,grandChef);
        this.pointVieVue = new PointVieVue(trunks, pane);
        this.inventaireVue = new InventaireVue(trunks.getInventaire(), pane, paneInventaire,this.trunks);
        this.inventaireListener = new InventaireListener(inventaireVue,trunks.getInventaire(), paneInventaire);
        trunks.getInventaire().getListObjects().addListener(inventaireListener);
        this.clavier = new Clavier(trunks, trunksVue, inventaireVue,terrainVue, grandChef);
        this.moletteController = new MoletteControlleur(trunks,inventaireVue);
        this.pane.addEventHandler(ScrollEvent.SCROLL, moletteController);
        this.pane.addEventHandler(KeyEvent.KEY_PRESSED,clavier);
        this.pane.addEventHandler(KeyEvent.KEY_RELEASED,clavier);
        this.grandChefVue.afficherMessageAcceuil();
        pane.setFocusTraversable(true); // autorise le focus

        Platform.runLater(() -> pane.requestFocus()); // donne le focus réellement

        initAnimation();
    }

    private void initAnimation() {
        gameLoop = new Timeline(new KeyFrame(Duration.millis(10), ev -> {
            environnement.update();
            this.centrerVueSurTrunks();
            this.grandChefVue.afficherMessageAcceuil();
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

    private void centrerVueSurTrunks() {
        double decalageX = tilePane.getWidth() / 2 - trunks.getX() - (double) trunks.getX() / 2;
        double decalageY = tilePane.getHeight() / 2 - trunks.getY() - (double) trunks.getY() /2 + tilePane.getHeight()/2;

        pane.setTranslateX(decalageX);
        pane.setTranslateY(decalageY);
    }


    public void afficherGameOver() {
       gameLoop.stop();
        gameOver = new GameOver();
        gameOver.afficherGameOver(pane);
    }
}