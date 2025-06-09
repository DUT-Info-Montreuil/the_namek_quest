package universite_paris8.iut.fan.the_namek_quest.vue;

/**
 * Classe TrunksVue
 * ------------------
 * Gère l'affichage graphique du personnage Trunks dans l'interface utilisateur.
 * Elle lie les propriétés du modèle (classe Trunks) à des composants JavaFX
 * comme une ImageView et une barre de vie, pour refléter visuellement son état.
 **/


import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.fan.the_namek_quest.modele.Trunks;

public class TrunksVue{


    private ImageView persoImage;
    private Pane pane;
    private Trunks trunks;
    private AnimationTimer animationTimer;
    private int frameCounter = 0;
    private boolean enMarche = false;
    private boolean alterner;
    private boolean regardAdroite;
    private long lastFrameTime = 0;
    private static final long FRAME_DURATION = 150_000_000; // 150ms par frame
    private AnimationTimer marcheTimer;
    //-----------------------IMAGES----------------------------//
    //immobile
    Image imagePersoDroite = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/trunks/trunks-droite.png").toExternalForm());
    Image imagePersoGauche = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/trunks/trunks-droite.png").toExternalForm());
    //marcheDroite
    Image imagePersoMarheDroite1 = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/trunks/trunks-marche.png").toExternalForm());
    Image imagePersoMarheDroite2 = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/trunks/trunks-marche1.png").toExternalForm());
    //marcheGauche
    Image imagePersoMarcheGauche1 = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/trunks/trunks-marche-gauche.png").toExternalForm());
    Image imagePersoMarcheGauche2 = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/trunks/trunks-marche-gauche1.png").toExternalForm());
    //pioche
    Image imagePersoPiocheDroite = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/trunks/trunks-pioche-droite.png").toExternalForm());
    Image imagePersoPiocheGauche = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/trunks/trunks-pioche-gauche.png").toExternalForm());
    //hache
    Image imagePersoHacheDroite = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/trunks/trunks-hache-droite.png").toExternalForm());
    Image imagePersoHacheGauche = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/trunks/trunks-hache-gauche.png").toExternalForm());
    //epee
    Image imagePersoEpeeDroite = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/trunks/trunks-epee-droite.png").toExternalForm());
    Image imagePersoEpeeGauche = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/trunks/trunks-epee-gauche.png").toExternalForm());


    public TrunksVue(Pane pane, Trunks trunks) {
        this.trunks = trunks;
        this.pane = pane;
        this.persoImage = new ImageView();

        this.afficherTrunks();
        this.startMarcheAnimation();
    }

    public void afficherTrunks() {
        persoImage.setImage(imagePersoDroite);
        persoImage.translateXProperty().bind(trunks.getXProp());
        persoImage.translateYProperty().bind(trunks.getYProp());
        pane.getChildren().add(persoImage);
    }

    private void startMarcheAnimation() {
        marcheTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Changer d'image pour l'animation seulement toutes les FRAME_DURATION
                if (now - lastFrameTime >= FRAME_DURATION) {
                    modifImage();
                    lastFrameTime = now;
                }
            }
        };
        marcheTimer.start();
    }


    public void modifImage() {

        if (trunks.getObjectEquipe() != null) {

            switch (trunks.getObjectEquipe().getId()) {

                case 1:
                    switch (trunks.getDirection()) {
                        case 0:
                            persoImage.setImage(regardAdroite ? imagePersoPiocheDroite : imagePersoPiocheGauche);
                            break;
                        case 1:
                            regardAdroite = true;
                            persoImage.setImage(imagePersoPiocheDroite);
                            break;
                        case -1:
                            regardAdroite = false;
                            persoImage.setImage(imagePersoPiocheGauche);
                            break;
                    }
                    break;

                case 2:
                    switch (trunks.getDirection()) {
                        case 0:
                            persoImage.setImage(regardAdroite ? imagePersoHacheDroite : imagePersoHacheGauche);
                            break;
                        case 1:
                            regardAdroite = true;
                            persoImage.setImage(imagePersoHacheDroite);
                            break;
                        case -1:
                            regardAdroite = false;
                            persoImage.setImage(imagePersoHacheGauche);
                            break;
                    }
                    break;

                case 0:
                    switch (trunks.getDirection()) {
                        case 0:
                            persoImage.setImage(regardAdroite ? imagePersoEpeeDroite : imagePersoEpeeGauche);
                            break;
                        case 1:
                            regardAdroite = true;
                            persoImage.setImage(imagePersoEpeeDroite);
                            break;
                        case -1:
                            regardAdroite = false;
                            persoImage.setImage(imagePersoEpeeGauche);
                            break;
                    }
                    break;
            }

        } else {
            switch (trunks.getDirection()) {
                case 0:
                    persoImage.setImage(regardAdroite ? imagePersoDroite : imagePersoGauche);
                    break;
                case 1:
                    regardAdroite = true;
                    persoImage.setImage(alterner ? imagePersoMarheDroite2 : imagePersoMarheDroite1);
                    alterner = !alterner;
                    break;
                case -1:
                    regardAdroite = false;
                    persoImage.setImage(alterner ? imagePersoMarcheGauche2 : imagePersoMarcheGauche1);
                    alterner = !alterner;
                    break;
            }
        }
    }
}