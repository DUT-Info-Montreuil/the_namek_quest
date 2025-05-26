package universite_paris8.iut.fan.the_namek_quest.controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import universite_paris8.iut.fan.the_namek_quest.model.Environnement;
import universite_paris8.iut.fan.the_namek_quest.model.Terrain;

public class Souris implements EventHandler<MouseEvent> {

    private final Controller controller;
    private Environnement environnement;

    public Souris(Controller controller , Environnement environnement) {
        this.controller = controller;
        this.environnement = environnement;
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
            System.out.println(environnement.getTerrain().creuserBlocPioche(environnement.getTrunks().getX(), environnement.getTrunks().getY(), mouseEvent.getX(), mouseEvent.getY()));
        }
    }
}