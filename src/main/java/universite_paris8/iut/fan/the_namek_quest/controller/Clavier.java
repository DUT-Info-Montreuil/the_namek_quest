package universite_paris8.iut.fan.the_namek_quest.controller;

import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.scene.input.*;

import universite_paris8.iut.fan.the_namek_quest.model.Trunks;

//import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Clavier implements EventHandler<KeyEvent> {

    private Trunks trunks;

    public Clavier(Trunks trunks ) {
        this.trunks = trunks;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        switch (keyEvent.getCode()){
            case D:
                trunks.seDeplacer(0);

            case Q:
                trunks.seDeplacer(1);

        }
    }


}
