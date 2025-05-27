package universite_paris8.iut.fan.the_namek_quest.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.fan.the_namek_quest.model.Terrain;

public class TerrainVue {

    private TilePane tilePane;
    private Terrain terrain;
    private final int tailleTuile = 32;

    private ImageView[][] tuiles;

    public TerrainVue(TilePane tilePane, Terrain terrain) {
        this.tilePane = tilePane;
        this.terrain = terrain;
        this.afficherTerrain();
    }

    public void afficherTerrain () {
        tilePane.setPrefColumns(terrain.largeurTerrain());
        tilePane.setPrefSize(terrain.largeurTerrain()*tailleTuile,terrain.hauteurTerrain()*tailleTuile);
        tilePane.setFocusTraversable(true);

        Image imageCiel = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/ciel.png") );
        Image imageSol = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/sol.png"));
        Image imageHerbe = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/herbe.png"));

        this.tuiles = new ImageView[terrain.largeurTerrain()][terrain.hauteurTerrain()];


        for(int y = 0; y< terrain.hauteurTerrain(); y++) {
            for(int x = 0; x< terrain.largeurTerrain(); x++) {
                if(this.terrain.codeTuile(x,y)==1){
                    tuiles[x][y] = new ImageView(imageCiel);
                    //tilePane.getChildren().add(new ImageView(imageCiel));
                }
                else if(this.terrain.codeTuile(x,y)==2){
                    tuiles[x][y] = new ImageView(imageSol);
                    //tilePane.getChildren().add(new ImageView(imageSol));
                }
                else if(this.terrain.codeTuile(x,y)==3){
                    tuiles[x][y] = new ImageView(imageHerbe);
                    //tilePane.getChildren().add(new ImageView(imageHerbe));
                }

                tilePane.getChildren().add(tuiles[x][y]);
                /*tuiles[x][y].setFitWidth(tailleTuile);
                tuiles[x][y].setFitHeight(tailleTuile);
                tuiles[x][y].setTranslateX(x*tailleTuile);
                tuiles[x][y].setTranslateY(y*tailleTuile);*/
                //tilePane.getChildren().set(x,y,tuiles[x][y]);
                //tilePane.getChildren().set((y/32)*terrain.hauteurTerrain()+1+x/terrain.largeurTerrain()+1,tuiles[x][y]);
            }
        }
    }

    
    public void changerTuileCiel(int x, int y) {
        System.out.println("changerTuile");

        this.tuiles[x/32][y/32].setImage(new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/ciel.png")));

    }


}
