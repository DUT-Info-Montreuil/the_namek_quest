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
    private ImageView capsuleVue ;



    private boolean ouvert;

    public InventaireVue(Inventaire inventaire,Pane pane,Pane paneInventaire) {
        this.pane = pane;
        this.inventaire = inventaire;
        this.paneInventaire= paneInventaire;
        this.ouvert = false;
        this.capsuleVue = new ImageView(new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/inventaire.png") ));


        inventaire.addObject(new Pioche());
        inventaire.addObject(new Hache());

        //Image capsule = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/inventaire.png") );

        //ImageView capsuleVue = new ImageView(capsule);
        capsuleVue.setFitHeight(46);
        capsuleVue.setFitWidth(46);
        capsuleVue.setTranslateX(736);
        capsuleVue.setTranslateY(0);

        afficherLogoInventaire();
    }

    public void afficherLogoInventaire(){

        if(!this.estOuvert()){
            this.paneInventaire.getChildren().add(capsuleVue);
        }else {
            this.paneInventaire.getChildren().remove(capsuleVue);

        }

    }



    public void afficherContenuInventaire(){


        Image pioche = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/pioche.png") );
        Image hache = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/hache.png") );
        Image caseInventaire = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/caseInventaire.png") );

        ImageView caseInventaireVue;

        int x = 736;
        int y = 46;

        if(!inventaire.getObjects().isEmpty()){
            this.ouvert = true;
            for (Object object : inventaire.getObjects()) {
                if(x<100){
                    x=736;
                    y = y + 46;
                }

                caseInventaireVue = new ImageView(caseInventaire);

                caseInventaireVue.setFitHeight(46);
                caseInventaireVue.setFitWidth(46);
                caseInventaireVue.setTranslateX(x);
                caseInventaireVue.setTranslateY(y);
                paneInventaire.getChildren().add(caseInventaireVue);

                ImageView img = new ImageView();
                switch (object.getName()) {
                    case "Pioche":
                        img = new ImageView(pioche);
                        break;
                    case "Hache":
                        img = new ImageView(hache);
                        break;
                }

                img.setTranslateX(x+(46-32)/2);
                img.setTranslateY(y+(46-32)/2);
                img.setFitHeight(32);
                img.setFitWidth(32);
                paneInventaire.getChildren().add(img);

                x=x-46;

            }
        }
    }

    public boolean estOuvert(){
        return this.ouvert;
    }

    public void ouvrirInventaire(){
        paneInventaire.setVisible(true);
        afficherContenuInventaire();
        //afficherLogoInventaire();
    }

    public void fermeInventaire() {
        this.ouvert = false;
        paneInventaire.getChildren().removeAll();
        paneInventaire.setVisible(false);
        //afficherLogoInventaire();
    }

}



