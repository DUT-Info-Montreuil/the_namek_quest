package universite_paris8.iut.fan.the_namek_quest.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.fan.the_namek_quest.model.Inventaire.Inventaire;
import universite_paris8.iut.fan.the_namek_quest.model.Inventaire.Object;
import universite_paris8.iut.fan.the_namek_quest.model.Inventaire.arme.Arme;
import universite_paris8.iut.fan.the_namek_quest.model.Inventaire.arme.Epee;
import universite_paris8.iut.fan.the_namek_quest.model.Inventaire.outils.Hache;
import universite_paris8.iut.fan.the_namek_quest.model.Inventaire.outils.Pioche;
import universite_paris8.iut.fan.the_namek_quest.model.Inventaire.ressource.Materieau;

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


        inventaire.addObject(new Pioche());
        inventaire.addObject(new Hache());



        afficherLogoInventaire();
    }

    public void afficherLogoInventaire(){
        Image capsule = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/inventaire.png") );

        ImageView capsuleVue = new ImageView(capsule);
        capsuleVue.setFitHeight(46);
        capsuleVue.setFitWidth(46);
        capsuleVue.setTranslateX(736);
        capsuleVue.setTranslateY(0);
        this.pane.getChildren().add(capsuleVue);
    }

    public void ouvrirInventaire(){
        afficherContenuInventaire();
    }

    public void afficherContenuInventaire(){
        Image pioche = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/pioche.png") );
        Image hache = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/hache.png") );


    }
}
