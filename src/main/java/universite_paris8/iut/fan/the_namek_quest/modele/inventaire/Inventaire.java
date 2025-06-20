package universite_paris8.iut.fan.the_namek_quest.modele.inventaire;

/**
 * Classe Inventaire
 * ------------------
 * Gère la liste d'objets (objets génériques et ressources) détenus par le joueur.
 * Utilise une ObservableList pour faciliter l'observation et la mise à jour en interface graphique.
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.materiaux.*;

public class Inventaire {

    private ObservableList<Element> inventaire;

    public Inventaire() {
        this.inventaire = FXCollections.observableArrayList();
    }

    // Accesseur à la liste observable des objets
    public ObservableList<Element> getListObjects() {
        return inventaire;
    }

    // Mutateur pour remplacer l'inventaire complet (usage à manipuler avec précaution)
    public void setInventaire(ObservableList<Element> inventaire) {
        this.inventaire = inventaire;
    }

    // Ajoute un objet à l'inventaire
    public void addObject(Element o) {
        this.inventaire.add(o);
    }

    // Retourne l'indice d'un objet donné dans l'inventaire, -1 si absent
    public int getIndexObject(Element object) {
        return this.inventaire.indexOf(object);
    }

    /**
     * Ajoute une ressource à l'inventaire en fonction de son type.
     * Certains types ajoutent plusieurs ressources.
     */
    public void ajoutRessource(int typeRessource) {
        switch (typeRessource) {
            case 2:
            case 3:
                ajouterRessource(2, new Terre());
                break;
            case 4:
                ajouterRessource(4, new Energie());
                break;
            case 6:
                ajouterRessource(6, new Haricot());
                break;
            case 8:
                ajouterRessource(8, new BouleCristal());
                break;
            case 9:
                ajouterRessource(9, new RocheDeNamek());
                break;
            case 10:
            case 11:
                ajouterRessource(10, new Arbres());
                ajouterRessource(10, new Arbres());
                break;
            default:
                // Type ressource non reconnu : ne rien faire
                break;
        }
    }

    /**
     * Cherche la position d'une ressource par son type dans l'inventaire.
     * Commence à l'indice 3 réservé aux ressources.
     * @return indice de la ressource ou -1 si non trouvée
     */
    public int positionRessource(int typeRessource) {
        for (int i = 0; i < this.inventaire.size(); i++) {
            Element obj = this.inventaire.get(i);
            if (obj instanceof Materieau) {
                Materieau mat = (Materieau) obj;
                if (mat.getId() == typeRessource) {
                    return i;
                }
            }
        }
        return -1;
    }


    /**
     * Vérifie la présence d'une ressource dans l'inventaire.
     * Si présente, incrémente sa quantité.
     * Sinon, ajoute la nouvelle ressource.
     */
    public void ajouterRessource(int typeRessource, Element ressource) {
        int pos = positionRessource(typeRessource);
        if (pos != -1) {
            Materieau materieau = (Materieau) this.inventaire.get(pos);
            materieau.incrementerRessource();
        } else {
            this.inventaire.add(ressource);
        }
    }

    public int  verifierPresenceRessource(int typeRessouce){
        int pos = positionRessource(typeRessouce);
        return pos;

    }
}
