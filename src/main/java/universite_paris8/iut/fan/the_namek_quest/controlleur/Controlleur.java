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
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.arme.BouleDeKI;
import universite_paris8.iut.fan.the_namek_quest.modele.personnage.*;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.Inventaire;
import universite_paris8.iut.fan.the_namek_quest.modele.personnage.GrandChef;
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
    private ObservableEnnemis observableEnnemis;
    private VieuxNamek vieuxNamek;
    private VieuxNamekVue vieuxNamekVue;
    private Dende dende;
    private DendeVue dendeVue;
    private MoletteControlleur moletteController;
    private BouleDeKI bouleDeKI;
    private BouleKiVue bouleKiVue;

    @FXML private TilePane tilePane;
    @FXML private Pane pane; // pane qui contient trunks
    @FXML private Pane paneInventaire;
    @FXML private Pane paneScroll; // pour scroller tout le terrain

    private GameOver gameOver;
    private MenuDemarrage menuDemarrage;
    private PointVieVue pointVieVue;
    private int temps = 0;


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
        this.inventaireListener = new InventaireListener(inventaireVue);
        trunks.getInventaire().getListObjects().addListener(inventaireListener);
        // ===================== CONTROLES =====================
        this.clavier = new Clavier(trunks, trunksVue, inventaireVue, grandChef, dende);
        this.moletteController = new MoletteControlleur(trunks, inventaireVue);
        this.bouleDeKI = trunks.getBouleDeKI();

        // === OBSERVATEURS ===
        this.observableEnnemis = new ObservableEnnemis(pane);
        environnement.getPersonnageEnnemisList().addListener(observableEnnemis);

        pane.addEventHandler(ScrollEvent.SCROLL, moletteController);
        pane.addEventHandler(KeyEvent.KEY_PRESSED, clavier);
        pane.addEventHandler(KeyEvent.KEY_RELEASED, clavier);
        pane.setFocusTraversable(true);

        Platform.runLater(() -> pane.requestFocus());

        // === AJOUT DES ENNEMIS ET OBSERVATEURS DE MORT ===
        environnement.ajouterEnnemi();
        for (PersonnageEnnemis ennemi : environnement.getPersonnageEnnemisList()) {
            ennemi.getPvProp().addListener((obs, oldVal, newVal) -> {
                if (ennemi.estMort()) {
                    environnement.getPersonnageEnnemisList().remove(ennemi);
                }
            });
        }

        // === OBSERVATEUR POUR L’ATTAQUE À DISTANCE ===
        bouleDeKI.getEnAttaqueDistanceProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                this.bouleKiVue = new BouleKiVue(pane, bouleDeKI);
            }
        });
        bouleDeKI.setEnAttaqueDistance(false);


        // === OBSERVATEUR POUR LA MORT DE TRUNKS ===
        trunks.getPvProp().addListener((obs, oldVal, newVal) -> {
            if (trunks.estMort()) {
                afficherGameOver();
                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(event -> {
                    Stage stage = (Stage) pane.getScene().getWindow();
                    stage.close();
                });
                pause.play();
            }
        });

        // ===================== GAME LOOP =====================
        initAnimation();
    }

    private void initAnimation() {
        gameLoop = new Timeline(new KeyFrame(Duration.millis(10), ev -> {

            centrerVueSurTrunks();
            environnement.update(temps);
            grandChefVue.afficherMessageAcceuil();
            dendeVue.updateAffichageDende();
            vieuxNamekVue.updateAffichageVieuxNamek();
            trunksVue.changerImage();

            temps ++;
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
