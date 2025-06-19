package universite_paris8.iut.fan.the_namek_quest.modele.inventaire.outils;

import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.Element;
/**
 * Classe abstraite représentant un outil dans l'inventaire.
 * Un outil possède un identifiant et un nom, hérités de la classe Element.
 */
public abstract class Outils extends Element {
    public Outils(int id, String name) {
        super(id, name);
    }
}
