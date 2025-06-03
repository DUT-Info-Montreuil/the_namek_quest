package universite_paris8.iut.fan.the_namek_quest.modele.inventaire;

/**
 * Classe Inventaire
 * ------------------
 * Gère la liste d'objets (objets génériques et ressources) détenus par le joueur.
 * Utilise une ObservableList pour faciliter l'observation et la mise à jour en interface graphique.
 **/

 import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.materiaux.Materieau;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.materiaux.Terre;

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


    public void ajoutRessource(int typeRessource){
        System.out.println("entre dans ajoutRessource : \n type ressource"+typeRessource);
        switch (typeRessource) {
            case 2 :
                verifierRessource(2,new Terre());
                break;
            case 3 :
                verifierRessource(2,new Terre());
                break;

        }

    }

    public int positionRessource(int typeRessource){
        for (int i = 3; i < this.inventaire.size(); i++) {
            if (this.inventaire.get(i).getId()==typeRessource) {
                return i;
            }
        }
        return -1;
    }

    public void verifierRessource(int typeRessource,Object ressource){

        if(this.ressourceDansInventaire(typeRessource)){
            System.out.println("entre dans verif");
            Materieau materieau = (Materieau)this.inventaire.get(positionRessource(typeRessource));
            materieau.incrementerRessource();
            System.out.println("materieau quantité :"+materieau.getQuantite());

            if(this.inventaire.get(positionRessource(typeRessource)) instanceof Materieau){
                System.out.println("entre dans verif materieau");

            }
        }else {

            this.inventaire.add(ressource);
        }

    }

    public boolean ressourceDansInventaire(int typeRessource){
        for (int i = 3; i < this.inventaire.size(); i++) {
            if (this.inventaire.get(i).getId()==typeRessource) {
                return true;
            }
        }
        return false;
    }


}
