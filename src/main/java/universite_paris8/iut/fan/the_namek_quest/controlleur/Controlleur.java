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
import universite_paris8.iut.fan.the_namek_quest.modele.Environnement;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.arme.BouleDeKI;
import universite_paris8.iut.fan.the_namek_quest.modele.personnage.PersonnageEnnemis;
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

    private Trunks trunks;
    private TrunksVue trunksVue;
    private Terrain terrain;
    private TerrainVue terrainVue;
    private PersonnageEnnemis personnageEnnemis;
    private PersonnageEnnemisVue personnageEnnemisVue;
    private Timeline gameLoop;
    private InventaireVue inventaireVue;
    private Inventaire inventaire;
    private InventaireListener inventaireListener;
    private ObservableEnnemis observableEnnemis;
    private Clavier clavier;
    private Souris souris;
    private GrandChefVue grandChefVue;
    private GrandChef grandChef;
    private VieuxNamekVue vieuxNamekVue;
    private VieuxNamek vieuxNamek;
    private int temps = 0; // Variable pour le temps dans la boucle de jeu

    private MoletteControlleur moletteController;


    @FXML private TilePane tilePane;
    @FXML private Pane pane;
    @FXML private Pane paneInventaire;
    @FXML private Pane borderpane;
    private GameOver gameOver;


    private MenuDemarrage menuDemarrage;
    private PointVieVue pointVieVue;

    private Dende dende;
    private DendeVue dendeVue;

    private BouleKiVue bouleKiVue;
    private BouleDeKI bouleDeKI;

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
        this.inventaireVue = new InventaireVue(trunks.getInventaire(), pane, paneInventaire,this.trunks);
        this.inventaireListener = new InventaireListener(inventaireVue,trunks.getInventaire(), paneInventaire);
        trunks.getInventaire().getListObjects().addListener(inventaireListener);
        this.clavier = new Clavier(trunks, trunksVue, inventaireVue, grandChef, dende);
        this.observableEnnemis = new ObservableEnnemis(pane);
        environnement.getPersonnageEnnemisList().addListener(observableEnnemis);
        this.moletteController = new MoletteControlleur(trunks,inventaireVue);
        this.bouleDeKI = trunks.getBouleDeKI();
        this.pane.addEventHandler(ScrollEvent.SCROLL, moletteController);
        this.pane.addEventHandler(KeyEvent.KEY_PRESSED,clavier);
        this.pane.addEventHandler(KeyEvent.KEY_RELEASED,clavier);
        //this.bouleKiVue = new BouleKiVue(pane,trunks.getBouleDeKI());




        /*this.personnageEnnemis.getPvProp().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() <= 0) {
               pane.getChildren().remove(personnageEnnemisVue.getPersoImage());
                environnement.supprimerEnnemi();

            }
        });*/

        environnement.ajouterEnnemi();
        for (PersonnageEnnemis ennemi : environnement.getPersonnageEnnemisList()) {
            ennemi.getPvProp().addListener((obs, oldVal, newVal) -> {
                if (ennemi.estMort()) {
                    environnement.getPersonnageEnnemisList().remove(ennemi);
                }
            });
        }

        this.bouleDeKI.getEnAttaqueDistanceProperty().addListener((observable, oldValue, newValue) -> {
//            System.out.println("L'état actif a changé : " + oldValue + " -> " + newValue);
//            if(trunks.getBouleDeKI().getEnAttaqueDistance()){
//                System.out.println("rentre dans l'affichage");
//                this.bouleKiVue = new BouleKiVue(pane,trunks.getBouleDeKI());
//            }
            if(newValue== false){
                System.out.println("L'état actif a changé : " + oldValue + " -> " + newValue);
            }

        });

        this.trunks.getBouleDeKI().setEnAttaqueDistance(false);

        pane.setFocusTraversable(true); // autorise le focus

        Platform.runLater(() -> pane.requestFocus()); // donne le focus réellement

        initAnimation();
    }

    private void initAnimation() {

        this.trunks.getBouleDeKI().getEnAttaqueDistanceProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("L'état actif a changé : " + oldValue + " -> " + newValue);
            if(trunks.getBouleDeKI().getEnAttaqueDistance()){
                System.out.println("rentre dans l'affichage");
                this.bouleKiVue = new BouleKiVue(pane,trunks.getBouleDeKI());
            }
        });

        gameLoop = new Timeline(new KeyFrame(Duration.millis(10), ev -> {
            environnement.update(this.temps);
            this.grandChefVue.afficherMessageAcceuil();
            this.dendeVue.updateAffichageDende();
            this.vieuxNamekVue.updateAffichageVieuxNamek();

            if(temps%50==0){
                System.out.println(trunks.getBouleDeKI().getEnAttaqueDistance());
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
            this.temps++;
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