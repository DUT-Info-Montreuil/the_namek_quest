package universite_paris8.iut.fan.the_namek_quest.vue;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import universite_paris8.iut.fan.the_namek_quest.modele.Trunks;

import java.awt.*;

public class Scroll {
    @FXML private Pane pane;
    @FXML private StackPane stackPane;
    @FXML private Group group; // gameGroup dans le FXML
    @FXML private Label lifeLabel;
    @FXML
    private AnchorPane hudPane;
    private Trunks trunks;

    private static final double CAMERA_VIEWPORT_WIDTH = 600;
    private static final double CAMERA_VIEWPORT_HEIGHT = 300;
    private boolean cameraCenteredInitially = false;



    private void updateCamera() {
        if (!cameraCenteredInitially) {
            double sceneWidth = stackPane.getWidth(); // Fenêtre visible
            double sceneHeight = stackPane.getHeight();

            double terrainWidth = pane.getPrefWidth(); // Largeur totale du terrain
            double terrainHeight = pane.getPrefHeight();

            double offsetX = (sceneWidth - terrainWidth) / 2;
            double offsetY = (sceneHeight - terrainHeight) / 2;

            group.setTranslateX(offsetX);
            group.setTranslateY(offsetY);

            cameraCenteredInitially = true;
            return;
        }

        // CAMÉRA QUI SUIT LE JOUEUR
        double playerX = trunks.getX();

        double offsetX = CAMERA_VIEWPORT_WIDTH / 2 - playerX;
        double offsetY = CAMERA_VIEWPORT_HEIGHT ;

        double maxOffsetX = 0;
        double maxOffsetY = 0;
        double minOffsetX = CAMERA_VIEWPORT_WIDTH - pane.getPrefWidth();
        double minOffsetY = CAMERA_VIEWPORT_HEIGHT - pane.getPrefHeight();

        offsetX = Math.max(minOffsetX, Math.min(maxOffsetX, offsetX));
        offsetY = Math.max(minOffsetY, Math.min(maxOffsetY, offsetY));

        group.setTranslateX(offsetX);
        group.setTranslateY(offsetY);
    }


}
