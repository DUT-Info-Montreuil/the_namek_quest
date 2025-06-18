package universite_paris8.iut.fan.the_namek_quest.vue;





import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.fan.the_namek_quest.modele.personnage.Trunks;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;


/**
 * Classe TrunksVue
 * ------------------
 * Gère l'affichage graphique du personnage Trunks dans l'interface utilisateur.
 * Elle lie les propriétés du modèle (classe Trunks) à des composants JavaFX
 * comme une ImageView et une barre de vie, pour refléter visuellement son état.
 **/




public class TrunksVue {

    private final Trunks trunks;
    private final Pane pane;
    private final ImageView persoImage;

    // Images de base
    private final Image trunksDroite, trunksGauche;
    private final Image epeeDroite, epeeGauche;
    private final Image piocheDroite, piocheGauche;
    private final Image hacheDroite, hacheGauche;

    // Images de marche
    private final Image imgMarche1Droite, imgMarche2Droite;
    private final Image imgMarche1Gauche, imgMarche2Gauche;

    private Timeline animationMarche;
    private boolean marcheImageToggle = false;

    public TrunksVue(Pane pane, Trunks trunks) {
        this.trunks = trunks;
        this.pane = pane;
        this.persoImage = new ImageView();

        // Chargement des images
        trunksDroite = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/trunks/trunks-droite.png").toExternalForm());
        trunksGauche = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/trunks/trunks-gauche.png").toExternalForm());

        epeeDroite = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/trunks/trunks-epee-droite.png").toExternalForm());
        epeeGauche = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/trunks/trunks-epee-gauche.png").toExternalForm());

        piocheDroite = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/trunks/trunks-pioche-droite.png").toExternalForm());
        piocheGauche = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/trunks/trunks-pioche-gauche.png").toExternalForm());

        hacheDroite = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/trunks/trunks-hache-droite.png").toExternalForm());
        hacheGauche = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/trunks/trunks-hache-gauche.png").toExternalForm());

        imgMarche1Droite = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/trunks/trunks-marche1.png").toExternalForm());
        imgMarche2Droite = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/trunks/trunks-marche.png").toExternalForm());
        imgMarche1Gauche = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/trunks/trunks-marche-gauche.png").toExternalForm());
        imgMarche2Gauche = new Image(getClass().getResource("/universite_paris8/iut/fan/the_namek_quest/images/trunks/trunks-marche-gauche1.png").toExternalForm());

        afficherTrunks();
    }

    public void afficherTrunks() {
        persoImage.setImage(trunksDroite); // Image par défaut
        persoImage.translateXProperty().bind(trunks.getXProp());
        persoImage.translateYProperty().bind(trunks.getYProp());
        pane.getChildren().add(persoImage);
    }


    public void demarrerAnimationMarche() {
        if (animationMarche != null && animationMarche.getStatus() == Animation.Status.RUNNING) return;

        animationMarche = new Timeline(new KeyFrame(Duration.millis(150), e -> {
            marcheImageToggle = !marcheImageToggle;

            if (trunks.getDirection() == 'd') {
                persoImage.setImage(marcheImageToggle ? imgMarche1Droite : imgMarche2Droite);
            } else if (trunks.getDirection() == 'g') {
                persoImage.setImage(marcheImageToggle ? imgMarche1Gauche : imgMarche2Gauche);
            }
        }));

        animationMarche.setCycleCount(Animation.INDEFINITE);
        animationMarche.play();
    }

    public void arreterAnimationMarche() {
        if (animationMarche != null) {
            animationMarche.stop();
            animationMarche = null;

            // Image fixe quand on arrête de marcher
            remettreImageStatique();
        }
    }

    public void remettreImageStatique() {
        if (trunks.getDirection() == 1) {
            persoImage.setImage(trunksDroite);
        } else if (trunks.getDirection() == -1) {
            persoImage.setImage(trunksGauche);
        } else {
            persoImage.setImage(trunksDroite); // Par défaut
        }
    }

    public void changerImage() {
        if (trunks.getDirection() == 1) {
            changerImageDroite();
        } else if (trunks.getDirection() == -1) {
            changerImageGauche();
        }
    }


    public void changerImageDroite() {
        switch (trunks.getObjectEquipe().getId()) {
            case 0 -> persoImage.setImage(epeeDroite);
            case 1 -> persoImage.setImage(piocheDroite);
            case 2 -> persoImage.setImage(hacheDroite);
            default -> persoImage.setImage(trunksDroite);
        }
    }

    public void changerImageGauche() {
        switch (trunks.getObjectEquipe().getId()) {
            case 0 -> persoImage.setImage(epeeGauche);
            case 1 -> persoImage.setImage(piocheGauche);
            case 2 -> persoImage.setImage(hacheGauche);
            default -> persoImage.setImage(trunksGauche);
        }
    }
}
