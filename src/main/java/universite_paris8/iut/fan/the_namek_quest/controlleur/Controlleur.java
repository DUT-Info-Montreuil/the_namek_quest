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
import universite_paris8.iut.fan.the_namek_quest.modele.*;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.Inventaire;
import universite_paris8.iut.fan.the_namek_quest.modele.personnage.Dende;
import universite_paris8.iut.fan.the_namek_quest.modele.personnage.GrandChef;
import universite_paris8.iut.fan.the_namek_quest.modele.personnage.Trunks;
import universite_paris8.iut.fan.the_namek_quest.modele.personnage.VieuxNamek;
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
    private VieuxNamekVue vieuxNamekVue;
    private VieuxNamek vieuxNamek;

    private MoletteControlleur moletteController;


    @FXML private TilePane tilePane;
    @FXML private Pane pane;
    @FXML private Pane paneInventaire;
    @FXML private Pane borderpane;
    private GameOver gameOver;
    private KiVue kiVue;



    private MenuDemarrage menuDemarrage;
    private PointVieVue pointVieVue;

    private Dende dende;
    private DendeVue dendeVue;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menuDemarrage = new MenuDemarrage();
        menuDemarrage.afficherMenuDemarrage(pane, this::demarrerJeu);
        this.environnement = new Environnement();
        this.trunks = environnement.getTrunks();
        this.grandChef = environnement.getGrandChef();
        this.dende = environnement.getDende();
        this.vieuxNamek = environnement.getVieuxNamek();
        this.terrainVue = new TerrainVue(tilePane, environnement.getTerrain());
        souris = new Souris(this.environnement,this.terrainVue );
        pane.addEventHandler(MouseEvent.MOUSE_CLICKED, souris);
    }

    public void demarrerJeu() {
        menuDemarrage.retirerMenuDemarrage(pane); // enlève le menu
        this.trunksVue = new TrunksVue(pane,trunks);
        this.grandChefVue = new GrandChefVue(pane,grandChef);
        this.dendeVue = new DendeVue(pane,dende);
        this.vieuxNamekVue = new VieuxNamekVue(pane, vieuxNamek);
        this.pointVieVue = new PointVieVue(trunks, pane);
        this.kiVue = new KiVue(trunks, pane);
        this.inventaireVue = new InventaireVue(trunks.getInventaire(), pane, paneInventaire,this.trunks);
        this.inventaireListener = new InventaireListener(inventaireVue,trunks.getInventaire(), paneInventaire);
        trunks.getInventaire().getListObjects().addListener(inventaireListener);
        this.clavier = new Clavier(trunks, trunksVue, inventaireVue, grandChef, dende);
        this.moletteController = new MoletteControlleur(trunks,inventaireVue);
        this.pane.addEventHandler(ScrollEvent.SCROLL, moletteController);
        this.pane.addEventHandler(KeyEvent.KEY_PRESSED,clavier);
        this.pane.addEventHandler(KeyEvent.KEY_RELEASED,clavier);
        pane.setFocusTraversable(true); // autorise le focus

        Platform.runLater(() -> pane.requestFocus()); // donne le focus réellement

        initAnimation();
    }

    private void initAnimation() {
        gameLoop = new Timeline(new KeyFrame(Duration.millis(10), ev -> {
            environnement.update();
            this.grandChefVue.afficherMessageAcceuil();
            this.dendeVue.updateAffichageDende();
            this.vieuxNamekVue.updateAffichageVieuxNamek();
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