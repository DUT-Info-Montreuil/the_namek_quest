package universite_paris8.iut.fan.the_namek_quest.controlleur;

import javafx.event.EventHandler;
import javafx.scene.input.ScrollEvent;
import universite_paris8.iut.fan.the_namek_quest.modele.Trunks;
import universite_paris8.iut.fan.the_namek_quest.vue.InventaireVue;

/**
 * Classe MoletteController
 * -------------------------
 * Gère les événements de la molette de la souris (scroll) pour changer
 * l'équipement de Trunks et mettre à jour la vue de l'inventaire.
 * Lors d'un scroll vers le haut, l'équipement est changé dans un sens,
 * lors d'un scroll vers le bas, il est changé dans l'autre sens.
 * Si l'inventaire est ouvert, il est rafraîchi pour refléter le changement.
 */

public class MoletteControlleur implements EventHandler<ScrollEvent> {
    private Trunks trunks;
    private InventaireVue inventaireVue;


    public MoletteControlleur(Trunks trunks, InventaireVue inventaireVue){
        this.trunks = trunks;
        this.inventaireVue = inventaireVue;
    }


    @Override
    public void handle(ScrollEvent event) {

        if(event.getDeltaY()>0){
            System.out.println("Molette vers le haut");
            trunks.changerEquipement(1);


            if(inventaireVue.estOuvert()) {
                inventaireVue.fermeInventaire();
                inventaireVue.ouvrirInventaire();
            }

            System.out.println(trunks.getObjectEquipe().toString());
        } else if (event.getDeltaY()<0) {
            System.out.println("Molette vers le bas");
            trunks.changerEquipement(-1);

            if(inventaireVue.estOuvert()) {
                inventaireVue.fermeInventaire();
                inventaireVue.ouvrirInventaire();
            }
            System.out.println(trunks.getObjectEquipe().toString());
        }

    }
}

