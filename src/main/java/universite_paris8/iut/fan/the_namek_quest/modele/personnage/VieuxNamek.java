package universite_paris8.iut.fan.the_namek_quest.modele.personnage;

import universite_paris8.iut.fan.the_namek_quest.modele.Environnement;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.Inventaire;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.materiaux.BouleCristal;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.materiaux.Energie;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.materiaux.Materieau;

/**
 * Classe PersonnageNonJoueur
 * ---------------------------
 *
 **/

public class VieuxNamek extends PersonnageNonJoueur {
    private Trunks trunks;
    private Inventaire inventaire;

    public VieuxNamek(int x, int y, Environnement env, Trunks trunks) {
        super(x, y, env);
        this.trunks = trunks;
        this.inventaire = new Inventaire();
        //disparaitre(); // cache le personnage au départ
    }

   /* public boolean peutApparaitre() {
        // Vérifie si on a au moins 8 ressources
        if (inventaire.ressourceDansInventaire(8)) {
            int index = inventaire.getIndexObject(new BouleCristal());

            // Si une boule de cristal est présente
            if (index != -1) {
                Objet objet = inventaire.getListObjects().get(index);

                if (objet instanceof Materieau) {
                    Materieau boule = (Materieau) objet;
                    return boule.getQuantite() >= 1;
                }
            }
        }
        return false;
    }

    public void apparitionOuDisparition(){
        if (peutApparaitre() && trunksAProximite()) {
            // Positionne le Vieux Namek à gauche de Trunks
            setX(trunks.getX() - 64);
            setY(trunks.getY());
        } else {
            disparaitre();
        }
    }

    public void disparaitre(){
        // Déplace le personnage hors de l’écran
        setX(-1000);
        setY(-1000);
    }
    */
}
