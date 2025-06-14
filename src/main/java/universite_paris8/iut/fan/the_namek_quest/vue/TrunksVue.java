package universite_paris8.iut.fan.the_namek_quest.vue;

/**
 * Classe TrunksVue
 * ------------------
 * Gère l'affichage graphique du personnage Trunks dans l'interface utilisateur.
 * Elle lie les propriétés du modèle (classe Trunks) à des composants JavaFX
 * comme une ImageView et une barre de vie, pour refléter visuellement son état.
 **/


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.fan.the_namek_quest.modele.personnage.Trunks;

public class TrunksVue {
    private ImageView persoImage;
    private Pane pane;
    private Trunks trunks;


    public TrunksVue(Pane pane,Trunks trunks) {
        this.trunks = trunks;
        this.pane = pane;
        this.persoImage = new ImageView();
        this.afficherTrunks();

    }

    public void afficherTrunks() {
        Image imagePersoDroite = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/trunks/trunks-droite.png").toExternalForm());
        persoImage.setImage(imagePersoDroite);
        persoImage.translateXProperty().bind(trunks.getXProp());
        persoImage.translateYProperty().bind(trunks.getYProp());
        pane.getChildren().add(persoImage);
    }

    public void changerImage(){
        //System.out.println("rentre dans la condition");
        if(trunks.getDirection()=='d'){
            changerImageDroite();
            System.out.println("rentre a droite");
        } else if (trunks.getDirection()=='g') {
            System.out.println("rentre a gauche");
            changerImageGauche();
        }
    }

    public void changerImageDroite() {

        //TODO charger les images une seule fois au démarrage de TrunksVue
        Image imagePersoDroite = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/trunks/trunks-droite.png").toExternalForm());
        switch (trunks.getObjectEquipe().getId()) {
            case 0:
                changerImageEpeeDroite();
                break;
            case 1:
                changerImagePiocheDroite();
                break;
            case 2:
                changerImageHacheDroite();
                break;
            default:
                persoImage.setImage(imagePersoDroite);
                break;
        }

    }

    public void changerImageGauche() {
        Image imagePersoGauche = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/trunks/trunks-gauche.png").toExternalForm());
        switch (trunks.getObjectEquipe().getId()) {
            case 0:
                changerImageEpeeGauche();
                break;
            case 1:
                changerImagePiocheGauche();
                break;
            case 2:
                changerImageHacheGauche();
                break;
            default:
                persoImage.setImage(imagePersoGauche);
                break;
        }
    }

    public void changerImagePiocheDroite(){
        if(this.trunks.getObjectEquipe().getId() == 1){
            Image imagePersoPiocheDroite = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/trunks/trunks-pioche-droite.png").toExternalForm());
            persoImage.setImage(imagePersoPiocheDroite);
        }
    }

    public void changerImagePiocheGauche(){

        if(this.trunks.getObjectEquipe().getId() == 1){
            Image imagePersoPiocheGauche = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/trunks/trunks-pioche-gauche.png").toExternalForm());
            persoImage.setImage(imagePersoPiocheGauche);
        }
    }


    public void changerImageEpeeDroite(){
        if(this.trunks.getObjectEquipe().getId() == 0){
            Image imagePersoEpeeDroite = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/trunks/trunks-epee-droite.png").toExternalForm());
            persoImage.setImage(imagePersoEpeeDroite);
        }
    }

    public void changerImageEpeeGauche(){

        if(this.trunks.getObjectEquipe().getId() == 0){
            System.out.println("epee gauche");
            Image imagePersoEpeeGauche = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/trunks/trunks-epee-gauche.png").toExternalForm());
            persoImage.setImage(imagePersoEpeeGauche);
        }
    }

    public void changerImageHacheDroite(){
        if(this.trunks.getObjectEquipe().getId() == 2){
            Image imagePersoHacheDroite = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/trunks/trunks-hache-droite.png").toExternalForm());
            persoImage.setImage(imagePersoHacheDroite);
        }
    }

    public void changerImageHacheGauche(){
        if(this.trunks.getObjectEquipe().getId() == 2){
            Image imagePersoHacheGauche = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/trunks/trunks-hache-gauche.png").toExternalForm());
            persoImage.setImage(imagePersoHacheGauche);
        }
    }



}