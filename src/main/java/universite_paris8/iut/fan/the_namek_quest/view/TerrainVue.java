package universite_paris8.iut.fan.the_namek_quest.view;

import javafx.animation.PauseTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.util.Duration;
import universite_paris8.iut.fan.the_namek_quest.model.Terrain;

public class TerrainVue {

    private TilePane tilePane;
    private Terrain terrain;
    private final int tailleTuile = 32;

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
        Image imageBlanche = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/blanc.png"));
        Image imageArbre = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/arbre.png"));
        Image imageChampignon = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/champgnon.png"));
        Image imageRocher = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/rocher.png"));
        Image imageRoche = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/roche.png"));
        Image imageCoucherSoleil = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/coucher-soleil.png"));





        for(int y = 0; y< terrain.hauteurTerrain(); y++) {
            for(int x = 0; x< terrain.largeurTerrain(); x++) {
                if(this.terrain.codeTuile(x,y)==1){
                    tilePane.getChildren().add(new ImageView(imageCiel));

                }
                else if(this.terrain.codeTuile(x,y)==2){

                    tilePane.getChildren().add(new ImageView(imageSol));
                }
                else if(this.terrain.codeTuile(x,y)==3){

                    tilePane.getChildren().add(new ImageView(imageHerbe));

                } else if (this.terrain.codeTuile(x,y)==4) {
                    tilePane.getChildren().add(new ImageView(imageBlanche));
                } else if (this.terrain.codeTuile(x,y)==5) {
                    tilePane.getChildren().add(new ImageView(imageArbre));
                }
                else if (this.terrain.codeTuile(x,y)==6) {
                    tilePane.getChildren().add(new ImageView(imageChampignon));
                }
                else if (this.terrain.codeTuile(x,y)==7) {
                    tilePane.getChildren().add(new ImageView(imageRocher));
                }
                else if (this.terrain.codeTuile(x,y)==8) {
                    tilePane.getChildren().add(new ImageView(imageRoche));
                }
                else if (this.terrain.codeTuile(x,y)==9) {
                    tilePane.getChildren().add(new ImageView(imageCoucherSoleil));
                }
            }
        }
    }

     public void GameOver(){

         tilePane.getChildren().clear();

        Image imageGameOver = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/gameOver.png") );
        ImageView GameOverView = new ImageView(imageGameOver);
        //GameOverView.setOpacity(0.3);
        tilePane.getChildren().add(GameOverView);
         System.out.println("affiche game over");

         PauseTransition pause = new PauseTransition(Duration.seconds(3));
         pause.setOnFinished(event -> {

             System.out.println("fait la pose");
             Stage stage = (Stage) tilePane.getScene().getWindow();
             stage.close();
         });
         pause.play();

     }
}
