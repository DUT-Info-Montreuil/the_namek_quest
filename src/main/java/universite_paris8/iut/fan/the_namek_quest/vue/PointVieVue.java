package universite_paris8.iut.fan.the_namek_quest.vue;

import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import universite_paris8.iut.fan.the_namek_quest.modele.personnage.Personnage;
import universite_paris8.iut.fan.the_namek_quest.modele.personnage.Trunks;

/**
 * Classe PointVieVue
 * ------------------
 * Gère l'affichage graphique de la barre de vie du personnage Trunks.
 * Affiche une barre de progression qui se met à jour en fonction des points de vie.
 */
public class PointVieVue {

    private ProgressBar barreDeVie;
    private Trunks trunks;
    private Pane pane;

    /**
     * Constructeur PointVieVue.
     * @param trunks Le personnage dont on affiche la barre de vie.
     * @param pane Le conteneur dans lequel afficher la barre de vie.
     */
    public PointVieVue(Trunks trunks, Pane pane){
        this.trunks = trunks;
        this.pane = pane;
        this.barreDeVie = new ProgressBar();
        afficherPv();
    }

    /**
     * Initialise et affiche la barre de vie sur le pane.
     * La barre est liée aux points de vie du personnage (entre 0 et 100).
     */
    public void afficherPv(){
        barreDeVie.setStyle("-fx-accent: red;");
        barreDeVie.setPrefWidth(150);
        // La progressProperty est bindée sur le pv du personnage divisé par 100 pour normaliser à [0,1]
        barreDeVie.progressProperty().bind(trunks.getPvProp().divide(100.0));
        pane.getChildren().add(barreDeVie);
    }
}