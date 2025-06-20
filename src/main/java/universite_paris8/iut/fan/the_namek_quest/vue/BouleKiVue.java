package universite_paris8.iut.fan.the_namek_quest.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.arme.BouleDeKI;

public class BouleKiVue {

    private Pane pane;
    private BouleDeKI bouleDeKI;
    private ImageView imageBouleView;

    public BouleKiVue(Pane pane, BouleDeKI bouleDeKI) {
        this.pane = pane;
        this.bouleDeKI = bouleDeKI;
        this.imageBouleView = new ImageView();
        afficherBouleDeKi();
    }
    public void afficherBouleDeKi(){
        if(bouleDeKI.getEnAttaqueDistance()){
            Image imageBoule = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/materieau/ki.png").toExternalForm());
            imageBouleView.setImage(imageBoule);
            imageBouleView.setFitWidth(25);
            imageBouleView.setFitHeight(25);
            imageBouleView.translateXProperty().bind(bouleDeKI.getXProp());
            imageBouleView.translateYProperty().bind(bouleDeKI.getYProp());
            pane.getChildren().add(imageBouleView);
        }else {
            pane.getChildren().remove(imageBouleView);
        }


    }

}