package universite_paris8.iut.fan.the_namek_quest.modele.personnage;

import universite_paris8.iut.fan.the_namek_quest.modele.Environnement;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.Inventaire;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.Element;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.arme.Arme;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.materiaux.Energie;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.materiaux.Materieau;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.materiaux.RocheDeNamek;

/**
 * Classe GrandChef
 * -----------------
 * Représente le Grand Chef, un PNJ (personnage non joueur) capable d'améliorer l'épée de Trunks.
 *
 * Comportements :
 *   - Peut améliorer l'épée de Trunks si certaines conditions sont remplies :
 *   - Trunks est à proximité immédiate (gauche du Grand Chef).
 *   - L'inventaire contient au moins 3 roches de Namek (id=7) et 2 énergies (id=4).
 *   - Trunks a équipé une épée (id=0).
 *   - Dans ce cas, les dégâts de l'épée sont augmentés de +10.
 */

public class GrandChef extends PersonnageNonJoueur {

    private Trunks trunks;
    private  Inventaire inventaire;

    public GrandChef(int x, int y, Environnement env, Trunks trunks) {
        super(x, y, env);
        this.trunks = trunks;
        this.inventaire = trunks.getInventaire();
    }

    /**
     * Tente d'améliorer l'épée de Trunks si toutes les conditions sont remplies.
     */

    public boolean ameliorerEpee() {
        boolean ameliorer = false;
        // Vérifie que Trunks est juste à gauche du Grand Chef
        if (!trunksAProximite()) {
            return false;
        }
        // Vérifie la disponibilité

        if (inventaire.positionRessource(9)  == -1) {
            return false;
        }
        if (inventaire.positionRessource(4) == -1) {
            return false;
        }

        Element objetEquipe = trunks.getObjectEquipe();
        if (objetEquipe == null || objetEquipe.getId() != 0) {
            return false;
        }


        Materieau rocheNamek = (Materieau) inventaire.getListObjects().get(inventaire.positionRessource(9));
        Materieau energie = (Materieau) inventaire.getListObjects().get(inventaire.positionRessource(4));

        // Vérification des quantités
        if( rocheNamek.getQuantite() < 3 || energie.getQuantite() < 2) {
            return false;
        }

        // Amélioration de l'épée
        if(!ameliorer) {
            Arme epee = (Arme) objetEquipe;
            epee.incrementerDegat(10);
            ameliorer = true;
        }
        // Mise à jour des quantités dans l'inventaire du joueur
        if(ameliorer){
        for(int i = 0; i < 3; i++) {
            rocheNamek.decrementerRessource();
        }
        for(int i = 0; i < 2; i++) {
            energie.decrementerRessource();
        }
        ameliorer = false;
        return true;
    }
    return false;}
}

