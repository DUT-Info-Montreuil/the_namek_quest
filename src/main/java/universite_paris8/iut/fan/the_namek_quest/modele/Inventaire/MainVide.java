package universite_paris8.iut.fan.the_namek_quest.modele.Inventaire;

/**
 * Classe MainVide
 * --------------------
 * Représente un objet particulier de l'inventaire : une main vide, utilisée
 * lorsqu'aucun autre objet n'est équipé par le joueur.
 * Hérite de la classe Object, avec un identifiant fixe (99) et un nom par défaut ("main vide").
 **/

public class MainVide extends Object {

    public MainVide() {
        super(99, "main vide");
    }
}