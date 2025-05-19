package universite_paris8.iut.fan.the_namek_quest.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import universite_paris8.iut.fan.the_namek_quest.model.Environnement;
import universite_paris8.iut.fan.the_namek_quest.model.Terrain;
import universite_paris8.iut.fan.the_namek_quest.model.Trunks;
import universite_paris8.iut.fan.the_namek_quest.view.TerrainVue;
import universite_paris8.iut.fan.the_namek_quest.view.TrunksVue;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.application.Application.launch;

public class Controller implements Initializable {

    private Environnement environnement;
    private Terrain terrain;
    private Trunks trunks;

    private Timeline gameLoop;
    private int temps;

    @FXML private TilePane tilePane;
    @FXML private Pane pane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.environnement = new Environnement();
        this.terrain = new Terrain();
        this.trunks = new Trunks(environnement);
        TerrainVue terrainVue = new TerrainVue(tilePane, terrain);
        TrunksVue trunksVue = new TrunksVue(pane,trunks);
        Clavier clavier = new Clavier(trunks, trunksVue);
        pane.setFocusTraversable(true); // autorise le focus
        Platform.runLater(() -> pane.requestFocus()); // donne le focus réellement
        pane.addEventHandler(KeyEvent.KEY_PRESSED, clavier);// écoute les touches
        initAnimation();

    }

    private void initAnimation() {
        gameLoop = new Timeline(new KeyFrame(Duration.seconds(0.017), (ev -> {
            trunks.gravite();
            trunks.seDeplacer();
        })));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();

    }
}

