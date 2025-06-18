package universite_paris8.iut.fan.the_namek_quest.controlleur;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import universite_paris8.iut.fan.the_namek_quest.modele.Environnement;
import universite_paris8.iut.fan.the_namek_quest.modele.personnage.PersonnageEnnemis;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.arme.Arme;
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


    private final Environnement environnement;
    private final TerrainVue terrainVue;
    private final Controlleur controlleur;
    private boolean demarrerJeu = false;


    public Souris(Environnement environnement, TerrainVue terrainVue,Controlleur controlleur) {
        this.environnement = environnement;
        this.terrainVue = terrainVue;
        this.controlleur = controlleur;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if (mouseEvent.getEventType() == MouseEvent.MOUSE_CLICKED) {
            System.out.println("clic");

            if (mouseEvent.getX() > 330 && mouseEvent.getX() < 530
                    && mouseEvent.getY() > 420 && mouseEvent.getY() < 480 && !demarrerJeu) {
                this.demarrerJeu = true;
                System.out.println("clic start");
                controlleur.demarrerJeu();
            }

            double x = mouseEvent.getX();
            double y = mouseEvent.getY();


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
                else if (idObjet == 99 && (idTuile == 6 || idTuile == 8|| idTuile == 4)) {
                    environnement.getTrunks().getInventaire().ajoutRessource(idTuile);
                    environnement.getTerrain().casserBloc(x, y);
                    terrainVue.changerTuileCiel((int) x, (int) y);
                }

                //si trunks a une epee
                else if (environnement.getTrunks().getObjectEquipe().getId() == 0) {
                    Arme epee = (Arme) environnement.getTrunks().getObjectEquipe();
                    //si un ennemis est touché
                    System.out.println("entre dans la range epee");
                    PersonnageEnnemis persotouché = environnement.trouverEnnemi((int) mouseEvent.getX(), (int) mouseEvent.getY());
                    //System.out.println("persotouché = " + persotouché.toString());
                    if (persotouché != null) {
                        System.out.println("ennemi touché");
                        persotouché.decrementerPv(epee.getDegat());
                    }


                }
                // Si Trunks tient un matériau
                else if (environnement.getTrunks().getObjectEquipe() instanceof Materieau) {
                    if (environnement.getTerrain().codeTuilePixel((int) mouseEvent.getX(), (int) mouseEvent.getY()) == 1) {
                        Materieau materieau = (Materieau) environnement.getTrunks().getObjectEquipe();
                        if (materieau.getQuantite() > 0) {
                            materieau.decrementerRessource();
                            environnement.getTerrain().poserBloc(mouseEvent.getX(), mouseEvent.getY(), materieau.getId());
                            this.terrainVue.changerTuile((int) mouseEvent.getX(), (int) mouseEvent.getY(),materieau.getId());
                        }

                    }
                }
            }
        }
    }
}
