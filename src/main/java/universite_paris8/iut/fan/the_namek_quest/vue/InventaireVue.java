package universite_paris8.iut.fan.the_namek_quest.vue;


/**
 * Classe InventaireVue
 * ---------------------
 * Gère l’affichage graphique de l’inventaire dans l’interface du jeu.
 * Affiche une icône d’accès à l’inventaire, les objets collectés par le joueur,
 * et les informations associées comme la quantité et l’objet équipé.
 **/


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.Inventaire;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.Object;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.materiaux.Materieau;
import universite_paris8.iut.fan.the_namek_quest.modele.Trunks;

import javafx.scene.control.Label;
import javafx.scene.text.Font;


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
        this.capsuleVue = new ImageView(new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/affichageGeneral/inventaire.png") ));

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


        Image pioche = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/outils/pioche.png") );
        Image hache = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/outils/hache.png") );
        Image epee = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/outils/epee.png") );
        Image caseInventaire = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/outils/caseInventaire.png") );
        Image Equipé = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/materieau/CaseObjetEquipe.png") );
        Image terre = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/materieau/herbe.png") );
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
                if(this.trunks.getObjectEquipe().equals(object)){
                    caseInventaireVue = new ImageView(Equipé);
                    caseInventaireVue.setFitHeight(46);
                    caseInventaireVue.setFitWidth(46);
                    caseInventaireVue.setTranslateX(x);
                    caseInventaireVue.setTranslateY(y);
                    paneInventaire.getChildren().add(caseInventaireVue);
                }  else {
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
                        case "épée":
                            img = new ImageView(epee);
                            break;
                        case  "terre":
                                img = new ImageView(terre);
                            break;
                    }

                    img.setTranslateX(x + (46 - 32) / 2);
                    img.setTranslateY(y + (46 - 32) / 2);
                    img.setFitHeight(32);
                    img.setFitWidth(32);
                    paneInventaire.getChildren().add(img);

                    if (object instanceof Materieau){
                        Materieau mat = (Materieau) object;
                        Label labelQuantite = new Label();
                        labelQuantite.textProperty().bind(mat.getQuantiteProp().asString());
                        labelQuantite.setFont(new Font("Arial", 12));
                        labelQuantite.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
                        labelQuantite.setTranslateX(x + 30);// Ajuste la position X
                        labelQuantite.setTranslateY(y + 28); // Ajuste la position Y
                        paneInventaire.getChildren().add(labelQuantite);
                    }

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



