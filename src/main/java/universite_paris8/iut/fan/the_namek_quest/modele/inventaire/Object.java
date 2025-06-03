package universite_paris8.iut.fan.the_namek_quest.modele.inventaire;

/**
 * Classe Object
 * ------------------
 * Représente un objet de l'inventaire dans le jeu "The Namek Quest".
 * Cette classe peut être utilisée pour modéliser des armes, objets de quête ou équipements.
 **/


// TODO attention : Object est un mot réservé de Java
public class Object {
    private int id;
    private String name;

    public Object(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }

    public String toString(){
        return "id :"+this.id+" arme :"+this.name;
    }
}

