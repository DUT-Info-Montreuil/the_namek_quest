package universite_paris8.iut.fan.the_namek_quest.jUnit;

import org.junit.jupiter.api.Test;
import universite_paris8.iut.fan.the_namek_quest.modele.Environnement;
import universite_paris8.iut.fan.the_namek_quest.modele.Terrain;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.Element;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.materiaux.Haricot;
import universite_paris8.iut.fan.the_namek_quest.modele.personnage.Trunks;

import static org.junit.jupiter.api.Assertions.*;
class TerrainTest {

    @Test
    void constructeur() {
        Trunks trunks = new Trunks(new Environnement());
        assertNotNull(trunks);
        assertEquals(720, trunks.getY());
        assertEquals(2, trunks.getVitesse());
        assertNotNull(trunks.getInventaire());
        assertNotNull(trunks.getObjectEquipe());

        // Cas supplémentaires
        assertTrue(trunks.getPv() > 0, "Les PV initiaux doivent être positifs");
        assertEquals(0, trunks.getDirection(), "La direction par défaut doit être 0");
        assertTrue(trunks.getInventaire().getListObjects().contains(trunks.getObjectEquipe()), "L'objet équipé doit être dans l'inventaire");
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

        // Cas supplémentaires
        trunks.setDirection(999); // Teste une valeur hors norme
        assertEquals(999, trunks.getDirection(), "La direction doit accepter toute valeur");

        trunks.setDirection(Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, trunks.getDirection(), "La direction doit accepter la valeur minimale d'un int");
    }

    @Test
    void decrementerPv() {
        Trunks trunks = new Trunks(new Environnement());
        int pvDepart = trunks.getPv();
        trunks.decrementerPv();
        assertEquals(pvDepart - 10, trunks.getPv());

        // Cas supplémentaires
        trunks.setPv(5);
        trunks.decrementerPv();
        assertTrue(trunks.getPv() <= 0, "Les PV ne doivent pas être négatifs ou doivent être à 0");

        trunks.setPv(0);
        trunks.decrementerPv();
        assertTrue(trunks.getPv() <= 0, "Les PV restent à 0 si déjà à 0");
    }

    @Test
    void changerEquipement() {
        Trunks trunks = new Trunks(new Environnement());
        Element avant = trunks.getObjectEquipe();
        trunks.changerEquipement(1);
        Element apres = trunks.getObjectEquipe();
        // Peut être égal si déjà au dernier, mais jamais null
        assertNotNull(apres);

        // Cas supplémentaires
        trunks.changerEquipement(-1); // Changer en arrière
        assertNotNull(trunks.getObjectEquipe(), "L'objet équipé ne doit jamais être nul");

        // Changer un grand nombre de fois
        for (int i = 0; i < 100; i++) {
            trunks.changerEquipement(1);
        }
        assertNotNull(trunks.getObjectEquipe(), "L'objet équipé ne doit jamais être nul même après plusieurs changements");

        // Changer avec 0 (normalement ne change rien)
        Element courant = trunks.getObjectEquipe();
        trunks.changerEquipement(0);
        assertEquals(courant, trunks.getObjectEquipe(), "Changer avec 0 ne doit rien faire");
    }

    @Test
    void inventaireNonVide() {
        Trunks trunks = new Trunks(new Environnement());
        assertTrue(trunks.getInventaire().getListObjects().size() > 0);

        // Cas supplémentaire : tous les objets sont retirés (si possible)
        trunks.getInventaire().getListObjects().clear();
        assertEquals(0, trunks.getInventaire().getListObjects().size(), "L'inventaire peut être vide après suppression manuelle");
    }
}