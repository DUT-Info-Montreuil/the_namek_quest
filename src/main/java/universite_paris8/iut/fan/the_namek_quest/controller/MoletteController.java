package universite_paris8.iut.fan.the_namek_quest.controller;

import javafx.event.EventHandler;
import javafx.scene.input.ScrollEvent;
import universite_paris8.iut.fan.the_namek_quest.model.Inventaire.Inventaire;
import universite_paris8.iut.fan.the_namek_quest.model.Inventaire.outils.Hache;
import universite_paris8.iut.fan.the_namek_quest.model.Inventaire.outils.Pioche;
import universite_paris8.iut.fan.the_namek_quest.model.Trunks;
import universite_paris8.iut.fan.the_namek_quest.view.InventaireVue;


public class MoletteController implements EventHandler<ScrollEvent> {
    private Trunks trunks;
    private InventaireVue inventaireVue;


    public MoletteController(Trunks trunks,InventaireVue inventaireVue){
        this.trunks = trunks;
        this.inventaireVue = inventaireVue;
    }


    @Override
    public void handle(ScrollEvent event) {

        if(event.getDeltaY()>0){
            System.out.println("⬆️ Molette vers le haut");
            trunks.changerEquipement(1);

            if(inventaireVue.estOuvert()) {
                inventaireVue.fermeInventaire();
                inventaireVue.ouvrirInventaire();
            }

            System.out.println(trunks.getObjectEquipe().toString());
        } else if (event.getDeltaY()<0) {
            System.out.println(" Molette vers le bas");
            trunks.changerEquipement(-1);

            if(inventaireVue.estOuvert()) {
                inventaireVue.fermeInventaire();
                inventaireVue.ouvrirInventaire();
            }

            trunks.getInventaire().addObject(new Hache());

            System.out.println(trunks.getObjectEquipe().toString());

        }

    }
}

