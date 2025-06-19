package universite_paris8.iut.fan.the_namek_quest.jUnit;

import org.junit.jupiter.api.Test;
import universite_paris8.iut.fan.the_namek_quest.modele.Environnement;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.Element;
import universite_paris8.iut.fan.the_namek_quest.modele.personnage.Trunks;

import static org.junit.jupiter.api.Assertions.*;

public class TrunksTest {

    @Test
    void constructeur() {
        Trunks trunks = new Trunks(new Environnement());
        assertNotNull(trunks);
        assertEquals(720, trunks.getY());
        assertEquals(2, trunks.getVitesse());
        assertNotNull(trunks.getInventaire());
        assertNotNull(trunks.getObjectEquipe());
    }

    @Test
    void direction() {
        Trunks trunks = new Trunks(new Environnement());
        trunks.setDirection(1);
        assertEquals(1, trunks.getDirection());
        trunks.setDirection(-1);
        assertEquals(-1, trunks.getDirection());
        trunks.setDirection(0);
        assertEquals(0, trunks.getDirection());
    }

    @Test
    void decrementerPv() {
        Trunks trunks = new Trunks(new Environnement());
        int pvDepart = trunks.getPv();
        trunks.decrementerPv();
        assertEquals(pvDepart - 10, trunks.getPv());
    }

    @Test
    void changerEquipement() {
        Trunks trunks = new Trunks(new Environnement());
        Element avant = trunks.getObjectEquipe();
        trunks.changerEquipement(1);
        Element apres = trunks.getObjectEquipe();
        // Peut être égal si déjà au dernier, mais jamais null
        assertNotNull(apres);
    }

    @Test
    void inventaireNonVide() {
        Trunks trunks = new Trunks(new Environnement());
        assertTrue(trunks.getInventaire().getListObjects().size() > 0);
    }

    @Test
    void mangePasDeHaricotSiPasEquipe() {
        Trunks trunks = new Trunks(new Environnement());
        trunks.setPv(50);
        trunks.mangerHaricot();
        // Doit rester à 50 car haricot non équipé
        assertEquals(50, trunks.getPv());
    }
}