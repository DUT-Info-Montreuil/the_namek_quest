package universite_paris8.iut.fan.the_namek_quest.controller;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import universite_paris8.iut.fan.the_namek_quest.model.Inventaire.Inventaire;
import universite_paris8.iut.fan.the_namek_quest.view.InventaireVue;

public class InventaireListener implements ListChangeListener<Object>{

    private Inventaire inventaire;
    private InventaireVue inventaireVue;
    private Pane paneInventaire;

    public InventaireListener(InventaireVue inventaireVue,Inventaire inventaire ,Pane paneInventaire) {
        super();
        this.inventaire = inventaire;
        this.inventaireVue = inventaireVue;
        this.paneInventaire = paneInventaire;
    }

    @Override
    public void onChanged(Change<? extends Object> c) {
        while (c.next() && inventaireVue.estOuvert()) {
            if (c.wasAdded() || c.wasRemoved() || c.wasReplaced()) {
                for (Object o : c.getAddedSubList()) {
                    inventaireVue.afficherContenuInventaire();
                }

                if(inventaireVue.estOuvert()) {
                    inventaireVue.fermeInventaire();
                    inventaireVue.ouvrirInventaire();
                }
            }
        }
    }

}
