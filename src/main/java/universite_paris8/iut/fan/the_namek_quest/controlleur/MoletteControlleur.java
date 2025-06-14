package universite_paris8.iut.fan.the_namek_quest.controlleur;

/**
 * Classe MoletteController
 * -------------------------
 * Gère les événements de la molette de la souris (scroll) pour changer
 * l'équipement de Trunks et mettre à jour la vue de l'inventaire.
 * Lors d'un scroll vers le haut, l'équipement est changé dans un sens,
 * lors d'un scroll vers le bas, il est changé dans l'autre sens.
 * Si l'inventaire est ouvert, il est rafraîchi pour refléter le changement.
 */

import javafx.event.EventHandler;
import javafx.scene.input.ScrollEvent;
import universite_paris8.iut.fan.the_namek_quest.modele.personnage.Trunks;
import universite_paris8.iut.fan.the_namek_quest.vue.InventaireVue;

public class MoletteControlleur implements EventHandler<ScrollEvent> {

    private final Trunks trunks;
    private final InventaireVue inventaireVue;

    public MoletteControlleur(Trunks trunks, InventaireVue inventaireVue) {
        this.trunks = trunks;
        this.inventaireVue = inventaireVue;
    }

    @Override
    public void handle(ScrollEvent event) {
        double delta = event.getDeltaY();

        if (delta > 0) {
            changerEquipement(1); // Scroll vers le haut
        } else if (delta < 0) {
            changerEquipement(-1); // Scroll vers le bas
        }
    }

    /**
     * Change l’équipement de Trunks et met à jour la vue de l’inventaire si elle est ouverte.
     * @param direction +1 pour suivant, -1 pour précédent
     */
    private void changerEquipement(int direction) {
        trunks.changerEquipement(direction);

        if (inventaireVue.estOuvert()) {
            rafraichirInventaire();
        }
    }

    /**
     * Ferme puis rouvre l’inventaire pour forcer l’affichage mis à jour.
     */
    private void rafraichirInventaire() {
        inventaireVue.fermeInventaire();
        inventaireVue.ouvrirInventaire();
    }
}
