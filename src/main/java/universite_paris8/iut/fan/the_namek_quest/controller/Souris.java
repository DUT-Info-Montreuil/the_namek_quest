package universite_paris8.iut.fan.the_namek_quest.controller;

/*
    Cette classe gère tous ceux qui est évenements de la souris
 */

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import universite_paris8.iut.fan.the_namek_quest.view.TrunksVue;


public class Souris implements EventHandler<MouseEvent> {


    private TrunksVue tranks;

    public Souris(TrunksVue tranks) {
        this.tranks = tranks;
    }

   @Override
    public void handle(MouseEvent mouseEvent) {
        /*if (mouseEvent.getEventType() == MouseEvent.MOUSE_CLICKED){
            System.out.println("clic");
            if(){

            }
        }*/
    }
}