package universite_paris8.iut.fan.the_namek_quest.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.fan.the_namek_quest.model.Terrain;

public class TerrainVue {

    private TilePane tilePane;
    private Terrain terrain;
    private final int tailleTuile = 16;

    public TerrainVue(TilePane tilePane, Terrain terrain) {
        this.tilePane = tilePane;
        this.terrain = terrain;
        this.afficherTerrain();
    }

    public void afficherTerrain() {
        tilePane.setPrefColumns(terrain.largeurTerrain());
        tilePane.setPrefSize(terrain.largeurTerrain() * tailleTuile, terrain.hauteurTerrain() * tailleTuile);
        tilePane.setFocusTraversable(true);
        Image imageCiel = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/ciel.png"));
        Image imageSol = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/sol.png"));
        Image imageHerbe = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/herbe.png"));
        Image imageBlanche = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/blanc.png"));


        for (int y = 0; y < terrain.hauteurTerrain(); y++) {
            for (int x = 0; x < terrain.largeurTerrain(); x++) {
                if (this.terrain.codeTuile(y, x) == 1) {
                    tilePane.getChildren().add(new ImageView(imageCiel));

                } else if (this.terrain.codeTuile(y, x) == 2) {

                    tilePane.getChildren().add(new ImageView(imageSol));
                } else if (this.terrain.codeTuile(y, x) == 3) {

                    tilePane.getChildren().add(new ImageView(imageHerbe));
                } else if (this.terrain.codeTuile(y, x) == 4) {

                    tilePane.getChildren().add(new ImageView(imageBlanche));

                }
            }

        }
    }
}