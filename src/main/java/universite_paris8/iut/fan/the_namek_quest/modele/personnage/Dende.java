package universite_paris8.iut.fan.the_namek_quest.modele.personnage;

import universite_paris8.iut.fan.the_namek_quest.modele.Environnement;

public class Dende extends PersonnageNonJoueur {

    private Trunks trunks;
    private boolean dejaApparu = false;
    private boolean aSoigne = false;

    public Dende(int x, int y, Environnement env, Trunks trunks) {
        super(x, y, env);
        this.trunks = trunks;
        setX(-1000); // Caché hors de l'écran par défaut
        setY(-1000);
    }

    public boolean dendePeutAider() {
        return trunks.getPv() <= 20 && !aSoigne;
    }

    public void apparitionOuDisparition() {
        if (dendePeutAider()) {
            if (!dejaApparu) {
                setX(trunks.getX() - 64);
                setY(trunks.getY());
                dejaApparu = true;
            }
        } else {
            disparaitre();
        }
    }

    public void soignerTrunksSiProcheEtTouchePressee() {
        if (trunksAProximite() && dendePeutAider()) {
            System.out.println("Dende soigne le Trunks");
            trunks.setPv(trunks.getPv() + 65);
            aSoigne = true;
            System.out.println("pv augmenté");
            disparaitre();
        }
    }

    public void disparaitre() {
        setX(-1000); // cache hors écran
        setY(-1000);
        dejaApparu = false;
    }
    public boolean estVisible() {
        return dejaApparu;
    }

}

