package universite_paris8.iut.fan.the_namek_quest.controlleur;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import universite_paris8.iut.fan.the_namek_quest.modele.PersonnageEnnemis;
import universite_paris8.iut.fan.the_namek_quest.vue.PersonnageEnnemisVue;

public class ObservableEnnemis implements ListChangeListener<PersonnageEnnemis> {

    private Pane pane;

    public ObservableEnnemis(Pane pane) {
        this.pane = pane;
    }

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
