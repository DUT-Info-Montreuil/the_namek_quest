package universite_paris8.iut.fan.the_namek_quest;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import universite_paris8.iut.fan.the_namek_quest.model.Terrain;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.application.Application.launch;

public class Controller implements Initializable {
    private Terrain terrain;

    @FXML
    private TilePane tilePane;

    @FXML
    private ImageView persoImage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.terrain = new Terrain();
        tilePane.setPrefColumns(terrain.largeurTerrain());
        tilePane.setPrefSize(terrain.largeurTerrain()*16,terrain.hauteurTerrain()*16);
        tilePane.setFocusTraversable(true) ;
        Image imageCiel = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/ciel.png") );
        Image imageSol = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/sol.png"));
        Image imageHerbe = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/herbe.png"));
        Image imageperso = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/trunks.png"));
        persoImage.setImage(imageperso);
        persoImage.setTranslateX(5*16);
        persoImage.setTranslateY(4*16);
        //boucle
        for(int i = 0; i< terrain.hauteurTerrain(); i++) {
            for(int j = 0; j< terrain.largeurTerrain(); j++) {
                if(this.terrain.getTerrain()[i][j]==1){
                    tilePane.getChildren().add(new ImageView(imageCiel));
                    System.out.println("1");
                }
                else if(this.terrain.getTerrain()[i][j]==2){
                    System.out.println("2");
                    tilePane.getChildren().add(new ImageView(imageSol));
                }
                else if(this.terrain.getTerrain()[i][j]==3){
                    System.out.println("3");
                    tilePane.getChildren().add(new ImageView(imageHerbe));
                }
                /*else if(this.terrain.getTerrain()[i][j]==4){
                    System.out.println("4");
                    tilePane.getChildren().add(new ImageView(imageperso));
                }*/
            }
        }
    }
}
