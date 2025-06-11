package universite_paris8.iut.fan.the_namek_quest.controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import universite_paris8.iut.fan.the_namek_quest.model.Environnement;
import universite_paris8.iut.fan.the_namek_quest.model.Inventaire.ressource.Materieau;
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
                //si trunks a une pioche
                if (environnement.getTrunks().getObjectEquipe().getId() == 1
                        && (environnement.getTerrain().codeTuilePixel((int) mouseEvent.getX(),(int) mouseEvent.getY())==3
                ||environnement.getTerrain().codeTuilePixel((int) mouseEvent.getX(),(int) mouseEvent.getY())==2)) {

                    environnement.getTrunks().getInventaire().ajoutRessource(environnement.getTerrain().codeTuilePixel((int) mouseEvent.getX(),(int) mouseEvent.getY()));
                    environnement.getTerrain().casserBloc(mouseEvent.getX(), mouseEvent.getY());
                    this.terrainVue.changerTuileCiel((int) mouseEvent.getX(), (int) mouseEvent.getY());
                } else if (environnement.getTrunks().getObjectEquipe().getId()==2  && environnement.getTerrain().codeTuilePixel((int) mouseEvent.getX(),(int) mouseEvent.getY())==10) {
                    environnement.getTrunks().getInventaire().ajoutRessource(environnement.getTerrain().codeTuilePixel((int) mouseEvent.getX(),(int) mouseEvent.getY()));
                    environnement.getTerrain().casserBloc(mouseEvent.getX(), mouseEvent.getY());
                    this.terrainVue.changerTuileCiel((int) mouseEvent.getX(), (int) mouseEvent.getY());
                } else if (environnement.getTrunks().getObjectEquipe() instanceof Materieau) {
                    if(environnement.getTerrain().codeTuilePixel((int) mouseEvent.getX(),(int) mouseEvent.getY())==1){
                        Materieau materieau = (Materieau) environnement.getTrunks().getObjectEquipe();
                        if(materieau.getQuantite()>0) {
                            materieau.decrementerRessource();
                            environnement.getTerrain().poserBloc(mouseEvent.getX(), mouseEvent.getY(), materieau.getId());
                            this.terrainVue.changerTuileSol((int) mouseEvent.getX(), (int) mouseEvent.getY());
                        }
                    }
                }
            }
        }
    }
}

