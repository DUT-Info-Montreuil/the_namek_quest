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
import universite_paris8.iut.fan.the_namek_quest.Constante;
import universite_paris8.iut.fan.the_namek_quest.modele.*;
import universite_paris8.iut.fan.the_namek_quest.modele.Environnement;
import universite_paris8.iut.fan.the_namek_quest.modele.personnage.GrandChef;
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
    private InventaireListener inventaireListener;
    private Clavier clavier;
    private Souris souris;
    private GrandChefVue grandChefVue;
    private GrandChef grandChef;

    private VieuxNamek vieuxNamek;
    private VieuxNamekVue vieuxNamekVue;
    private Dende dende;
    private DendeVue dendeVue;
    private MoletteControlleur moletteController;


    @FXML private TilePane tilePane;
    @FXML private Pane pane; // pane qui contient trunks + UI
    @FXML private Pane paneInventaire;
    @FXML private Pane paneFond; // pour afficher une image derrière
    @FXML private Pane paneScroll; // pour scroller tout le terrain
    private FondVue fond;

    private GameOver gameOver;
    private MenuDemarrage menuDemarrage;
    private PointVieVue pointVieVue;
    // Scroll global

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
        this.souris = new Souris( environnement, terrainVue,this);
        pane.addEventHandler(MouseEvent.MOUSE_CLICKED, souris);
    }

    public void demarrerJeu() {
        menuDemarrage.retirerMenuDemarrage(pane);

        // ===================== VUES =====================
        this.trunksVue = new TrunksVue(pane, trunks);
        this.grandChefVue = new GrandChefVue(pane, grandChef);
        this.dendeVue = new DendeVue(pane, dende);
        this.vieuxNamekVue = new VieuxNamekVue(pane, vieuxNamek);
        this.pointVieVue = new PointVieVue(trunks, pane);
        this.inventaireVue = new InventaireVue(trunks.getInventaire(), pane, paneInventaire, trunks);
        // ===================== INVENTAIRE =====================
        this.inventaireListener = new InventaireListener(inventaireVue, trunks.getInventaire(), paneInventaire);
        trunks.getInventaire().getListObjects().addListener(inventaireListener);
        // ===================== CONTROLES =====================
        this.clavier = new Clavier(trunks, trunksVue, inventaireVue, grandChef, dende);
        this.moletteController = new MoletteControlleur(trunks, inventaireVue);

        pane.addEventHandler(ScrollEvent.SCROLL, moletteController);
        pane.addEventHandler(KeyEvent.KEY_PRESSED, clavier);
        pane.addEventHandler(KeyEvent.KEY_RELEASED, clavier);
        pane.setFocusTraversable(true);

        Platform.runLater(() -> pane.requestFocus());


        // ===================== FOND =====================
        //this.fond = new FondVue(paneFond); // Fond image placé dans fondVue
        //fond.afficherFond("/universite_paris8/iut/fan/the_namek_quest/images/namek.png");

        // ===================== VUES =====================
        this.terrainVue = new TerrainVue(tilePane, environnement.getTerrain());
        this.trunksVue = new TrunksVue(pane, trunks);
       // this.grandChefVue = new GrandChefVue(tilePane, grandChef);
        this.pointVieVue = new PointVieVue(trunks, pane);
        this.inventaireVue = new InventaireVue(trunks.getInventaire(), pane, paneInventaire, trunks);

        // ===================== INVENTAIRE =====================
        this.inventaireListener = new InventaireListener(inventaireVue, trunks.getInventaire(), paneInventaire);
        trunks.getInventaire().getListObjects().addListener(inventaireListener);

        // ===================== CONTROLES =====================
        this.clavier = new Clavier(trunks, trunksVue, inventaireVue, grandChef,dende);
        this.moletteController = new MoletteControlleur(trunks, inventaireVue);

        pane.addEventHandler(ScrollEvent.SCROLL, moletteController);
        pane.addEventHandler(KeyEvent.KEY_PRESSED, clavier);
        pane.addEventHandler(KeyEvent.KEY_RELEASED, clavier);
        pane.setFocusTraversable(true);
        Platform.runLater(() -> pane.requestFocus());

        //grandChefVue.afficherMessageAcceuil();


        // ===================== GAME LOOP =====================
        initAnimation();
    }

    private void initAnimation() {
        gameLoop = new Timeline(new KeyFrame(Duration.millis(10), ev -> {

            centrerVueSurTrunks();
            environnement.update();
            grandChefVue.afficherMessageAcceuil();
            dendeVue.updateAffichageDende();
            vieuxNamekVue.updateAffichageVieuxNamek();
            trunksVue.changerImage();
            //grandChefVue.afficherMessageAcceuil();

            if (trunks.estMort()) {
                afficherGameOver();
            }
        }));

        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();
    }

    private void centrerVueSurTrunks() {
        double largeurScene = pane.getScene().getWidth();
        double hauteurScene = pane.getScene().getHeight();
        double centreX = largeurScene / 2 - trunks.getX();
        double centreY = hauteurScene / 2 - trunks.getY();


        if(trunks.getX()>30* Constante.TAILLE_TUILE && trunks.getX()<(environnement.getTerrain().largeurTerrain()-30)*Constante.TAILLE_TUILE) {



            paneScroll.setTranslateX(centreX);
            paneScroll.setTranslateY(centreY);


            paneInventaire.setTranslateX(trunks.getX() + 120);
            paneInventaire.setTranslateY(trunks.getY() - 507);

            pointVieVue.getBarreDeVie().setTranslateX(trunks.getX() - 920);
            pointVieVue.getBarreDeVie().setTranslateY(trunks.getY() - 500);

            inventaireVue.getCapsuleVue().setTranslateX(trunks.getX() + 870);
            inventaireVue.getCapsuleVue().setTranslateY(trunks.getY() - 500);
        }

    }

    public void afficherGameOver() {
        gameLoop.stop();
        gameOver = new GameOver();
        gameOver.afficherGameOver(pane);
    }
}
