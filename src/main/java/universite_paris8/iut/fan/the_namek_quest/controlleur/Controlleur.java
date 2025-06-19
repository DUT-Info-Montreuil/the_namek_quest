package universite_paris8.iut.fan.the_namek_quest.controlleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import universite_paris8.iut.fan.the_namek_quest.Constante;
import universite_paris8.iut.fan.the_namek_quest.modele.*;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.arme.BouleDeKI;
import universite_paris8.iut.fan.the_namek_quest.modele.personnage.*;
import universite_paris8.iut.fan.the_namek_quest.vue.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controlleur implements Initializable {

    private Environnement environnement;
    private Trunks trunks;
    private TerrainVue terrainVue;
    private TrunksVue trunksVue;
    private GrandChef grandChef;
    private GrandChefVue grandChefVue;
    private Dende dende;
    private DendeVue dendeVue;
    private VieuxNamek vieuxNamek;
    private VieuxNamekVue vieuxNamekVue;
    private PointVieVue pointVieVue;
    private BouleDeKI bouleDeKI;
    private BouleKiVue bouleKiVue;
    private InventaireVue inventaireVue;
    private InventaireListener inventaireListener;
    private Clavier clavier;
    private Souris souris;
    private MoletteControlleur moletteController;
    private ObservableEnnemis observableEnnemis;
    private MenuDemarrage menuDemarrage;
    private GameOver gameOver;
    private Timeline gameLoop;
    private int temps = 0;

    @FXML private TilePane tilePane;
    @FXML private Pane pane;
    @FXML private Pane paneInventaire;
    @FXML private Pane paneScroll;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menuDemarrage = new MenuDemarrage();
        menuDemarrage.afficherMenuDemarrage(pane, this::demarrerJeu);

        this.environnement = new Environnement();
        this.trunks = environnement.getTrunks();
        this.grandChef = environnement.getGrandChef();
        this.dende = environnement.getDende();
        this.vieuxNamek = environnement.getVieuxNamek();
        this.souris = new Souris(environnement, new TerrainVue(tilePane, environnement.getTerrain()), this);
        pane.addEventHandler(MouseEvent.MOUSE_CLICKED, souris);
    }

    public void demarrerJeu() {
        menuDemarrage.retirerMenuDemarrage(pane);

        // === INIT VUES ===
        this.terrainVue = new TerrainVue(tilePane, environnement.getTerrain());
        this.trunksVue = new TrunksVue(pane, trunks);
        this.grandChefVue = new GrandChefVue(pane, grandChef);
        this.dendeVue = new DendeVue(pane, dende);
        this.vieuxNamekVue = new VieuxNamekVue(pane, vieuxNamek);
        this.pointVieVue = new PointVieVue(trunks, pane);


        this.inventaireVue = new InventaireVue(trunks.getInventaire(), pane, paneInventaire, trunks);
        this.inventaireListener = new InventaireListener(inventaireVue, trunks.getInventaire(), paneInventaire);
        trunks.getInventaire().getListObjects().addListener(inventaireListener);

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

        initAnimation();
    }

    private void initAnimation() {
        gameLoop = new Timeline(new KeyFrame(Duration.millis(10), ev -> {


            if (temps % 50 == 0) {
                System.out.println(trunks.getBouleDeKI().getEnAttaqueDistance());
            }
            environnement.update(temps);
            centrerVueSurTrunks();
            grandChefVue.afficherMessageAcceuil();
            dendeVue.updateAffichageDende();
            vieuxNamekVue.updateAffichageVieuxNamek();
            trunksVue.changerImage();

            if (trunks.estMort()) {
                afficherGameOver();
            }
            temps++;
        }));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();
    }

    private void centrerVueSurTrunks() {
        double largeurScene = pane.getScene().getWidth();
        double hauteurScene = pane.getScene().getHeight();
        double centreX = largeurScene / 2 - trunks.getX();
        double centreY = hauteurScene / 2 - trunks.getY();

        if (trunks.getX() > 30 * Constante.TAILLE_TUILE &&
                trunks.getX() < (environnement.getTerrain().largeurTerrain() - 30) * Constante.TAILLE_TUILE) {

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
