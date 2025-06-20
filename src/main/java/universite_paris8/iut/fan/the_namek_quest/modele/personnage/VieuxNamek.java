package universite_paris8.iut.fan.the_namek_quest.modele.personnage;

import universite_paris8.iut.fan.the_namek_quest.modele.Environnement;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.Element;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.materiaux.BouleCristal;

/**
 * Classe VieuxNamek
 * -----------------
 * Représente un PNJ (personnage non joueur) spécial qui apparaît lorsque Trunks
 * collecte des boules de cristal. Il peut apparaître jusqu’à 3 fois pour féliciter Trunks.
 *
 * Règles de comportement :
 * - Il disparaît au démarrage.
 * - Il apparaît près de Trunks s’il obtient une nouvelle boule de cristal (max 3 fois).
 * - Il disparaît si Trunks s’éloigne de plus de 100 pixels.
 */


public class VieuxNamek extends PersonnageNonJoueur {

    /** Référence au personnage principal (Trunks) */
    private Trunks trunks;

    /** Nombre de fois où le Vieux Namek est déjà apparu pour féliciter */
    private int boulesDejaFellicitees = 0;

    /** Indique si le Vieux Namek est visible à l'écran */
    private boolean visible = false;

    /**
     * Constructeur du Vieux Namek.
     *
     * @param x     Position initiale en x (sera ignorée car le personnage disparaît au départ)
     * @param y     Position initiale en y (sera ignorée)
     * @param env   Référence à l’environnement du jeu
     * @param trunks Référence au personnage Trunks
     */
    public VieuxNamek(int x, int y, Environnement env, Trunks trunks) {
        super(x, y, env);
        this.trunks = trunks;
        disparaitre(); // Disparaît dès le début du jeu
    }

    /**
     * Compte le nombre total de boules de cristal dans l'inventaire de Trunks.
     *
     * @return le nombre total de boules de cristal
     */
    public int compterBoulesDeCristal() {
        int total = 0;
        for (Element obj : trunks.getInventaire().getListObjects()) {
            if (obj instanceof BouleCristal) {
                total += ((BouleCristal) obj).getQuantite();
            }
        }
        return total;
    }

    /**
     * Gère l’apparition ou la disparition du Vieux Namek :
     * - Il apparaît à gauche de Trunks lorsqu'une nouvelle boule est collectée (max 3 fois).
     * - Il disparaît si Trunks s'éloigne de plus de 64 pixels.
     */
    public void apparitionOuDisparition() {
        int nbBoules = compterBoulesDeCristal();

        // Apparition si Trunks a collecté une nouvelle boule (jusqu'à 3 fois)
        if (nbBoules > boulesDejaFellicitees && boulesDejaFellicitees < 3) {
            setX(trunks.getX() - 64); // apparaît à gauche de Trunks
            System.out.println(trunks.getX() - 64);
            setY(trunks.getY());
            visible = true;
            boulesDejaFellicitees = nbBoules;
        }

        // Disparition si Trunks est trop loin
        if (visible) {
            double distance = Math.hypot(trunks.getX() - getX(), trunks.getY() - getY());
            if (distance > 64) {
                disparaitre();
            }
        }
    }

    /**
     * Fait disparaître le Vieux Namek hors de la carte et le rend invisible.
     */
    public void disparaitre() {
        setX(-1000); // position hors écran
        setY(-1000);
        visible = false;
    }

    /**
     * Indique si le Vieux Namek est actuellement visible.
     *
     * @return true s’il est visible, false sinon
     */
    public boolean estVisible() {
        return visible;
    }
}