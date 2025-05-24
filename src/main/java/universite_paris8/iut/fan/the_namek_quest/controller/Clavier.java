package universite_paris8.iut.fan.the_namek_quest.controller;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import universite_paris8.iut.fan.the_namek_quest.model.Inventaire.Inventaire;
import universite_paris8.iut.fan.the_namek_quest.model.Trunks;
import universite_paris8.iut.fan.the_namek_quest.view.InventaireVue;
import universite_paris8.iut.fan.the_namek_quest.view.TerrainVue;
import universite_paris8.iut.fan.the_namek_quest.view.TrunksVue;

public class Clavier implements EventHandler<KeyEvent> {

    private Trunks trunks;
    private TrunksVue trunksVue;
    private InventaireVue inventaireVue;
    private TerrainVue  terrainVue;
    private final BooleanProperty spacePressed = new SimpleBooleanProperty();
    private BooleanProperty qPressed = new SimpleBooleanProperty();
    private final BooleanProperty sPressed = new SimpleBooleanProperty();
    private final BooleanProperty dPressed = new SimpleBooleanProperty();
    private final BooleanProperty vPressed = new SimpleBooleanProperty();


    public Clavier(Trunks trunks, TrunksVue trunksVue, InventaireVue inventaireVue, TerrainVue terrainVue) {
        this.trunks = trunks;
        this.trunksVue = trunksVue;
        this.inventaireVue = inventaireVue;
        this.terrainVue = terrainVue;
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
                case V ->  vPressed.set(true);

            }
        });

        pane.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case Q -> qPressed.set(false);
                case LEFT -> qPressed.set(false);
                case S -> sPressed.set(false);
                case D -> dPressed.set(false);
                case RIGHT -> dPressed.set(false);
                case V ->  vPressed.set(false);
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
    public boolean isVPressed() { return vPressed.get(); }


    public void handleInventaire(){
        inventaireVue.ouvrirInventaire();
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

    public void handleV() {
        trunks.decrementerPv();
        vPressed.set(false);
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case M:
                terrainVue.GameOver();
                break;
        }
    }
}
