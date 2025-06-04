package universite_paris8.iut.fan.the_namek_quest.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.fan.the_namek_quest.modele.PersonnageEnnemis;

public class PersonnageEnnemisVue {
    private PersonnageEnnemis personnageEnnemis;
    private Pane pane;
    private ImageView persoImage;


    public PersonnageEnnemisVue(Pane pane, PersonnageEnnemis personnageEnnemis) {
        this.personnageEnnemis = personnageEnnemis;
        this.pane = pane;
        this.persoImage = new ImageView();
        afficherEnnemis();
    }

    public void afficherEnnemis(){
        Image imageEnnemis = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/soldat_freezer.png").toExternalForm());
        persoImage.setImage(imageEnnemis);
        persoImage.translateXProperty().bind(personnageEnnemis.getXProp());
        persoImage.translateYProperty().bind(personnageEnnemis.getYProp());
        pane.getChildren().add(persoImage);

    }


}
