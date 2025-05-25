package universite_paris8.iut.fan.the_namek_quest.controller;

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
            if (mouseEvent.getX() > 350 && mouseEvent.getX() < 550
                    && mouseEvent.getY() > 450 && mouseEvent.getY() < 500) {
                System.out.println("clic start");
                controller.demarrerJeu();
            }
        }
    }
}