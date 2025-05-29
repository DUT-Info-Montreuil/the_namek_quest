package universite_paris8.iut.fan.the_namek_quest.controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import universite_paris8.iut.fan.the_namek_quest.model.Environnement;
import universite_paris8.iut.fan.the_namek_quest.view.TerrainVue;

public class Souris implements EventHandler<MouseEvent> {

    private final Controller controller;
    private Environnement environnement;
    private TerrainVue terrainVue;

    public Souris(Controller controller, Environnement environnement, TerrainVue terrainVue) {
        this.controller = controller;
        this.environnement = environnement;
        this.terrainVue = terrainVue;
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


            /// TODO verifier en premier la range du click puis quel type de block a ete cliqué et agir en fonction du bloque


            if (environnement.getTerrain().rangeCreuser(environnement.getTrunks().getX(), environnement.getTrunks().getY(), mouseEvent.getX(), mouseEvent.getY())) {
                System.out.println("entre dans la range");
                if (environnement.getTrunks().getObjectEquipe().getId() == 1) {

                    environnement.getTrunks().getInventaire().ajoutRessource(environnement.getTerrain().codeTuilePixel((int) mouseEvent.getX(),(int) mouseEvent.getY()));
                    environnement.getTerrain().casserBloc(mouseEvent.getX(), mouseEvent.getY());
                    this.terrainVue.changerTuileCiel((int) mouseEvent.getX(), (int) mouseEvent.getY());
                }
            }
        }

    }
}
            /*if(environnement.getTrunks().getObjectEquipe().getId() == 1) {
                if (/*environnement.getTerrain().codeTuilePixel((int) mouseEvent.getX(), (int) mouseEvent.getY()) == 2 || environnement.getTerrain().codeTuilePixel((int) mouseEvent.getX(), (int) mouseEvent.getY()) == 3) {
                    System.out.println("entre dans la fonction");
                    if(environnement.getTerrain().creuserBlocPioche(environnement.getTrunks().getX(), environnement.getTrunks().getY(), mouseEvent.getX(), mouseEvent.getY()) != -1) {
                        //teste deux fois car il y a un || (une fois pour 2 et une fois pour 3)
                        this.terrainVue.changerTuileCiel((int) mouseEvent.getX(), (int) mouseEvent.getY());
                    }


                    //this.environnement.getTrunks().getInventaire().addObject();
                }
            }
            */
            /*if(environnement.getTrunks().getObjectEquipe().getId() == 1)*/
