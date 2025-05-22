package universite_paris8.iut.fan.the_namek_quest.controller;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import universite_paris8.iut.fan.the_namek_quest.model.Inventaire.Inventaire;
import universite_paris8.iut.fan.the_namek_quest.model.Trunks;
import universite_paris8.iut.fan.the_namek_quest.view.InventaireVue;
import universite_paris8.iut.fan.the_namek_quest.view.TrunksVue;

public class Clavier implements EventHandler<KeyEvent> {

    private Trunks trunks;
    private TrunksVue trunksVue;
    private InventaireVue inventaireVue;
    private final BooleanProperty spacePressed = new SimpleBooleanProperty();
    private BooleanProperty qPressed = new SimpleBooleanProperty();
    private final BooleanProperty sPressed = new SimpleBooleanProperty();
    private final BooleanProperty dPressed = new SimpleBooleanProperty();
    private final BooleanProperty iPressed = new SimpleBooleanProperty();

    public Clavier(Trunks trunks, TrunksVue trunksVue, InventaireVue inventaireVue) {
        this.trunks = trunks;
        this.trunksVue = trunksVue;
        this.inventaireVue = inventaireVue;
    }

    public void setupKeyHandlers(Pane pane) {
        pane.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case SPACE -> spacePressed.set(true);
                case UP -> spacePressed.set(true);
                case Z -> spacePressed.set(true);
                case Q  -> qPressed.set(true);
                case LEFT -> qPressed.set(true);
                case S -> sPressed.set(true);
                case D -> dPressed.set(true);
                case RIGHT -> dPressed.set(true);
                //case I -> iPressed.set(true);
            }
        });

        pane.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case Q -> qPressed.set(false);
                case LEFT -> qPressed.set(false);
                case S -> sPressed.set(false);
                case D -> dPressed.set(false);
                case RIGHT -> dPressed.set(false);
               // case I -> iPressed.set(false);
            }
        });
    }


    public boolean isQPressed() {
        return qPressed.get();
    }

    public boolean isDPressed() {
        return dPressed.get();
    }

    public boolean isSpacePressed() {
        return spacePressed.get();
    }

    public boolean isIPressed() {
        return iPressed.get();
    }

    public void handleInventaire(){

        if(inventaireVue.estOuvert()){
            System.out.println("fermé");
            inventaireVue.fermeInventaire();

            //iPressed.set(false);
        }else{
            System.out.println("ouvert");
            inventaireVue.ouvrirInventaire();

        }
    }

    public void handleLeft() {
        trunks.setDirection('g');
        trunksVue.changerImageGauche();
    }

    public void handleRight() {
        trunks.setDirection('d');
        trunksVue.changerImageDroite();
    }

    public void handleUp() {
        trunks.sauter();
        spacePressed.set(false);
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case I:

                handleInventaire();

                break;
        }
    }
}
