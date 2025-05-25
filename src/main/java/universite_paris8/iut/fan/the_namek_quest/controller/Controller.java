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
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
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

import static javafx.application.Application.launch;

public class Controller implements Initializable {

    private Environnement environnement;
    private Terrain terrain;
    private Trunks trunks;
    private TrunksVue trunksVue;

    private Timeline gameLoop;
    private InventaireVue inventaireVue;
    private Inventaire inventaire;
    private InventaireListener inventaireListener;
    private Clavier clavier;
    private MoletteController moletteController;

    @FXML private TilePane tilePane;
    @FXML private Pane pane;
    @FXML private Pane paneInventaire;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.environnement = new Environnement();
        this.terrain = new Terrain();
        this.trunks = new Trunks(environnement);
        TerrainVue terrainVue = new TerrainVue(tilePane, terrain);
        this.trunksVue = new TrunksVue(pane,trunks);
        //this.inventaire = new Inventaire();
        this.inventaireVue = new InventaireVue(trunks.getInventaire(), pane, paneInventaire,this.trunks);
        this.inventaireListener = new InventaireListener(inventaireVue,trunks.getInventaire(), paneInventaire);
        trunks.getInventaire().getListObjects().addListener(inventaireListener);
        this.clavier = new Clavier(trunks, trunksVue, inventaireVue);
        this.clavier.setupKeyHandlers(pane);
        this.moletteController = new MoletteController(trunks,inventaireVue);
        this.pane.addEventHandler(ScrollEvent.SCROLL, moletteController);
        this.pane.addEventHandler(KeyEvent.KEY_PRESSED,clavier);
        pane.setFocusTraversable(true); // autorise le focus


        Platform.runLater(() -> pane.requestFocus()); // donne le focus réellement


        initAnimation();
    }

    private void initAnimation() {
        gameLoop = new Timeline(new KeyFrame(Duration.millis(10), (ev -> {
            //trunks.collision(trunks.getX(),  trunks.getY());
            trunks.seDeplacer();
            trunks.setY(terrain.gravite(trunks.getX(), trunks.getY()));
            trunks.setPv(trunks.getPv()-1);
            //clavier.setupKeyHandlers(pane);
            if(clavier.isQPressed()) {
                clavier.handleLeft();
            }
            if(clavier.isDPressed()) {
                clavier.handleRight();
            }
            if(clavier.isSpacePressed()){
                clavier.handleUp();
            }
            /*if(clavier.isIPressed()) {
                clavier.handleInventaire();
            }*/
        })));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();

    }
}

