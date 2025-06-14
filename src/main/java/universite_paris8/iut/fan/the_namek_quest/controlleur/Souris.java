package universite_paris8.iut.fan.the_namek_quest.controlleur;


/**
 * Classe Souris
 * --------------
 * Gère les événements de souris (clics) dans le jeu.
 * Cette classe détecte les clics de souris, vérifie la zone cliquée
 * et agit en conséquence, notamment pour commencer le jeu,
 * creuser des blocs ou poser des blocs dans l'environnement.
 **/


import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import universite_paris8.iut.fan.the_namek_quest.modele.Environnement;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.materiaux.Materieau;
import universite_paris8.iut.fan.the_namek_quest.vue.TerrainVue;

public class Souris implements EventHandler<MouseEvent> {

    private final Controlleur controlleur;
    private Environnement environnement;
    private TerrainVue terrainVue;

    public Souris(Controlleur controlleur, Environnement environnement, TerrainVue terrainVue) {
        this.controlleur = controlleur;
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
                controlleur.demarrerJeu();
            }

            if (environnement.getTerrain().rangeCreuser(environnement.getTrunks().getX(), environnement.getTrunks().getY(), mouseEvent.getX(), mouseEvent.getY())) {
                System.out.println("entre dans la range");
                //si trunks a une pioche
                if (environnement.getTrunks().getObjectEquipe().getId() == 1
                        && (environnement.getTerrain().codeTuilePixel((int) mouseEvent.getX(),(int) mouseEvent.getY())==3
                        ||environnement.getTerrain().codeTuilePixel((int) mouseEvent.getX(),(int) mouseEvent.getY())==2 ||environnement.getTerrain().codeTuilePixel((int) mouseEvent.getX(),(int) mouseEvent.getY())==6 ||environnement.getTerrain().codeTuilePixel((int) mouseEvent.getX(),(int) mouseEvent.getY())==9)) {
                    environnement.getTrunks().getInventaire().ajoutRessource(environnement.getTerrain().codeTuilePixel((int) mouseEvent.getX(),(int) mouseEvent.getY()));
                    environnement.getTerrain().casserBloc(mouseEvent.getX(), mouseEvent.getY());
                    this.terrainVue.changerTuileCiel((int) mouseEvent.getX(), (int) mouseEvent.getY());
                }
                //si trunks a une hache
                else if (environnement.getTrunks().getObjectEquipe().getId()==2  && environnement.getTerrain().codeTuilePixel((int) mouseEvent.getX(),(int) mouseEvent.getY())==10) {
                    environnement.getTrunks().getInventaire().ajoutRessource(environnement.getTerrain().codeTuilePixel((int) mouseEvent.getX(),(int) mouseEvent.getY()));
                    environnement.getTerrain().casserBloc(mouseEvent.getX(), mouseEvent.getY());
                    this.terrainVue.changerTuileCiel((int) mouseEvent.getX(), (int) mouseEvent.getY());

                //si trunks a les mains vides
                } else if (environnement.getTrunks().getObjectEquipe().getId()==99
                        &&( (environnement.getTerrain().codeTuilePixel((int) mouseEvent.getX(),(int) mouseEvent.getY())==8)
                        || environnement.getTerrain().codeTuilePixel((int) mouseEvent.getX(),(int) mouseEvent.getY())==6)) {
                    environnement.getTrunks().getInventaire().ajoutRessource(environnement.getTerrain().codeTuilePixel((int) mouseEvent.getX(),(int) mouseEvent.getY()));
                    environnement.getTerrain().casserBloc(mouseEvent.getX(), mouseEvent.getY());
                    this.terrainVue.changerTuileCiel((int) mouseEvent.getX(), (int) mouseEvent.getY());

                //si trunks a du materiaux en mains
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

