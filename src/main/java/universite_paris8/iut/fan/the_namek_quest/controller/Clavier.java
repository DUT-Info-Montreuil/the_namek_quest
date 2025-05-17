package universite_paris8.iut.fan.the_namek_quest.controller;

import javafx.event.EventHandler;
import javafx.scene.input.*;
import universite_paris8.iut.fan.the_namek_quest.model.Trunks;
import universite_paris8.iut.fan.the_namek_quest.view.TrunksVue;

public class Clavier implements EventHandler<KeyEvent> {

    private Trunks trunks;
    private TrunksVue trunksVue;

    public Clavier(Trunks trunks, TrunksVue trunksVue ) {
        this.trunks = trunks;
        this.trunksVue = trunksVue;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        switch (keyEvent.getCode()){
            case RIGHT:
            case D:
                trunks.seDeplacer(0);
                trunksVue.changerImageDroite();
                break;
            case LEFT:
            case Q:
                trunks.seDeplacer(1);
                trunksVue.changerImageGauche();
                break;
            /*case SPACE:
                trunks.sauter();
                break;*/
        }
    }
}
