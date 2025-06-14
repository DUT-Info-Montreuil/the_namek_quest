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

    private final Trunks trunks;
    private final Inventaire inventaire;

    public GrandChef(int x, int y, Environnement env, Trunks trunks) {
        super(x, y, env);
        this.trunks = trunks;
        this.inventaire = new Inventaire();
    }

    /**
     * Tente d'améliorer l'épée de Trunks si toutes les conditions sont remplies.
     */
    public void ameliorerEpee() {
        System.out.println("Demande d'amélioration de l'épée par le Grand Chef");

        // Vérifie que Trunks est juste à gauche du Grand Chef
        if (trunks.getX() == this.getX() - 1 && trunks.getY() == this.getY()) {

            // Vérifie que les ressources nécessaires sont dans l'inventaire
            boolean aRoche = inventaire.ressourceDansInventaire(7);
            boolean aEnergie = inventaire.ressourceDansInventaire(4);

            if (aRoche && aEnergie) {
                // Récupération des ressources
                Materieau rocheNamek = (Materieau) inventaire.getListObjects().get(inventaire.getIndexObject(new RocheDeNamek()));
                Materieau energie = (Materieau) inventaire.getListObjects().get(inventaire.getIndexObject(new Energie()));

                // Vérifie les quantités
                if (rocheNamek != null && energie != null && rocheNamek.getQuantite() >= 3 && energie.getQuantite() >= 2) {

                    Element objetEquipe = trunks.getObjectEquipe();

                    // Vérifie que Trunks a équipé une épée (id = 0)
                    if (objetEquipe != null && objetEquipe.getId() == 0) {
                        Arme epee = (Arme) objetEquipe;
                        epee.incrementerDegat(10);
                        System.out.println("Épée améliorée de +10 dégâts !");
                    }
                }
            }
        }
    }
}
