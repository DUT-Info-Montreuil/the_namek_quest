package universite_paris8.iut.fan.the_namek_quest.controller;

import javafx.event.EventHandler;
import javafx.scene.input.ScrollEvent;
import universite_paris8.iut.fan.the_namek_quest.model.Inventaire.Inventaire;
import universite_paris8.iut.fan.the_namek_quest.model.Inventaire.outils.Hache;
import universite_paris8.iut.fan.the_namek_quest.model.Inventaire.outils.Pioche;
import universite_paris8.iut.fan.the_namek_quest.model.Trunks;



public class MoletteController implements EventHandler<ScrollEvent> {
    private Trunks trunks;

    public MoletteController(Trunks trunks){
        this.trunks = trunks;
    }


    @Override
    public void handle(ScrollEvent event) {

        if(event.getDeltaY()>0){
            System.out.println("⬆️ Molette vers le haut");
            trunks.changerEquipement(1);
            //trunks.setObjectEquipe(new Pioche());
            //trunks.setObjectEquipe(trunks.getInventaire().getListObjects().get(2));
            //trunks.getObjectEquipe().toString();
        } else if (event.getDeltaY()<0) {
            System.out.println(" Molette vers le bas");
            trunks.changerEquipement(-1);
           // trunks.setObjectEquipe(trunks.getInventaire().getListObjects().get(1));
            //trunks.getObjectEquipe().toString();

        }

    }
}

