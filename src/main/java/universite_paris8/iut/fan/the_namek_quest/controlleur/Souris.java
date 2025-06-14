package universite_paris8.iut.fan.the_namek_quest.controlleur;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import universite_paris8.iut.fan.the_namek_quest.modele.Environnement;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.materiaux.Materieau;
import universite_paris8.iut.fan.the_namek_quest.vue.TerrainVue;

/**
 * Classe Souris
 * --------------
 * Gère les clics de souris dans le jeu :
 * - Lance le jeu via le bouton "start"
 * - Permet de creuser ou poser des blocs selon l'objet équipé
 */
public class Souris implements EventHandler<MouseEvent> {

    private final Controlleur controlleur;
    private final Environnement environnement;
    private final TerrainVue terrainVue;

    public Souris(Controlleur controlleur, Environnement environnement, TerrainVue terrainVue) {
        this.controlleur = controlleur;
        this.environnement = environnement;
        this.terrainVue = terrainVue;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if (mouseEvent.getEventType() == MouseEvent.MOUSE_CLICKED) {
            System.out.println("clic");

            double x = mouseEvent.getX();
            double y = mouseEvent.getY();

           /* // Bouton start
            if (x > 330 && x < 530 && y > 420 && y < 480) {
                System.out.println("clic start");
                controlleur.demarrerJeu();
                return;
            }*/

            // Action dans le jeu (creuser ou poser)
            if (environnement.getTerrain().rangeCreuser(environnement.getTrunks().getX(), environnement.getTrunks().getY(), x, y)) {
                System.out.println("entre dans la range");

                int idTuile = environnement.getTerrain().codeTuilePixel((int) x, (int) y);
                int idObjet = environnement.getTrunks().getObjectEquipe().getId();

                // Si Trunks a une pioche (id 1)
                if (idObjet == 1 && (idTuile == 2 || idTuile == 3 || idTuile == 6 || idTuile == 9)) {
                    environnement.getTrunks().getInventaire().ajoutRessource(idTuile);
                    environnement.getTerrain().casserBloc(x, y);
                    terrainVue.changerTuileCiel((int) x, (int) y);
                }

                // Si Trunks a une hache (id 2)
                else if (idObjet == 2 && (idTuile == 10 || idTuile == 11)) {
                    environnement.getTrunks().getInventaire().ajoutRessource(idTuile);
                    environnement.getTerrain().casserBloc(x, y);
                    terrainVue.changerTuileCiel((int) x, (int) y);
                }

                // Si Trunks a les mains vides (id 99)
                else if (idObjet == 99 && (idTuile == 6 || idTuile == 8)) {
                    environnement.getTrunks().getInventaire().ajoutRessource(idTuile);
                    environnement.getTerrain().casserBloc(x, y);
                    terrainVue.changerTuileCiel((int) x, (int) y);
                }

                // Si Trunks tient un matériau
                else if (environnement.getTrunks().getObjectEquipe() instanceof Materieau) {
                    Materieau materieau = (Materieau) environnement.getTrunks().getObjectEquipe();

                    if (idTuile == 1 && materieau.getQuantite() > 0) {
                        materieau.decrementerRessource();
                        environnement.getTerrain().poserBloc(x, y, materieau.getId());
                        terrainVue.changerTuile((int) x, (int) y, materieau.getId());
                    }
                }
            }
        }
    }
}
