package universite_paris8.iut.fan.the_namek_quest.controlleur;

/**
 * Classe Clavier
 * --------------
 * Gère les entrées clavier du jeu.
 * Permet de détecter les touches pressées et relâchées
 * et d’agir en conséquence sur le personnage (Trunks), l’inventaire et la vue.
 */


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import universite_paris8.iut.fan.the_namek_quest.modele.GrandChef;
import universite_paris8.iut.fan.the_namek_quest.modele.Trunks;
import universite_paris8.iut.fan.the_namek_quest.vue.InventaireVue;
import universite_paris8.iut.fan.the_namek_quest.vue.TerrainVue;
import universite_paris8.iut.fan.the_namek_quest.vue.TrunksVue;


public class Clavier implements EventHandler<KeyEvent> {

    private Trunks trunks;
    private TrunksVue trunksVue;
    private InventaireVue inventaireVue;
    private TerrainVue  terrainVue;
    private GrandChef grandChef;
    private final BooleanProperty spacePressed = new SimpleBooleanProperty();
    private BooleanProperty qPressed = new SimpleBooleanProperty();
    private final BooleanProperty dPressed = new SimpleBooleanProperty();
    private final BooleanProperty vPressed = new SimpleBooleanProperty();
    private final BooleanProperty ePressed = new SimpleBooleanProperty();

    public Clavier(Trunks trunks, TrunksVue trunksVue, InventaireVue inventaireVue, TerrainVue terrainVue, GrandChef grandChef) {
        this.trunks = trunks;
        this.trunksVue = trunksVue;
        this.inventaireVue = inventaireVue;
        this.terrainVue = terrainVue;
        this.grandChef = grandChef;
    }

    public void setupKeyHandlers(Pane pane) {
        pane.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case SPACE, UP, Z:
                    spacePressed.set(true);
                    break;

                case Q, LEFT:
                    qPressed.set(true);
                    break;
                case D, RIGHT :
                    dPressed.set(true);
                    break;
                case V :
                    vPressed.set(true);
                    break;
                case E:
                    ePressed.set(true);
                    break;
            }
        });

        pane.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case Q -> qPressed.set(false);
                case LEFT -> qPressed.set(false);
                case D -> dPressed.set(false);
                case RIGHT -> dPressed.set(false);
                case V ->  vPressed.set(false);
                case E ->  ePressed.set(false);
            }
        });

    }

    public void handleInventaire(){

        if(inventaireVue.estOuvert()){
            System.out.println("fermé");
            inventaireVue.fermeInventaire();
        }else{
            System.out.println("ouvert");
            inventaireVue.ouvrirInventaire();
        }
    }

    public void handleLeft() {
        trunks.setDirection(-1);
        trunksVue.enMarche = true;

    }

    public void handleRight() {
        trunks.setDirection(1);
        trunksVue.enMarche = true;
    }

    public void handleUp() {
        trunks.sauter();

    }
    public void handleE() {
        grandChef.ameliorerEpee();
    }

    public void handleV() {
        trunks.decrementerPv();
    }

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


