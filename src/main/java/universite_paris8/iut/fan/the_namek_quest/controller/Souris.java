package universite_paris8.iut.fan.the_namek_quest.controller;

<<<<<<< HEAD
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class Souris implements EventHandler<MouseEvent> {

    private final Controller controller;

    public Souris(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if (mouseEvent.getEventType() == MouseEvent.MOUSE_CLICKED) {
            System.out.println("clic");
            if (mouseEvent.getX() > 330 && mouseEvent.getX() < 530
                    && mouseEvent.getY() > 420 && mouseEvent.getY() < 480) {
                System.out.println("clic start");
                controller.demarrerJeu();
            }
        }
=======
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
>>>>>>> inventaire
    }
}