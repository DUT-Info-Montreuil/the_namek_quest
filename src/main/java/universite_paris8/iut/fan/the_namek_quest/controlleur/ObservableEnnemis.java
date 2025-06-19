package universite_paris8.iut.fan.the_namek_quest.controlleur;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import universite_paris8.iut.fan.the_namek_quest.modele.personnage.PersonnageEnnemis;
import universite_paris8.iut.fan.the_namek_quest.vue.PersonnageEnnemisVue;

/**
 * Classe ObservableEnnemis
 * ------------------------
 * Permet d'observer la liste des ennemis dans l'environnement.
 * Ajoute ou retire dynamiquement leur représentation graphique (vue) sur le pane du jeu
 * lors de l'ajout ou la suppression d'un ennemi dans la liste observable.
 */

public class ObservableEnnemis implements ListChangeListener<PersonnageEnnemis> {

    private Pane pane;

    public ObservableEnnemis(Pane pane) {
        this.pane = pane;
    }

    /**
     * Méthode appelée lors d'un changement dans la liste observable d'ennemis.
     * Ajoute la vue pour chaque nouvel ennemi, retire la vue pour chaque ennemi supprimé.
     * @param c Changement détecté dans la liste
     */
    @Override
    public void onChanged(Change<? extends PersonnageEnnemis> c) {
            while (c.next()) {
                for(PersonnageEnnemis o : c.getAddedSubList()){
                    PersonnageEnnemisVue personnageEnnemisVue = new PersonnageEnnemisVue(pane,o);
                }
                for(PersonnageEnnemis o : c.getRemoved()){
                    System.out.println("Ennemis supprimé : " + o.getId());
                    pane.getChildren().remove(pane.lookup("#"+o.getId()));
                }
            }

    }
}
