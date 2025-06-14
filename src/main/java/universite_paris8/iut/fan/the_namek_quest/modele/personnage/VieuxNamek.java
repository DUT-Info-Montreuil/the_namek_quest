package universite_paris8.iut.fan.the_namek_quest.modele.personnage;

import universite_paris8.iut.fan.the_namek_quest.modele.Environnement;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.Inventaire;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.Object;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.arme.Arme;
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
    private int boulesDejaFellicitees = 0;
    private boolean visible = false;

    public VieuxNamek(int x, int y, Environnement env, Trunks trunks) {
        super(x, y, env);
        this.trunks = trunks;
        disparaitre();
    }

    public int compterBoulesDeCristal() {
        int total = 0;
        for (Object obj : trunks.getInventaire().getListObjects()) {
            if (obj instanceof BouleCristal) {
                total += ((BouleCristal) obj).getQuantite();
            }
        }
        return total;
    }

    public void apparitionOuDisparition() {
        int nbBoules = compterBoulesDeCristal();

        // Si Trunks a une nouvelle boule (max 3 fois)
        if (nbBoules > boulesDejaFellicitees && boulesDejaFellicitees < 3) {
            // On fait apparaître le Vieux Namek
            setX(trunks.getX() - 64);
            setY(trunks.getY());
            visible = true;
            boulesDejaFellicitees = nbBoules;
        }

        // Si le Vieux Namek est visible, on vérifie la distance
        if (visible) {
            double distance = Math.hypot(trunks.getX() - getX(), trunks.getY() - getY());
            if (distance > 100) {
                disparaitre();
            }
        }
    }

    public void disparaitre() {
        setX(-1000);
        setY(-1000);
        visible = false;
    }

    public boolean estVisible() {
        return visible;
    }
}

