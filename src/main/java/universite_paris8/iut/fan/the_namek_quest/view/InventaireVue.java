package universite_paris8.iut.fan.the_namek_quest.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.fan.the_namek_quest.model.Inventaire.Inventaire;

public class InventaireVue {

    private Pane pane;
    private Inventaire inventaire;
    private Pane paneInventaire;

    private boolean ouvert;

    public InventaireVue(Inventaire inventaire,Pane pane,Pane paneInventaire) {
        this.pane = pane;
        this.inventaire = inventaire;
        this.paneInventaire= paneInventaire;
        this.ouvert = false;

        afficherInventaire();

    }

    public void afficherInventaire(){
        Image capsule = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/inventaire.png") );
        ImageView capsuleVue = new ImageView(capsule);
        capsuleVue.setFitHeight(46);
        capsuleVue.setFitWidth(46);
        capsuleVue.setTranslateX(50);
        capsuleVue.setTranslateY(50);
        this.pane.getChildren().add(capsuleVue);
    }
}
