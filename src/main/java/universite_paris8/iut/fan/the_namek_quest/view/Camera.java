package universite_paris8.iut.fan.the_namek_quest.view;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import universite_paris8.iut.fan.the_namek_quest.model.Trunks;

public class Camera {

    private final StackPane stackPane;
    private final Pane pane;
    private final Group group;
    private final Trunks trunks;

    private boolean cameraCenteredInitially = false;
    private static final double CAMERA_VIEWPORT_WIDTH = 600;
    private static final double CAMERA_VIEWPORT_HEIGHT = 300;

    public Camera(StackPane stackPane, Pane pane, Group group, Trunks trunks){
        this.stackPane = stackPane;
        this.pane = pane;
        this.group = group;
        this.trunks = trunks;
    }

    public void update() {
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
            return; // On sort pour ne pas exécuter le centrage sur le joueur
        }

        // CAMÉRA QUI SUIT LE JOUEUR
        double playerX = trunks.getX();
        double playerY = trunks.getY();

        double offsetX = CAMERA_VIEWPORT_WIDTH / 2 - playerX;
        double offsetY = (CAMERA_VIEWPORT_HEIGHT/2) - playerY ;

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
