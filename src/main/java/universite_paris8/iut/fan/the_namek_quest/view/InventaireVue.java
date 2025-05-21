package universite_paris8.iut.fan.the_namek_quest.view;

import javafx.scene.layout.Pane;
import universite_paris8.iut.fan.the_namek_quest.model.Inventaire.Inventaire;

public class InventaireVue {

    private Pane pane;
    private Inventaire inventaire;
    private InventaireVue inventaireVue;

    private boolean ouvert;

    public InventaireVue(Inventaire inventaire,Pane pane,InventaireVue inventaireVue ) {
        this.pane = pane;
        this.inventaire = inventaire;
        this.inventaireVue = inventaireVue;
        this.ouvert = false;
    }

    private void afficherInventaire(){}
}
