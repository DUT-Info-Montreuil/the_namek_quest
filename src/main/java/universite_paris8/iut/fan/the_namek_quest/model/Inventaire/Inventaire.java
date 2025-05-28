package universite_paris8.iut.fan.the_namek_quest.model.Inventaire;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.fan.the_namek_quest.model.Inventaire.ressource.Materieau;

public class Inventaire {
    private ObservableList<Object> inventaire;

    public Inventaire() {

        this.inventaire = FXCollections.observableArrayList();
    }

    public ObservableList<Object> getListObjects() {
        return inventaire;
    }

    public void setInventaire(ObservableList<Object> inventaire) {

        this.inventaire = inventaire;
    }
    public void addObject(Object o) {
        this.inventaire.add(o);
    }


    public void removeObject(Object object) {
        this.inventaire.remove(object);
    }
    public void removeObjectsIndex(int index) {
        this.inventaire.remove(index);
    }

    public int getIndexObject(Object object) {
        for (int i = 0; i < this.inventaire.size(); i++) {
            if (this.inventaire.get(i).equals(object)) {
                return i;
            }
        }
        return -1;
    }


    public void verifierRessource(int typeRessource){
        switch (typeRessource) {
            case 2 :

                case 3 :



        }

    }

    public boolean ressourceDetenue(int typeRessource){
        for (int i = 0; i < this.inventaire.size(); i++) {
            if (this.inventaire.get(i).getId()==typeRessource) {
                return true;
            }
        }
        return false;
    }

    public void ajoutRessource(int typeRessource){
        if (this.ressourceDetenue(typeRessource)) {
            if(this.inventaire.get(ressourceDansInventaire(typeRessource)) instanceof Materieau){
                Materieau materieau = (Materieau) this.inventaire.get(ressourceDansInventaire(typeRessource));
                materieau.incrementerRessource();
            }
        }else {
            //this.inventaire.
        }
    }

    public int ressourceDansInventaire(int typeRessource){
        for (int i = 0; i < this.inventaire.size(); i++) {
            if (this.inventaire.get(i).getId()==typeRessource) {
                return i;
            }
        }
        return -1;
    }


}

