package universite_paris8.iut.fan.the_namek_quest.view;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import universite_paris8.iut.fan.the_namek_quest.model.Inventaire.Inventaire;

public class InventaireVue {

    private Pane pane;
    private Inventaire inventaire;
    private Pane paneInventaire;

    private boolean ouvert;
    private AnchorPane anchorPaneInventaire;

    public InventaireVue(Inventaire inventaire, Pane pane, Pane paneInventaire, AnchorPane anchorPaneInventaire) {
        this.pane = pane;
        this.inventaire = inventaire;
        this.paneInventaire= paneInventaire;
        this.ouvert = false;
        this.anchorPaneInventaire = anchorPaneInventaire;

        afficherInventaire();

    }

    public void afficherInventaire(){
        Image capsule = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/inventaire.png") );
        ImageView capsuleVue = new ImageView(capsule);
        capsuleVue.setFitHeight(46);
        capsuleVue.setFitWidth(46);
        capsuleVue.setTranslateX(850);
        capsuleVue.setTranslateY(0);
        this.anchorPaneInventaire.getChildren().add(capsuleVue);
    }

    public void ouvrirInventaire(){

    }
}
