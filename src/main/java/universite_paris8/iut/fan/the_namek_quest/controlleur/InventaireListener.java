package universite_paris8.iut.fan.the_namek_quest.controlleur;

/**
 * Classe InventaireListener
 * --------------------------
 * Écoute les changements dans la liste d'objets de l'inventaire.
 * Lorsqu'un ajout, une suppression ou un remplacement survient,
 * elle met à jour la vue de l'inventaire en rafraîchissant l'affichage.
 **/


import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.Inventaire;
import universite_paris8.iut.fan.the_namek_quest.vue.InventaireVue;

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
