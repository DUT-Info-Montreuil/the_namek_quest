package universite_paris8.iut.fan.the_namek_quest.modele.inventaire;

/**
 * Classe Element
 * ------------------
 * Représente un objet de l'inventaire dans le jeu "The Namek Quest".
 * Cette classe peut être utilisée pour modéliser des armes, objets de quête ou équipements.
 **/

public class Element {
    private int id;
    private String name;

    public Element(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "id: " + this.id + " nom: " + this.name;
    }
}