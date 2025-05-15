package universite_paris8.iut.fan.the_namek_quest.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.fan.the_namek_quest.model.Terrain;
import universite_paris8.iut.fan.the_namek_quest.model.Trunks;
import universite_paris8.iut.fan.the_namek_quest.view.TerrainVue;
import universite_paris8.iut.fan.the_namek_quest.view.TrunksVue;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.application.Application.launch;

public class Controller implements Initializable {
    private Terrain terrain;


    @FXML
    private TilePane tilePane;
    @FXML
    private ImageView persoImage;
    private double persoImageX = 0;
    private final int tailleTuile = 16;

    @FXML
    private Pane pane;

    private Trunks trunks;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.terrain = new Terrain();
        this.trunks = new Trunks();
        TerrainVue terrainVue = new TerrainVue(tilePane, terrain);
        TrunksVue trunksVue = new TrunksVue(pane,trunks);
        Clavier clavier= new Clavier(trunks);
        this.pane.addEventHandler(KeyEvent.KEY_PRESSED,clavier);
    }
}

