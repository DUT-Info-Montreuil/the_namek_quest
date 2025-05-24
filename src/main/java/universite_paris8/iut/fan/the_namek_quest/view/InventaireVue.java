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
import universite_paris8.iut.fan.the_namek_quest.model.Trunks;

public class InventaireVue {

    private Pane pane;
    private Inventaire inventaire;
    private Trunks trunks;
    private Pane paneInventaire;
    private ImageView capsuleVue ;
    private boolean ouvert;

    public InventaireVue(Inventaire inventaire,Pane pane,Pane paneInventaire,Trunks trunks) {

        this.pane = pane;
        this.inventaire = inventaire;
        this.paneInventaire= paneInventaire;
        this.ouvert = false;
        this.trunks=trunks;
        this.capsuleVue = new ImageView(new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/inventaire.png") ));

        inventaire.addObject(new Pioche());
        inventaire.addObject(new Hache());


        capsuleVue.setFitHeight(46);
        capsuleVue.setFitWidth(46);
        capsuleVue.setTranslateX(752);
        capsuleVue.setTranslateY(0);

        afficherLogoInventaire();
    }


    public void afficherLogoInventaire() {
        if (!this.estOuvert()) {
            if (!pane.getChildren().contains(capsuleVue)) {
                pane.getChildren().add(capsuleVue);
            }
        } else {
            pane.getChildren().remove(capsuleVue);
        }
    }

    public void afficherContenuInventaire(){


        Image pioche = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/pioche.png") );
        Image hache = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/hache.png") );
        Image caseInventaire = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/caseInventaire.png") );
        Image Equipé = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/CaseObjetEquipe.png") );
        ImageView caseInventaireVue;
        ImageView EquipéVue;

        int x = 753;
        int y = 0;

        if(!inventaire.getListObjects().isEmpty()){
            this.ouvert = true;
            for (Object object : inventaire.getListObjects()) {
                if(x<100){
                    x=753;
                    y = y + 46;
                }
                if(this.trunks.getObjectEquipe().getId()==object.getId()){
                    caseInventaireVue = new ImageView(Equipé);
                    caseInventaireVue.setFitHeight(46);
                    caseInventaireVue.setFitWidth(46);
                    caseInventaireVue.setTranslateX(x);
                    caseInventaireVue.setTranslateY(y);
                    paneInventaire.getChildren().add(caseInventaireVue);
                }else {
                    caseInventaireVue = new ImageView(caseInventaire);
                    caseInventaireVue.setFitHeight(46);
                    caseInventaireVue.setFitWidth(46);
                    caseInventaireVue.setTranslateX(x);
                    caseInventaireVue.setTranslateY(y);
                    paneInventaire.getChildren().add(caseInventaireVue);
                }
                    ImageView img = new ImageView();
                    switch (object.getName()) {
                        case "Pioche":
                            img = new ImageView(pioche);
                            break;
                        case "Hache":
                            img = new ImageView(hache);
                            break;
                    }

                    img.setTranslateX(x + (46 - 32) / 2);
                    img.setTranslateY(y + (46 - 32) / 2);
                    img.setFitHeight(32);
                    img.setFitWidth(32);
                    paneInventaire.getChildren().add(img);

                    x = x - 46;

            }
        }
    }

    public boolean estOuvert(){
        return this.ouvert;
    }

    public void ouvrirInventaire(){
        this.ouvert = true;
        paneInventaire.setVisible(true);
        afficherContenuInventaire();
        pane.getChildren().remove(capsuleVue);
        //inventaire.addObject(new Hache());
    }

    public void fermeInventaire() {
        this.ouvert = false;
        paneInventaire.getChildren().clear();
        paneInventaire.setVisible(false);
        pane.getChildren().add(capsuleVue);
        //inventaire.removeObjectsIndex();
    }
}



