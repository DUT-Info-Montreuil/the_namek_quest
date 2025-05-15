package universite_paris8.iut.fan.the_namek_quest.controller;

import javafx.event.EventHandler;
import javafx.scene.input.*;
import universite_paris8.iut.fan.the_namek_quest.model.Trunks;

public class Clavier implements EventHandler<KeyEvent> {

    private Trunks trunks;

    public Clavier(Trunks trunks ) {
        this.trunks = trunks;
    }


    @Override
    public void handle(KeyEvent keyEvent) {
        switch (keyEvent.getCode()){
            case RIGHT:
            case D:
                trunks.seDeplacer(0);
                break;
            case LEFT:
            case Q:
                trunks.seDeplacer(1);
                break;
            case SPACE:
                trunks.sauter();
                break;
        }
    }
}
