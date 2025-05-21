package universite_paris8.iut.fan.the_namek_quest.controller;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.layout.Pane;
import universite_paris8.iut.fan.the_namek_quest.model.Trunks;
import universite_paris8.iut.fan.the_namek_quest.view.TrunksVue;

public class Clavier {

    private Trunks trunks;
    private TrunksVue trunksVue;
    private final BooleanProperty spacePressed = new SimpleBooleanProperty();
    private BooleanProperty qPressed = new SimpleBooleanProperty();
    private final BooleanProperty sPressed = new SimpleBooleanProperty();
    private final BooleanProperty dPressed = new SimpleBooleanProperty();

    public Clavier(Trunks trunks, TrunksVue trunksVue ) {
        this.trunks = trunks;
        this.trunksVue = trunksVue;
    }

    public void setupKeyHandlers(Pane pane) {
        pane.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case SPACE -> spacePressed.set(true);
                case UP -> spacePressed.set(true);
                case Q  -> qPressed.set(true);
                case LEFT -> qPressed.set(true);
                case S -> sPressed.set(true);
                case D -> dPressed.set(true);
                case RIGHT -> dPressed.set(true);
            }
        });

        pane.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case Q -> qPressed.set(false);
                case LEFT -> qPressed.set(false);
                case S -> sPressed.set(false);
                case D -> dPressed.set(false);
                case RIGHT -> dPressed.set(false);
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
}
