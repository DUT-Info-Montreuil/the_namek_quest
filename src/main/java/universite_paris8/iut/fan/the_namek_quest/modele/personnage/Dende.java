package universite_paris8.iut.fan.the_namek_quest.modele.personnage;

import universite_paris8.iut.fan.the_namek_quest.modele.Environnement;

public class Dende extends PersonnageNonJoueur {

    private final Trunks trunks;
    private boolean dejaApparu = false;
    private boolean aSoigne = false;

    public Dende(int x, int y, Environnement env, Trunks trunks) {
        super(x, y, env);
        this.trunks = trunks;
        // Positionner Dende hors écran au départ
        disparaitre();
    }

    /**
     * Vérifie si Dende peut venir en aide à Trunks.
     * Condition : Trunks a 20 PV ou moins et Dende ne l'a pas encore soigné.
     */
    public boolean dendePeutAider() {
        return trunks.getPv() <= 20 && !aSoigne;
    }

    /**
     * Gère l'apparition ou la disparition de Dende selon la santé de Trunks.
     */
    public void apparitionOuDisparition() {
        if (dendePeutAider()) {
            if (!dejaApparu) {
                // Positionne Dende près de Trunks
                setX(trunks.getX() - 64);
                setY(trunks.getY());
                dejaApparu = true;
            }
        } else {
            disparaitre();
        }
    }

    /**
     * Soigne Trunks s'il est proche et si les conditions sont remplies.
     * Ensuite, Dende disparaît.
     */
    public void soignerTrunksSiProcheEtTouchePressee() {
        if (trunksAProximite() && dendePeutAider()) {
            System.out.println("Dende soigne le Trunks");
            trunks.setPv(trunks.getPv() + 65);
            aSoigne = true;
            System.out.println("PV augmenté");
            disparaitre();
        }
    }

    /**
     * Cache Dende hors écran et remet l'état d'apparition à false.
     */
    public void disparaitre() {
        setX(-1000);
        setY(-1000);
        dejaApparu = false;
    }

    /**
     * Indique si Dende est actuellement visible à l'écran.
     */
    public boolean estVisible() {
        return dejaApparu;
    }

}
