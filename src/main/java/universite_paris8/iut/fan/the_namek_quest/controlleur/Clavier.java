package universite_paris8.iut.fan.the_namek_quest.controlleur;

/**
 * Classe Clavier
 * --------------
 * Gère les entrées clavier du jeu.
 * Permet de détecter les touches pressées et relâchées
 * et d’agir en conséquence sur le personnage (Trunks), l’inventaire et la vue.
 */


import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import universite_paris8.iut.fan.the_namek_quest.modele.personnage.Dende;
import universite_paris8.iut.fan.the_namek_quest.modele.personnage.GrandChef;
import universite_paris8.iut.fan.the_namek_quest.modele.personnage.Trunks;
import universite_paris8.iut.fan.the_namek_quest.vue.InventaireVue;
import universite_paris8.iut.fan.the_namek_quest.vue.TrunksVue;



public class Clavier implements EventHandler<KeyEvent> {

    private final Trunks trunks;
    private final TrunksVue trunksVue;
    private final InventaireVue inventaireVue;
    private final GrandChef grandChef;
    private final Dende dende;

    public Clavier(Trunks trunks, TrunksVue trunksVue, InventaireVue inventaireVue, GrandChef grandChef, Dende dende) {
        this.trunks = trunks;
        this.trunksVue = trunksVue;
        this.inventaireVue = inventaireVue;
        this.grandChef = grandChef;
        this.dende = dende;
    }

    /** Ouvre ou ferme l’inventaire selon son état actuel */
    private void handleInventaire() {
        if (inventaireVue.estOuvert()) {
            inventaireVue.fermeInventaire();
        } else {
            inventaireVue.ouvrirInventaire();
        }
    }

    /** Déplace Trunks vers la gauche */
    private void handleLeft() {
        trunks.setDirection(-1);
        trunksVue.changerImageGauche();
    }

    /** Déplace Trunks vers la droite */
    private void handleRight() {
        trunks.setDirection(1);
        trunksVue.changerImageDroite();
    }

    /** Fait sauter Trunks */
    private void handleUp() {
        trunks.sauter();
    }

    /** Interagit avec le Grand Chef pour améliorer l’épée */
    private void handleE() {
        grandChef.ameliorerEpee();
    }

    /** Fait perdre des PV à Trunks (méthode pour tester des fonctionnalites) */
    private void handleV() {
        trunks.decrementerPv();
    }

    /** Fait soigner Trunks par Dende s’il est proche */
    private void handleP() {
        dende.soignerTrunksSiProcheEtTouchePressee();
    }

    /** Trunks mange un haricot magique */
    private void handleH() {
        trunks.mangerHaricot();
    }

    /** Gère les événements clavier */
    @Override
    public void handle(KeyEvent keyEvent) {
        if(keyEvent.getEventType() == KeyEvent.KEY_PRESSED) {
            switch (keyEvent.getCode()) {
                case I:
                    handleInventaire();
                    break;
                case Q:
                    handleLeft();
                    break;
                case D:
                    handleRight();
                    break;
                case SPACE,UP:
                    handleUp();
                    break;
                case V:
                    handleV();
                    break;
                case E:
                    handleE();
                    break;
                case P:
                    handleP();
                    break;
                case H:
                    handleH();
                    break;
            }

        }if(keyEvent.getEventType() == KeyEvent.KEY_RELEASED ){
            switch (keyEvent.getCode()) {
                case Q:
                    trunks.setDirection(0);
                    break;
                case D:
                    trunks.setDirection(0);
                    break;
            }

        }
    }
}

